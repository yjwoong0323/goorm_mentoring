package src;

import java.util.Arrays;

class Solution {
  public int solution(int n, int[] lost, int[] reserve) {
    int answer = n-lost.length;

    // 정렬
    Arrays.sort(lost);
    Arrays.sort(reserve);

    // 체육복 가지고 온 사람이 도난당한 경우
    for (int i=0; i<lost.length; i++){
      for (int j=0; j<reserve.length; j++){
        if (lost[i] == reserve[j]) {
          answer++;
          lost[i] = -1;
          reserve[j] = -1;
        }
      }
    }

    // 앞 뒤 사람에게 빌려주는 경우
    for (int i=0; i<lost.length; i++){
      for (int j=0; j<reserve.length; j++){
        if (lost[i] == reserve[j]-1 || lost[i] == reserve[j]+1) {
          answer++;
          lost[i] = -1;
          reserve[j] = -1;
        }
      }
    }
    return answer;
  }
}