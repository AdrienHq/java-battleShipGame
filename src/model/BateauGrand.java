package model;

public class BateauGrand extends Navire {

    public BateauGrand(int pv, int resi, int porteeTir) {
        super(pv, resi, porteeTir);
    }

    public int getPointVie() {
        return 100;
    }

    public int getResistance() {
        return resistance;
    }

    public int getPorteeTir() {
        return porteeTir;
    }
    
    @Override
    TypeNavire getTypeNavire() {
        return TypeNavire.GRAND;
    }
    
}
