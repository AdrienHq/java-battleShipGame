package model;

public interface Deplacement {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    default void deplaceHorizontal(int distance) {
        setX(getX() + distance);
    }

    default void deplaceVertical(int distance) {
        setY(getY() + distance);
    }

    default void déplacer(Direction d, int distance) {
        switch (d) {
            case HAUT:
                this.deplaceVertical(distance);
            case BAS:
                this.deplaceVertical(distance);
            case GAUCHE:
                this.deplaceHorizontal(distance);
            case DROITE:
                this.deplaceHorizontal(distance);
        }
    }
}
