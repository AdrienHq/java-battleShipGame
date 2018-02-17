package model;

public abstract class Flottant {
    //Pour les mines aet possiblements autre Case non bateau. 

    protected final TypeFlottant typeflottant;
    protected final int DEGATS;
    protected Flottant(int degats, TypeFlottant type) {
        this.DEGATS = degats;
        this.typeflottant = type;
    }

//    public Flottant(TypeFlottant type) {
//        this.typeflottant = type;
//        this.DEGATS = 0;
//    }
    //JE NE SAIS PAS SI ON A BESOIN D'AUTRE CONSTRUCTEUR DONC VOIR SI IL Y A DES D2GATS STATIC

    public TypeFlottant getType() {
        return this.typeflottant;
    }

    public int getDegats() {
        return this.DEGATS;
    }
}
