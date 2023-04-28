package study4;

public class Archer extends Unit {
    public void arrow() {
        System.out.println("궁수가 화살을 쏩니다.");
    }

    @Override
    public void run() {
        System.out.println("궁수가 달립니다.");
    }

    @Override
    public void walk() {
        System.out.println("궁수가 걷습니다.");
    }
}
