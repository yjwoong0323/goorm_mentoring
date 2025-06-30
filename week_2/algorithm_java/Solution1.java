package src;

import java.util.Arrays;

class Solution {
  public int[] solution(int[] array, int[][] commands) {
    int[] answer = new int[commands.length];

    for (int i=0; i<commands.length; i++) {
      int from = commands[i][0];
      int to = commands[i][1];
      int k = commands[i][2];

      int[] sub = Arrays.copyOfRange(array, from-1, to);
      Arrays.sort(sub);

      answer[i] = sub[k-1];
    }

    return answer;
  }
}