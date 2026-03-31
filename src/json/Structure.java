package json;

import java.util.List;

/**
 * Classe representant une structure sauvegardable avec un nom et une liste de coordonnees
 * @author LWilliam
 * @version 1.0
 */
public class Structure {
    private String nom;
    private List<int[]> cellules;

    /**
     * Constructeur d'une structure
     * @param nom le nom de la structure
     * @param cellules la liste des coordonnees des cellules vivantes
     */
    public Structure(String nom, List<int[]>cellules){
        this.nom = nom;
        this.cellules = cellules;
    }

    /**
     * Getter du nom de la structure
     * @return le nom de la structure
     */
    public String getNom(){return nom;}
    /**
     * Getter de la liste des coordonnees des cellules vivantes
     * @return la liste des coordonnees
     */
    public List<int[]> getCellules(){return cellules;}
}
