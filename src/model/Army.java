package model;

import java.util.ArrayList;

public class Army {
    //nom - nbre bateau dans une liste - scor ? 
    
    // id joueur

    private String nomJoueurArmee;
    private ArrayList<Navire> listNavire = new ArrayList<>();

    public Army(String nomJoueurArmee) {
        this.nomJoueurArmee = nomJoueurArmee;
    }

    public void setNomArmee(String nomJoueurArmee) {
        this.nomJoueurArmee = nomJoueurArmee;
    }

    public String getNomArmee() {
        return this.nomJoueurArmee;
    }

    public boolean addNavire(Navire e) {
        listNavire.add(e);
        return true;
    }

    public void getListTypeNavire() {
        for (Navire n : this.listNavire) {
            System.out.println(n.type);
        }
    }

    public void getPositionNavire() {
        for (Navire n : this.listNavire) {
            System.out.println(n.getPosition());
        }
    }

    public void getVieNavire() {
        for (Navire n : this.listNavire) {
            System.out.println(n.getPointVie());
        }
    }

//        System.out.println("Etat des armées");
//        System.out.println("Position -  Armée - Type - Intégrité (%)");
//        
//        for(Navire n : this.listNavire){
//            getPositionNavire();
//            System.out.println(this.nomJoueurArmee);
//            getListTypeNavire();
//            getVieNavire();
//        }
    

}
