package src;

class Solution {
  public int solution(int[][] sizes) {
    int answer = 0;

    // 행렬 정렬 (눞이기)
    for (int i=0; i<sizes.length; i++) {
      if (sizes[i][0] >= sizes[i][1]) continue;
      else {
        int temp = sizes[i][0];
        sizes[i][0] = sizes[i][1];
        sizes[i][1] = temp;
      }
    }

    int maxCol = Integer.MIN_VALUE;
    int maxRow = Integer.MIN_VALUE;

    // 최대, 최소 찾기
    for (int i=0; i<sizes.length; i++) {
      if (sizes[i][0] >= maxRow) maxRow = sizes[i][0];
      if (sizes[i][1] >= maxCol) maxCol = sizes[i][1];
    }

    answer = maxCol*maxRow;

    return answer;
  }
}