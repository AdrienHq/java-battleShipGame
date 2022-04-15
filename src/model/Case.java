package model;

import java.util.Objects;

public class Case {

   
    
    private String name ;
    Position p ;
    
    private Navire navire;
    private Flottant flottant;
    
    Boolean choixPossible = false ;
    Boolean radioActif = false;
    Boolean estVide = true;
    
    public Case(Navire n) {
        this.navire = n;
    }

    public Case(Flottant f) {
        this.flottant = f;
    }

    public Case(String name,Position p) {
        this.name = name ;
        this.p = p;
        this.navire = null;
        this.flottant = null;
    }
    
    
     public String getName() {
        return name;
    }
     
     public Position getPosition() {
        return p;
    } 
//     public boolean getCas(String name){
//     
//     }
    public boolean estchoixPossible(){
        return choixPossible ;
    }
    public void switchChoixPossible(){
        
        this.choixPossible = this.choixPossible != true;
    }
    public boolean estRadioactif() {
        return radioActif;
    }

    public boolean estVide() {
        return estVide;
    }

    public void switchVide() {
        this.estVide = this.estVide == false;
    }

    public void switchRadioactif() {
        this.radioActif = this.radioActif == false;
    } 
    public void setNavire(Navire n) {
        this.navire= n ;
        this.switchVide();
    }

    public Navire getNavire() {
        return this.navire;
    }
    
    void setFlottant(Flottant f) {
        this.flottant = f ;
        //this.switchVide();
    }
    
    public Flottant getFlottant() {
        return this.flottant;
    }

    public void supprimerNavire() {
        this.navire = null;
        this.switchVide();
    }

    public void supprimerFlottant() {
        this.flottant = null;
        //this.switchVide();
    }

    public String getTypeNavire() {
        if (this.navire != null) {
            return navire.getType();
        }
        return null;
    }

    public String getTypeFlottant() {
        if (this.flottant != null) {
            return flottant.getType();
        }
        return null;
    }
    public boolean estFlottant(){
        return this.getFlottant() != null ;
    }

    
    //    public void setRadioActif(boolean radioActif){
//        this.radioActif = radioActif;
//    }

    boolean estNavire() {
        return getNavire() != null ;
    }

    
     
}
