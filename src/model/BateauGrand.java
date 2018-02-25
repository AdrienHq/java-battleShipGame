package model;

import java.util.Random;

public class BateauGrand extends Navire {

    public static int porteeTir;
    private static int[] array = {0, 0, 1, 1, 1, 2, 2, 2, 2, 2};

    public BateauGrand() {
        super("BIG", 100);
    }

    @Override
    public void move(Direction d) {
        this.pos.déplacer(d);

    }
    @Override
    public int getPorteeTir() {
        int portee = new Random().nextInt(array.length);
        return array[portee];
    }

}
