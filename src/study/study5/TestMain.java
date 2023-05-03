package study.study5;

import java.util.ArrayList;

public class TestMain {
    public static void main(String[] args) {
        
        Archer a = new Archer();
        Worrior w = new Worrior();
        Magician m = new Magician();

        String s = a.toString();
        System.out.println(s);
        System.out.println(a.toString());

        ArrayList<Unit> unitArray = new ArrayList<Unit>();

        unitArray.add(a);
        unitArray.add(w);
        unitArray.add(m);

        for (int i=0; i< 3; i++) {
            unitArray.get(i).run();
            unitArray.get(i).walk();
            unitArray.get(i).attack();

            System.out.println(unitArray.get(i).toString());
        }

        TestClass t = new TestClass();

        //Object o;

    }
}
