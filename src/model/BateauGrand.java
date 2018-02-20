package model;

import java.util.Random;

public class BateauGrand extends Navire {
    private static int porteeTir;
    private static int[] array = {0,0,1,1,1,2,2,2,2,2};
    
    public BateauGrand(int id) {
        super("BIG", 100,id);
        this.porteeTir = getPorteeTir();
    }
    
    public int getPorteeTir() {
    int portee = new Random().nextInt(array.length);
    return array[portee];}
    }
