package model;

import java.util.ArrayList;

public class Army {

    private final String nom;
    private ArrayList<Navire> listNavire = new ArrayList<>();
    private ArrayList<Navire> listNavireToDelete = new ArrayList<>();
    
    public Army(String nom) {
        this.nom = nom;
        this.listNavire = fillNavires();
    }

    public ArrayList<Navire> fillNavires() {
        listNavire.add(new BateauGrand(nom));
        listNavire.add(new BateauPetit(nom));
        listNavire.add(new BateauPetit(nom));
        return listNavire;
    }

    public ArrayList<Navire> getListeNavire() {
        return listNavire;
    }

    public boolean addNavire(Navire n) {
        listNavire.add(n);
        return true;
    }

    public void tirDegat(Navire n) {
        n.degat(50);
    }
    
    public void deleteNavire() { 
        for (Navire x : listNavire) {
            if (x.pointVie == 0) {
                listNavireToDelete.add(x);
            }
        }
        for (Navire x : listNavireToDelete) {
            listNavire.remove(x);
        }
    }

    public boolean listeVide() {
        if (sizeListe() == 0) {
            return true;
        }
        return false;
    }

    private int sizeListe() {
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
