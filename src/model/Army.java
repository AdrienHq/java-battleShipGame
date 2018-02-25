package model;

import java.awt.Color;
import java.util.ArrayList;

public class Army {

    public String nom;
    public ArrayList<Navire> listNavire = new ArrayList<>();

    public Army(String nom) {
        this.nom = nom;
        this.listNavire = fillNavires();
    }

    public ArrayList<Navire> fillNavires() {
        listNavire.add(new BateauGrand());
        listNavire.add(new BateauPetit());
        listNavire.add(new BateauPetit());
        return listNavire;
    }

    public ArrayList<Navire> getListeNavire() {
        return listNavire;
    }

    public boolean addNavire(Navire n) {
        listNavire.add(n);
        return true;
    }

    public void deleteNavire(Navire n) { //tir en 0 5 
        if (!listeVide()) {
            listNavire.remove(n);
        }
    }

    public boolean listeVide() {
        if (sizeListe() == 0) {
            return true;
        }
        return false;
    }

    public int sizeListe() {
        return listNavire.size();
    }

    public String getNom() {
        return this.nom;
    }

    public boolean estAmi(Navire nav) {
        if (nav.getNom() == (this.nom)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Army) {
            Army army = (Army) o;
            return (army.nom == this.nom);
        }
        return false;
    }
}
