package study.study5;

public class Archer implements Unit {

    @Override
    public void run() {
        System.out.println("궁수가 달립니다.");
    }

    @Override
    public void walk() {
        System.out.println("궁수가 걷습니다.");
    }

    @Override
    public void attack() {
        System.out.println("궁수가 활을 쏩니다.");
    }

    @Override
    public String toString() {
        return "궁수입니다.";
    }
    
}
