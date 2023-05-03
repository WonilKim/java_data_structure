package study.study2;

import java.util.ArrayList;

public class UnitTest {
    public static void main(String[] args) {
        
        Archer archer = new Archer();
        archer.shootArrow();

        Magician magician = new Magician();
        magician.shootFireBolt();

        Warrior warrior = new Warrior();
        warrior.swingSword();

        ArrayList units = new ArrayList();

        units.add(archer);
        units.add(magician);
        units.add(warrior);

        for(int i=0; i<3; i++) {
            ((Archer)units.get(i)).shootArrow();
        }

    }
}
