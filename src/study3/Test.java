package study3;

public class Test {
    public static void main(String[] args) {
        
        Archer a = new Archer();
        Worrior w = new Worrior();
        Magician m = new Magician();

        a.run();
        a.walk();
        a.arrow();

        w.run();
        w.walk();
        w.sword();

        m.run();
        m.walk();
        m.meteo();

    }
}
