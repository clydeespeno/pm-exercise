import java.util.Stack;

/**
 * To represent a queue using two stacks, we can visualize the 2 stacks as having different roles:
 * in: accept new input items
 * out: reverse the contents of in during dequeue when it's not empty
 *
 * To optimize a bit, we can fill the out only when a dequeue op is invoked. This way, we can dequeue as much
 * as we can as long as the out is not empty, and only refill it when it's empty and when an operation to
 * dequeue it is invoked.
 */
public class MyQueue<T> {

  private Stack<T> in = new Stack<>();
  private Stack<T> out = new Stack<>();

  void queue(T t) {
    in.push(t);
  }

  T dequeue() {
    if (out.isEmpty()) {
      while(!in.isEmpty()) {
        out.push(in.pop());
      }
    }
    return out.pop();
  }

  public static void main(String[] args) {

    MyQueue<Integer> q = new MyQueue<>();
    q.queue(1);
    q.queue(2);
    q.queue(3);

    assert q.dequeue().equals(1);
    assert q.dequeue().equals(2);
    assert q.dequeue().equals(3);

  }

}
