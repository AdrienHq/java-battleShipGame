package model;

public abstract class Flottant {
    //Pour les mines et possiblement autre Case non bateau. 

    protected final String typeflottant;
    protected final int DEGATS;
    protected Flottant(int degats, String type) {
        this.DEGATS = degats;
        this.typeflottant = type;
    }

    public String getType() {
        return this.typeflottant;
    }

    public int getDegats() {
        return this.DEGATS;
    }
}
