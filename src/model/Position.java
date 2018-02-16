package model;

public class Position {

    private int x; //abscisse
    private int y; //ordonnée

    public Position(int x, int y) throws IllegalArgumentException {
        setX(x);
        setY(y);
    }

    private void setX(int x) {
        if (x >= 0 && x < MerBoard.COLONNE) {
            this.x = x;
        } else {
            throw new IllegalArgumentException("X n'est pas dans le range permis !");
        }
    }

    private void setY(int y) {
        if (y >= 0 && y < MerBoard.LIGNE) {
            this.y = y;
        } else {
            throw new IllegalArgumentException("Y n'est pas dans le range permis !");
        }
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean equals(Position pos) {
        return pos.getX() == this.x && pos.getY() == this.y;
    }

    @Override
    public String toString() {
        return "Abscisse X: " + this.x + "  Ordonnée Y: " + this.y;
    }

    public void déplacer(Direction d) {
        switch (d) {
            case HAUT:
                this.y--;
                break;
            case BAS:
                this.y++;
                break;
            case GAUCHE:
                this.x--;
                break;
            case DROITE:
                this.x++;
                break;
            case STOP:
                break;
        }
    }
    //RAJOUTER LES CAS OU LE DEPLACEMENT FAIT PLUS DE  1 CASE 

}
