package model;

public abstract class Navire {
    
    protected final TypeNavire type;
    protected Position pos = null; 
    protected int pointVie; //a 100 au début pour tlm
    protected int resistance;//petit resi = 1 / grand resi = 2 
    
    protected Navire(TypeNavire type, int pv, int resi){ 
        this.type= type;
        this.pointVie = pv;
        this.resistance = resi;
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

    public int getResistance() {
        return resistance;
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
