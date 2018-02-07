package model;

import java.util.List;

public class MerBoard {

    private final static int[][] tabMer = {
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0}
    };

    public static final int LIGNE = 5;
    public static final int COLONNE = 5;
    private Case[][] merCase;
    private List<Navire> listNavire;

}
