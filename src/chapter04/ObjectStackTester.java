package chapter04;

// int형 고정 길이 스택의 사용 예

import java.util.Scanner;

class ObjectStackTester {
    public static void main(String[] args) {
        Scanner stdIn = new Scanner(System.in);
        ObjectStack stck = new ObjectStack(64);    // 최대 64 개를 푸시할 수 있는 스택

        while (true) {
        	System.out.println(); // 메뉴 구분을 위한 빈 행 추가
            System.out.printf("현재 데이터 개수: %d / %d\n", stck.size(), stck.getCapacity());
            System.out.print("(1)푸시　(2)팝　(3)피크　(4)덤프　(0)종료: ");

            int menu = stdIn.nextInt();
            if (menu == 0) break;

            Point x;
            switch (menu) {
             case 1:                                // 푸시
                System.out.print("데이터: ");
                int val = stdIn.nextInt();
                x = new Point(val, val * 2 + val % 4);
                try {
                    stck.push(x);
                 } catch (ObjectStack.OverflowObjectStackException e) {
                    System.out.println("스택이 가득 찼습니다.");
                }
                break;

             case 2:                                // 팝
                try {
                     x = stck.pop();
                    System.out.println("팝한 데이터는 " + x + "입니다.");
                 } catch (ObjectStack.EmptyObjectStackException e) {
                    System.out.println("스택이 비어있습니다.");
                }
                break;

             case 3:                                // 피크
                try {
                     x = stck.peek();
                    System.out.println("피크한 데이터는 " + x + "입니다.");
                 } catch (ObjectStack.EmptyObjectStackException e) {
                    System.out.println("스택이 비어있습니다.");
                }
                break;

             case 4:                                // 덤프
                stck.dump();
                break;
            }
        }
    }
}