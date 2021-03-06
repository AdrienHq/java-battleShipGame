package model;

public abstract class Navire implements Deplacement {

    protected String nom ;
    protected final String typeNavire;
    protected Position pos = null;
    protected int pointVie; //50 pour les petits et 100 pour les grands (simple et double résistance au tir / mine)
    protected Army army = null;
//    protected int portee = 0;
    protected String popo = "" ;
    protected int deplacementMax ;
   

    

    protected Navire(String nom,String type, int pv,int deplacementMax) {
        this.nom = nom;
        
        this.typeNavire = type;
        this.pointVie = pv;
        this.deplacementMax = deplacementMax ;

    }
    public int getDeplacementMax() {
        return deplacementMax;
    }
    
    public void setPopo(String popo){
        this.popo = popo;
    }
    public String getPopo(){
        return this.popo ;
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
    
    public String getNom(){
        return this.nom;
    }

    public void degat(int degat) {
        this.pointVie -= degat;
        if(this.pointVie < 0){ // Pv à zero minimum
            this.pointVie = 0 ;    
        }
    }
    
    public abstract int getPorteeTir();
}

//     @Override
//     public String toString(){
//         
//     }
//    public void deplace (Direction d) {
//        this.pos.move(d);
//    }
//PEUT ETRE POUR UNE PROCHAINE ITERATION

