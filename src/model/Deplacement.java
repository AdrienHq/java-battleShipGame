package model;

public interface Deplacement {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    void deplaceHorizontal(int distance);

    void deplaceVertical(int distance);

    void déplacer(Direction d);
}
