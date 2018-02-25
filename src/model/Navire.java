package model;

public abstract class Navire implements Deplacement {

    protected final String nom;
    protected final String typeNavire;
    protected Position pos = null;
    protected int pointVie; //50 pour les petits et 100 pour les grands (simple et double résistance au tir / mine)
    protected Army army;
    protected int portee = 0;

    protected Navire(String type, int pv, int portee) {
        this.nom = army.getNom();
        this.typeNavire = type;
        this.pointVie = pv;
        this.portee = portee;

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

    public void tirDegat(Navire ennemy) {
         ennemy.pointVie -= 50;
    }
    
    public int getPortee(){
        return 
    }
}

//     @Override
//     public String toString(){
//         
//     }
//    public void deplace (Direction d) {
//        this.pos.move(d);
//    }
//PEUT ETRE POUR UNE PROCHAINE ITERATION

