package algorithm;

import java.util.HashMap;
import java.util.Map;

class Solution1 {
  public String solution(String[] participants, String[] completions){
    String answer = "";

    // HashMap
    Map<String, Integer> hashMap = new HashMap<>();

    // 참가자들 명단에 이름이랑 숫자 넣기
    for (String player : participants)
      hashMap.put(player, hashMap.getOrDefault(player, 0) + 1);

    // 완주자들은 전체 명단에서 -1
    for (String player : completions)
      hashMap.put(player, hashMap.get(player) - 1);

    // value 값이 0이 아닌 사람 찾
    for (String key : hashMap.keySet()) {
      if (hashMap.get(key) != 0) return key;
    }

    return answer;
  }
}

// hash --> 구현 : HashMap, HashTable, HashSet
// Hash 함수 : key를 배열 인덱스로 바꿔주는 함수

// hashMap.put(key, value) : 값 추가 또는 수정
// hashMap.get(key) : 키에 해당하는 값 반환
// hashMap.containsKey(key)
// hashMap.keySet() : 모든 key 반환
// hashMap.values() : 모든 value 반환
// hashMap.entrySet() : key-value 쌍 반환 (for 문에서 자주 사용)
// hashMap.getOrDefault(key, default) : key에 해당하는 value가 있으면 가져오고, 아닐 경우 default로 지정하여 사용