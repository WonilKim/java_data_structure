package study.study5;

public class Magician implements Unit {

    @Override
    public void run() {
        System.out.println("마법사가 달립니다.");
    }

    @Override
    public void walk() {
        System.out.println("마법사가 걷습니다.");
    }

    @Override
    public void attack() {
        System.out.println("마법사가 활을 쏩니다.");
    }
    
}
