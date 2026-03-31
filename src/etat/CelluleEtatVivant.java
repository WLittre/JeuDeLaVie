package etat;

import cellule.Cellule;
import visiteur.Visiteur;

/**
 * Classe representant l'etat vivant d'une cellule
 * @author LWilliam
 * @version 1.0
 */
public class CelluleEtatVivant implements CelluleEtat {
    /**
     * {@inheritDoc}
     */
    @Override
    public CelluleEtat vit() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CelluleEtat meurt() {
        return SingletonEtat.getMort();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean estVivante() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accepte(Visiteur v, Cellule c){
        v.visiteCelluleVivante(c);
    }
}
