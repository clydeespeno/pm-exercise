public class Partition {

  /**
   * Time complexity is linear, this algorithm traverses the list only once,
   * Using 3 extra space to store 3 numbers:
   *   current traversal index
   *   last found even index
   *   a swap variable
   *
   * This algorithm scans only for even numbers that are preceded by an odd number,
   * then swaps it to the last known position where an odd number can be swaped out.
   */
  private static int[] partition(int[] list) {
    System.out.print("input: ");
    printList(list);

    // Start of algorithm
    int lastEvenIndex = -1;
    for (int i = 0; i < list.length; i++) {
      if (list[i] % 2 == 0 && ++lastEvenIndex != i) {
        int swap = list[lastEvenIndex];
        list[lastEvenIndex] = list[i];
        list[i] = swap;
      }
    }

    System.out.print("output: ");
    printList(list);
    return list;
  }

  private static void printList(int[] list) {
    for (int a : list) {
      System.out.print(a + " ");
    }
    System.out.println();
  }

  private static boolean orderingIsCorrect(int[] list) {
    for (int i = 0; i < list.length; i++) {
      if (i < list.length - 1 && list[i] % 2 == 1 && list[i] % 2 == 0) {
        return false;
      }
    }
    return true;
  }

  public static void main(String args[]) {
    assert orderingIsCorrect(partition(new int[]{}));
    assert orderingIsCorrect(partition(new int[]{1}));
    assert orderingIsCorrect(partition(new int[]{1, 2}));
    assert orderingIsCorrect(partition(new int[]{1, 2, 1, 2}));
    assert orderingIsCorrect(partition(new int[]{1, 2, 1, 2}));
    assert orderingIsCorrect(partition(new int[]{2, 2, 2, 2}));
    assert orderingIsCorrect(partition(new int[]{2, 4, 7, 6, 1, 3, 5, 4}));
    assert orderingIsCorrect(partition(new int[]{2, 4, 7, 6, 1, 4, 3, 5, 4, 4, 4}));
  }
}
