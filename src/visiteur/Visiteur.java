package visiteur;

import jeu.*;
import cellule.*;

/**
 * Classe abstraite definissant les methodes de visite d'une cellule vivante ou morte
 * @author LWilliam
 * @version 1.0
 */
public abstract class Visiteur {
    protected JeuDeLaVie jeu;
    /**
     * Constructeur du visiteur avec une reference au jeu
     * @param jeu l'instance du jeu en cours
     */
    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    /**
     * Visite une cellule vivante et applique les regles du jeu
     * @param c la cellule vivante a visiter
     */
    public abstract void visiteCelluleVivante(Cellule c);
    /**
     * Visite une cellule morte et applique les regles du jeu
     * @param c la cellule morte a visiter
     */
    public abstract void visiteCelluleMorte(Cellule c);

}
