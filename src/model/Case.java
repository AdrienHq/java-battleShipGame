package model;

import java.util.Objects;

public class Case {

    private Army army;
    private Flottant flottant;

    public Case(Army army) {
        this.army = army; 
    }

    public Case(Flottant f) {
        this.flottant = f;
    }

    public Case() {
        this.army = null;
        this.flottant = null;
    }

    public Army getArmy() {
        return this.army;
    }

    public Flottant getFlottant() {
        return this.flottant;
    }

    public void supprimerFlottant() {
        this.flottant = null;
    }


    public String getTypeFlottant() {
        if (this.flottant != null) {
            return flottant.getType();
        }
        return null;
    }

    Boolean radioActif = false;
    Boolean estVide = true;

    //    public void setRadioActif(boolean radioActif){
//        this.radioActif = radioActif;
//    }
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
}
