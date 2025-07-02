package src;

class Solution {
  public int solution(int[][] sizes) {
    int answer = 0;

    // 정렬(카드 눞이기)
    for (int[] size : sizes) {
      if (size[0] < size[1]) {
        int temp = size[0];
        size[0] = size[1];
        size[1] = temp;
      }
    }

    // 최대값 구하기
    int maxLow = Integer.MIN_VALUE;
    int maxCol = Integer.MIN_VALUE;

    for (int[] size : sizes) {
      if (size[0] > maxLow) maxLow = size[0];
      if (size[1] > maxCol) maxCol = size[1];
    }

    return maxLow*maxCol;
  }
}

// [[60, 50], [30, 70], [60, 30], [80, 40]]