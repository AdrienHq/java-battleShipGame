package model;

import java.util.Random;

public class BateauGrand extends Navire {
    private static int porteeTir;
    private static int[] array = {0,0,1,1,1,2,2,2,2,2};
    
    public BateauGrand(TypeNavire type, int pv, int porteeTir) {
        super(TypeNavire.PETIT, 100);
        this.porteeTir = getPorteeTir();
    }
    
    public int getPorteeTir() {
    int portee = new Random().nextInt(array.length);
    return array[portee];}
    }
