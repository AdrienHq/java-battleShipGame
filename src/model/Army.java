package model;

import java.util.ArrayList;

public class Army {

    public String nom;
    public ArrayList<Navire> listNavire = new ArrayList<>();

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
//        if(n.pointVie == 0){
//            this.deleteNavire(n);
//        }

//        Navire temp = n ;
//        this.deleteNavire(n);
//        temp.tirDegat();
//        listNavire.add(temp);
//        if(n.pointVie == 0){
//            this.deleteNavire(n);
//        }
    }

    public void deleteNavire() { //tir en 0 5 
        for (Navire x : listNavire) {
            if (x.pointVie == 0) {
                listNavire.remove(x);
            }
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
