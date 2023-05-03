package study.study4;

import java.util.ArrayList;

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

        ArrayList unitArray = new ArrayList();

        unitArray.add(a);
        unitArray.add(w);
        unitArray.add(m);

        // for (int i=0; i< 3; i++) {
        //     unitArray.get(i).run();
        //     unitArray.get(i).walk();
        //     unitArray.get(i).arrow();
        // }


    }
}
