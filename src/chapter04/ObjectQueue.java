package chapter04;

import java.util.ArrayList;

// int형 고정 길이 큐

public class ObjectQueue {
    private ArrayList<Point> que;   // 큐용 배열
    private int capacity;         // 큐의 크기
    private int front;            // 맨 처음 요소 커서
    private int rear;             // 맨 끝 요소 커서
    private int num;              // 현재 데이터 개수

    //--- 실행시 예외: 큐가 비어있음 ---//
    public class EmptyIntQueueException extends RuntimeException {
        public EmptyIntQueueException() { }
    }

    //--- 실행시 예외: 큐가 가득 찼음 ---//
    public class OverflowIntQueueException extends RuntimeException {
        public OverflowIntQueueException() { }
    }

    //--- 생성자(constructor) ---//
    public ObjectQueue(int maxlen) {
        num = front = rear = 0;
        capacity = maxlen;
        try {
            que = new ArrayList<Point>(capacity);          // 큐 본체용 배열을 생성
            for(int i=0; i<capacity; i++) {
                que.add(null);
            }
        } catch (OutOfMemoryError e) {        // 생성할 수 없음
            capacity = 0;
        }
    }

    
    //--- 큐에 데이터를 인큐 ---//
    public Point enque(Point x) throws OverflowIntQueueException {
        if (num >= capacity)
            throw new OverflowIntQueueException();            // 큐가 가득 찼음
        que.set(rear++, x);
        num++;
        if (rear == capacity)
            rear = 0;
        return x;
    }

    //--- 큐에서 데이터를 디큐 ---//
    public Point deque() throws EmptyIntQueueException {
        if (num <= 0)
            throw new EmptyIntQueueException();            // 큐가 비어있음
        
        Point x = que.get(front++);
        num--;
        if (front == capacity)
            front = 0;
        return x;
    }

    //--- 큐에서 데이터를 피크(프런트 데이터를 들여다봄) ---//
    public Point peek() throws EmptyIntQueueException {
        if (num <= 0)
            throw new EmptyIntQueueException();            // 큐가 비어있음
        return que.get(front);
    }

    //--- 큐를 비움 ---//
    public void clear() {
        num = front = rear = 0;
        for(int i=0; i<que.size(); i++) {
            que.set(i, null);
        }
    }

    //--- 큐에서 x를 검색하여 인덱스(찾지 못하면 –1)를 반환 ---//
    public int indexOf(Point x) {
        for (int i = 0; i < num; i++) {
            int idx = (i + front) % capacity;
            if (que.get(idx).equals(x))                // 검색 성공
                return idx;
        }
        return -1;                            // 검색 실패
    }

    //--- 큐의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    //--- 큐에 쌓여 있는 데이터 개수를 반환 ---//
    public int size() {
        return num;
    }

    //--- 큐가 비어있는가? ---//
    public boolean isEmpty() {
        return num <= 0;
    }

    //--- 큐가 가득 찼는가? ---//
    public boolean isFull() {
        return num >= capacity;
    }

    //--- 큐 안의 모든 데이터를 프런트 → 리어 순으로 출력 ---//
    public void dump() {
        if (num <= 0)
            System.out.println("큐가 비어있습니다.");
        else {
            for (int i = 0; i < num; i++)
                System.out.print(que.get((i + front) % capacity) + " ");
            System.out.println();
        }
    }

    public ArrayList<Point> getAll() {
        if (this.size() <= 0)
            return null;
        else {
            return this.que;
        }

    }

    @Override
    public String toString() {

        String str = "";
        for (Point p : this.que) {
            str += "[" + p.toString() + "] ";
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        ObjectQueue q = (ObjectQueue) obj;
        if (this.size() != q.size()) {
            return false;
        } else {
            for (int i=0; i<this.size(); i++) {
                if (this.que.get(i).equals(q.que.get(i)) == false) {
                    return false;
                }
            }
            return true;
        }
    }
}