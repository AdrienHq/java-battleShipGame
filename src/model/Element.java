package model;

public class Element extends Case{
    
    private Navire navire;
    
    public Element (Navire n) {
        this.navire = n;
    }
    
    public Element () {
        this.navire = null;
    }
    
    public Navire getAliment () {
        return this.navire;
    }
    
    public void supprimerNavire () {
        this.navire = null;
    }

    public TypeNavire getTypeNavire() {
        if (this.navire != null) {
            return navire.getType();
        }
        return null;
    }
   
}
