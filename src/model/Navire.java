package model;

public abstract class Navire {

    protected String nom;
    protected final String typeNavire;
    protected Position pos = null;
    protected int pointVie; //50 pour les petits et 100 pour les grands (simple et double résistance au tir / mine)
    protected Army army = null;
    protected String popo = "";
    protected int deplacementMax;
    protected boolean estPlace = false;

    protected Navire(String nom, String type, int pv, int deplacementMax) {
        this.nom = nom;
        this.typeNavire = type;
        this.pointVie = pv;
        this.deplacementMax = deplacementMax;
    }

    public int getDeplacementMax() {
        return deplacementMax;
    }
    
    public boolean getEstPlace(){
        return this.estPlace;
    }
    
    public void switchEstPlace(){
        this.estPlace = this.estPlace = false;
    }

    public void setPopo(String popo) {
        this.popo = popo;
    }

    public String getPopo() {
        return this.popo;
    }

    public Position getPosition() {
        return this.pos;
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public int getX() {
        return this.pos.getX();
    }

    public int getY() {
        return this.pos.getY();
    }

    public String getType() {
        return typeNavire;
    }

    public int getPointVie() {
        return pointVie;
    }

    public String getNom() {
        return this.nom;
    }

    public void degat(int degat) {
        this.pointVie -= degat;
        if (this.pointVie < 0) { // Pv à zero minimum
            this.pointVie = 0;
        }
    }

    public abstract int getPorteeTir();
}
