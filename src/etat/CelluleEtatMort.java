package etat;

import cellule.*;
import visiteur.*;

/**
 * Classe representant l'etat mort d'une cellule
 * @author LWilliam
 * @version 1.0
 */
public class CelluleEtatMort implements CelluleEtat {

    /**
     * {@inheritDoc}
     */
    @Override
    public CelluleEtat vit() {
        return SingletonEtat.getVivant();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CelluleEtat meurt() {
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean estVivante() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accepte(Visiteur v, Cellule c){
        v.visiteCelluleMorte(c);
    }
}
