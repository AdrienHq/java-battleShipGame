package model;

import java.util.Random;

public class BateauGrand extends Navire {

    public static int porteeTir;
    private static int[] array = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2};

    public BateauGrand(String nom) {
        super(nom, "BIG", 100,1);
    }

    @Override
    public void move(Position pos) {
//        this.pos.déplacer(pos);
    }

    @Override
    public int getPorteeTir() {
        int portee = new Random().nextInt(array.length);
        return array[portee];
    }

}
