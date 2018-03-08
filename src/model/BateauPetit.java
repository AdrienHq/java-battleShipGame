package model;

import java.util.Random;

public class BateauPetit extends Navire {

   private static int porteeTir; 
    private static int[] array = {0, 0, 0, 0, 0, 1, 1, 1, 2, 2};

    public BateauPetit(String nom) {
        super(nom,"SMALL", 50,2);
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

