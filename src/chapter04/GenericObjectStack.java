package chapter04;

import java.util.ArrayList;

// --- 실행시 예외: 스택이 비어있음 ---//
class EmptyObjectStackException extends RuntimeException {
    public EmptyObjectStackException() {
    }
}

// --- 실행시 예외: 스택이 가득 참 ---//
class OverflowObjectStackException extends RuntimeException {
    public OverflowObjectStackException() {
    }
}

public class GenericObjectStack<T> {
    private ArrayList<T> data; // 스택용 배열
    private int capacity; // 스택의 크기
    private int top; // 스택 포인터

    // --- 생성자(constructor) ---//
    public GenericObjectStack(int maxlen) {
        top = 0;
        capacity = maxlen;
        try {
            data = new ArrayList<T>(); // 스택 본체용 배열을 생성
        } catch (OutOfMemoryError e) { // 생성할 수 없음
            capacity = 0;
        }
    }

    // --- 스택에 x를 푸시 ---//
    public T push(T x) throws OverflowObjectStackException {
        if (top >= capacity) // 스택이 가득 참
            throw new OverflowObjectStackException();

        data.add(x);
        top++;
        return x;
    }

    // --- 스택에서 데이터를 팝(정상에 있는 데이터를 꺼냄) ---//
    public T pop() throws EmptyObjectStackException {
        if (top <= 0) // 스택이 빔
            throw new EmptyObjectStackException();

        return data.remove(--top);
    }

    // --- 스택에서 데이터를 피크(peek, 정상에 있는 데이터를 들여다봄) ---//
    public T peek() throws EmptyObjectStackException {
        if (top <= 0) // 스택이 빔
            throw new EmptyObjectStackException();
        return data.get(top - 1);
    }

    // --- 스택을 비움 ---//
    public void clear() {
        data.clear();
        top = 0;
    }

    // --- 스택에서 x를 찾아 인덱스(없으면 –1)를 반환 ---//
    public int indexOf(T x) {
        for (int i = top - 1; i >= 0; i--) // 꼭대기 쪽부터 선형 검색
            if (data.get(i).equals(x))
                return i; // 검색 성공
        return -1; // 검색 실패
    }

    // --- 스택의 크기를 반환 ---//
    public int getCapacity() {
        return capacity;
    }

    // --- 스택에 쌓여있는 데이터 갯수를 반환 ---//
    public int size() {
        return top;
    }

    // --- 스택이 비어있는가? ---//
    public boolean isEmpty() {
        return top <= 0;
    }

    // --- 스택이 가득 찼는가? ---//
    public boolean isFull() {
        return top >= capacity;
    }

    // --- 스택 안의 모든 데이터를 바닥 → 정상 순서로 표시 ---//
    public void dump() {
        if (top <= 0)
            System.out.println("스택이 비어있습니다.");
        else {
            for (int i = 0; i < top; i++)
                System.out.print(data.get(i) + " ");
            System.out.println();
        }
    }

    public ArrayList<T> getAll() {
        if (top <= 0)
            return null;
        else {
            return this.data;
        }

    }

    @Override
    public String toString() {

        String str = "";
        for (T p : this.data) {
            str += "[" + p.toString() + "] ";
        }

        return str;
    }

    @Override
    public boolean equals(Object obj) {
        ObjectStack st = (ObjectStack) obj;
        if (this.size() != st.size()) {
            return false;
        } else {
            for (T p : this.data) {
                if (p.equals(st) == false) {
                    return false;
                }
            }
            return true;
        }
    }
}