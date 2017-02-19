public class ThreadTest {

  public static void main(String[] args) throws InterruptedException {
    Foo f = new Foo();

    Thread first = new Thread(() -> f.guardCall("first", f::first, "second"));
    Thread second = new Thread(() -> f.guardCall("second", f::second, "third"));
    Thread third = new Thread(() -> f.guardCall("third", f::third, ""));

    third.start();
    second.start();
    first.start();

    f.updateMethod("first");

    first.join();
    second.join();
    third.join();
  }

}

class Foo {

  Foo() {

  }

  private String method = "";

  void first() {
    System.out.println("first is invoked!");
  }

  void second() {
    System.out.println("second is invoked!");
  }

  void third() {
    System.out.println("third is invoked!");
  }

  synchronized void updateMethod(String m) {
    System.out.println("Updates method with message " + m);
    method = m;
    notifyAll();
  }

  synchronized void guardCall(String m, Runnable r, String update) {
    while(!m.equals(method)) {
      System.out.println("Guards method " + m + " until foo is in correct state");
      try {
        wait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    r.run();
    updateMethod(update);
  }
}
