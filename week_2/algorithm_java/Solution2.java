package src;

class Solution2 {
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
    int maxRow = Integer.MIN_VALUE;
    int maxCol = Integer.MIN_VALUE;

    for (int[] size : sizes) {
      if (size[0] > maxRow) maxRow = size[0];
      if (size[1] > maxCol) maxCol = size[1];
    }

    return maxRow*maxCol;
  }
}

// [[60, 50], [30, 70], [60, 30], [80, 40]]