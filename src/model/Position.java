package model;

public class Position {

    private int x; //abscisse
    private int y; //ordonnée

    public Position(int x, int y) throws IllegalArgumentException {
        setX(x);
        setY(y);
    }

    public void setX(int x) {
        if (x >= -2 && x < MerBoard.getCote() + 2) { //permet de créer des position plus grande que la merBoard ,qui seron converti en position reelle dans MerBoard.getRealPosition)
            this.x = x;
        } else {
            throw new IllegalArgumentException("X n'est pas dans le range permis !");
        }
    }

    public void setY(int y) {
        if (y >= -2 && y < MerBoard.getCote() + 2) {
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
}
