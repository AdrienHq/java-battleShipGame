package model;

public abstract class Navire implements Deplacement{
    
    protected Army armee; 
    protected final String typeNavire;
    protected Position pos = null; 
    protected int pointVie; //50 pour les petits et 100 pour les grands (simple et double résistance au tir / mine)
    
    protected int id  ;
    
    protected Navire(String type, int pv,int id){ 
        this.typeNavire= type;
        this.pointVie = pv;
        this.id = id ;
    }
    
    public Position getPosition () {
        return this.pos;
    }
    
    public int getX () {
        return this.pos.getX();
    }
    
    public int getY () {
        return this.pos.getY();
    }

    public String getType() {
        return typeNavire;
    }

    public int getPointVie() {
        return pointVie;
    }
    
//     @Override
//     public String toString(){
//         
//     }
          
//    public void deplace (Direction d) {
//        this.pos.move(d);
//    }
    //PEUT ETRE POUR UNE PROCHAINE ITERATION
    
}
