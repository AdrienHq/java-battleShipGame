package model;

import java.awt.Color;
import java.util.ArrayList;

public class Army {

    public String nom;

    public ArrayList<Navire> listNavire = new ArrayList<>();
    public Color color;

    public Army(String nom, Color color) {
        this.nom = nom;
        this.color = color; 
    }

    public boolean addNavire(Navire n) {
        listNavire.add(n);
        return true;
    }

    public boolean deleteNavire(Position pos) { //tir en 0 5 
        Navire n; 
        if (!listeVide()) {
            
            
            
            
            listNavire.remove(n.getPosition());
            return true;
        }
        
        
        
        return false; //VOIR SI LISTE SIZE SE REMET A JOUR OU NON      
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

}
