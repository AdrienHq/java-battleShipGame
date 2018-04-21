package model;

public class MerBuilder {

    private static MerBuilder instance = null;
    private static int cote;
    private Case[][] merBuilder;

    public static MerBuilder getInstance(int cote) {
        if (instance == null) {
            instance = new MerBuilder(cote);
            instance.initMerBuilder();
        }
        return instance;
    }

    private MerBuilder(int cote) {
        this.cote = cote;
        this.merBuilder = new Case[cote][cote];
        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (x + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(y + 1); //String du nom de la case (exemple : B1)
                Position p = new Position(x, y);
                merBuilder[x][y] = new Case(name, p);
            }
        }
    }

    private void initMerBuilder() { //Création et initialisation du tableau de case.
        merBuilder = new Case[cote][cote];

        for (int x = 0; x < cote; x++) {
            for (int y = 0; y < cote; y++) {
                char Col = (char) (y + 65);           //Valeur alphabétique de la colonne
                String name = Col + String.valueOf(x + 1); //String du nom de la case (exemple : B1)
                Position p = new Position(x, y);
                merBuilder[x][y] = new Case(name, p);
            }
        }
    }

}
