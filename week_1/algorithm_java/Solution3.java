package algorithm;

import java.util.PriorityQueue;

class Solution3 {
  public int solution(int[] scoville, int K) {
    int count = 0;

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for (int s : scoville) {
      heap.add(s);
    }

    while (heap.size() >= 2 && heap.peek() < K) {
      int leastHot = heap.poll();
      int secondLeastHot = heap.poll();

      heap.add(leastHot + (secondLeastHot * 2));
      count++;
    }

    if (heap.isEmpty() || heap.peek() < K)
      return -1;

    return count;
  }
}