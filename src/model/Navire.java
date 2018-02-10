package model;

public abstract class Navire {
    
    protected final TypeNavire type;
    protected Position pos = null; 
    protected int pointVie; //50 pour les petits et 100 pour les grands (simple et double résistance au tir / mine)
    
    protected Navire(TypeNavire type, int pv){ 
        this.type= type;
        this.pointVie = pv;
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

    public TypeNavire getType() {
        return type;
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
