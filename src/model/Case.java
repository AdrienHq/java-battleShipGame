package model;

import java.util.Objects;

public class Case {
    
    private String name ;
    
    private Navire navire;
    private Flottant flottant;
    
    Boolean radioActif = false;
    Boolean estVide = true;

    public boolean estRadioactif() {
        return radioActif;
    }

    public boolean estVide() {
        return estVide;
    }

    public void switchVide() {
        if (this.estVide = false) {
            this.estVide = true;
        } else {
            this.estVide = false;
        }
    }

    public void switchRadioactif() {
        this.radioActif = this.radioActif = false;
    } 

    public Case(Navire n) {
        this.navire = n;
    }

    public Case(Flottant f) {
        this.flottant = f;
    }

    public Case(String name) {
        this.name = name ;
        this.navire = null;
        this.flottant = null;
    }

    public Navire getNavire() {
        return this.navire;
    }

    public Flottant getFlottant() {
        return this.flottant;
    }

    public void supprimerNavire() {
        this.navire = null;
    }

    public void supprimerFlottant() {
        this.flottant = null;
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

    
    //    public void setRadioActif(boolean radioActif){
//        this.radioActif = radioActif;
//    }
     
}
