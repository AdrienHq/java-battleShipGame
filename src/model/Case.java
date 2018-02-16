package model;

import java.util.Objects;

public abstract class Case {
    
    abstract public boolean estRadioactif ();
    
}

//    private String nom = "";
//    private boolean radioActif = false;
//
//    public Case() {
//
//    }
//    
//    public Case(Case c){
//        this.nom = c.getNom();
//    }
//
//    public String getNom() {
//        return this.nom;
//    }
//    
//    public String getElement() {
//        return this.nom;
//    }
//    
//    public void setRadioActif(boolean radioActif){
//        this.radioActif = radioActif;
//    }
//    
//    public boolean estRadioactif(){
//        return radioActif; 
//    }
//
//    @Override
//    public boolean equals(Object o) {
//
//        if (o instanceof Case) {
//            Case c = (Case) o;
//            return this.nom.equals(c.getNom());
//        }
//        return false;
//
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 17;
//        hash = 73 * hash + Objects.hashCode(this.nom);
//        return hash;
//    }
//
//    @Override
//    public String toString() {
//        return this.nom;
//    }
//}
