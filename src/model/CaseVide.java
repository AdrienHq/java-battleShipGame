package model;

import java.util.Objects;

public class CaseVide {

    private String nom = " ";

    public CaseVide() {

    }
    
    public CaseVide(CaseVide casevide){
        this.nom = casevide.getNom();
    }

    public String getNom() {
        return this.nom;
    }
    
    public String getElement() {
        return this.nom;

    }

    @Override
    public boolean equals(Object o) {

        if (o instanceof CaseVide) {
            CaseVide v = (CaseVide) o;
            return this.nom.equals(v.getNom());
        }
        return false;

    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 73 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public String toString() {
        return this.nom;
    }
}
