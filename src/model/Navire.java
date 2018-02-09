package model;

public abstract class Navire {
    
    protected Position pos = null;
    protected int pointVie;
    protected int resistance;
    protected int porteeTir;
    
    protected Navire(int pv, int resi, int porteeTir){ 
        this.pointVie = pv;
        this.resistance = resi;
        this.porteeTir = porteeTir;
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
    
     abstract TypeNavire getTypeNavire();
     
     @Override
     public String toString(){
         
     }
    
    
    
//    public void deplace (Direction d) {
//        this.pos.move(d);
//    }
    //PEUT ETRE POUR UNE PROCHAINE ITERATION
    
}
