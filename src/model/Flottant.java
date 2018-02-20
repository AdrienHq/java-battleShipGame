package model;

public abstract class Flottant {
    //Pour les mines et possiblement autre Case non bateau. 

    protected final String typeflottant;
    protected final int DEGATS;
    protected Flottant(int degats, String type) {
        this.DEGATS = degats;
        this.typeflottant = type;
    }

//    public Flottant(TypeFlottant type) {
//        this.typeflottant = type;
//        this.DEGATS = 0;
//    }
    //JE NE SAIS PAS SI ON A BESOIN D'AUTRE CONSTRUCTEUR DONC VOIR SI IL Y A DES D2GATS STATIC

    public String getType() {
        return this.typeflottant;
    }

    public int getDegats() {
        return this.DEGATS;
    }
}
