package algorithm;

import java.util.*;

public class Solution2 {
  public int[] solution(int []arr) {

    // 예외 처리
    if (arr.length == 0) return new int[0];

    // stack
    Stack<Integer> stack = new Stack<>();
    stack.push(arr[0]);
    for (int number : arr) {
      if (stack.peek() != number) {
        stack.push(number);
      }
    }

    // 배열로 반환
    int[] answer = new int[stack.size()];
    for (int i=stack.size()-1; i>=0; i--) {
      answer[i] = stack.pop();
    }

    return answer;
  }
}

// stack (접시) : LIFO
// stack.push(item) : 스택에 데이터 삽입 (쌓기)
// stack.pop() : 맨 위 데이터 꺼냄 (제거) --> 비어있으면 Exception
// stack.peek() : 맨 위 데이터 확인 (제거X) --> 비어있으면 Exception
// stack.size()
// stack.isEmpty()

// queue (줄 서기) : FIFO --> 구현 : LinkedList, ArrayDeque
// queue.offer(item) : 큐 뒤에 데이터 삽입
// queue.poll() : 큐 앞에서 데이터 꺼냄 (제거)
// queue.peek() : 큐 앞에서 데이터 확인 (제거X)
// queue.size()
// queue.isEmpty()

// Deque : 양쪽에서 삽입/삭제 가능한 큐