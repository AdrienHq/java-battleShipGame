package model;

import java.util.Random;

public class BateauPetit extends Navire {

    private static int porteeTir;
    private static int[] array = {0, 0, 0, 0, 0, 1, 1, 1, 2, 2};

    public BateauPetit() {
        super("SMALL", 50);
        this.porteeTir = getPorteeTir();
    }

    public int getPorteeTir() {
        int portee = new Random().nextInt(array.length);
        return array[portee];
    }

    @Override
    public void move(Direction d) {
        this.pos.déplacer(d);
    }
}

//    @Override
//    public void setX(int x) {
//    }
//
//    @Override
//    public void setY(int y) {
//    }
//
//    @Override
//    public void deplaceHorizontal(int distance) {
//        setX(getX() + distance);
//    }
//
//    @Override
//    public void deplaceVertical(int distance) {
//        setY(getY() + distance);
//    }
//
//    @Override
//    public void déplacer(Direction d) { 
//        int maxDeplacement = 0;
//        int distance = 1;
//        do {
//            switch (d) {
//                case HAUT:
//                    this.deplaceVertical(distance);
//                case BAS:
//                    this.deplaceVertical(distance);
//                case GAUCHE:
//                    this.deplaceHorizontal(distance);
//                case DROITE:
//                    this.deplaceHorizontal(distance);
//            }
//            maxDeplacement++;
//        } while (maxDeplacement < 2);
//    }
