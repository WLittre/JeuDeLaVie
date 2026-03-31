package etat;

import cellule.*;
import visiteur.*;

/**
 * Interface definissant les etats possibles d'une cellule
 * @author LWilliam
 * @version 1.0
 */
public interface CelluleEtat {
    /**
     * Transition vers l'etat vivant
     * @return le nouvel etat de la cellule
     */
    public abstract CelluleEtat vit();
    /**
     * Transition vers l'etat mort
     * @return le nouvel etat de la cellule
     */
    public abstract CelluleEtat meurt();
    /**
     * Verifie si la cellule est vivante
     * @return true si la cellule est vivante, false sinon
     */
    public abstract boolean estVivante();
    /**
     * Accepte un visiteur et lui delegue le traitement selon l'etat
     * @param v le visiteur a accepter
     * @param c la cellule concernee
     */
    public abstract void accepte(Visiteur v, Cellule c);
}
