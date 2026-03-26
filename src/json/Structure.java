package json;

import java.util.List;

public class Structure {
    private String nom;
    private List<int[]> cellules;

    public Structure(String nom, List<int[]>cellules){
        this.nom = nom;
        this.cellules = cellules;
    }

    public String getNom(){return nom;}
    public List<int[]> getCellules(){return cellules;}
}
