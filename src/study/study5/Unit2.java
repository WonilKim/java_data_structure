package study.study5;

public abstract class Unit2 implements Unit3 {
    abstract void run();
    abstract void walk();
    abstract void attack();

    public void Drink() {
        System.out.println("회복포션을 마신다.");
    }
}
