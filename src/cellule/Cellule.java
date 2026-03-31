package cellule;

import etat.CelluleEtat;
import jeu.JeuDeLaVie;
import visiteur.*;

/**
 * Classe principale representant une cellule du jeu, utilise le pattern Etat
 * @author LWilliam
 * @version 1.0
 */
public class Cellule {
    private CelluleEtat etat;
    private int x;
    private int y;

    /**
     * Initialise les céllules selon l'état et sa position dans la grille
     * @param x coordonnée x
     * @param y coordonnée y
     * @param etat en vie ou mort
     */
    public Cellule(int x, int y, CelluleEtat etat) {
        this.etat = etat;
        this.x = x;
        this.y = y;
    }

    /**
     * Calcule le nombre de cellules voisines vivantes vivantes 
     * @param jeu Instance de jeu en cours
     * @return nombre de cellules voisines vivantes
     */
    public int nombreVoisinesVivantes(JeuDeLaVie jeu) {
        int nbCellule = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i == x && j == y) continue;
                if (i >= 0 && i < jeu.getxMax() && j >= 0 && j < jeu.getyMax()) {
                    if (jeu.getGrilleXY(i, j).estVivante()) {
                        nbCellule++;
                    }
                }
            }
        }
        return nbCellule;
    }

    /**
     * Méthode check d'un visiteur
     * @param visiteur visiteur utilisé actuellement
     */
    public void accepte(Visiteur visiteur){
        etat.accepte(visiteur, this);
    }
    /**
     * permet de set l'état de la céllule a celui de vivant
     */
    public void vit() {
        etat = etat.vit();
    }

    /**
     * permet de set l'état de la céllule a celui de mort
     */
    public void meurt() {
        etat = etat.meurt();
    }

    /**
     * Méthode demandant si une cellule est vivante
     * @return boolean oui ou non
     */
    public boolean estVivante() {
        return etat.estVivante();
    }

    /**
     * getter de la position X
     * @return la position x
     */
    public int getX() {
        return x;
    }

    /**
     * getter de la position X
     * @return la position y
     */
    public int getY() {
        return y;
    }
}
