package model;

public class Element extends Case {

    private Navire navire;
    private Flottant flottant;

    public Element(Navire n) {
        this.navire = n;
    }

    public Element(Flottant f) {
        this.flottant = f;
    }

    public Element() {
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

    public TypeNavire getTypeNavire() {
        if (this.navire != null) {
            return navire.getType();
        }
        return null;
    }

    public TypeFlottant getTypeFlottant() {
        if (this.flottant != null) {
            return flottant.getType();
        }
        return null;
    }

    @Override
    public boolean estRadioactif() {
        return false;
    }

    @Override
    public boolean estVide() {
        if()
    }

}
