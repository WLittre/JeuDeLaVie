package etat;

import cellule.*;
import visiteur.*;

public class CelluleEtatMort implements CelluleEtat {

    @Override
    public CelluleEtat vit() {
        return SingletonEtat.getVivant();
    }

    @Override
    public CelluleEtat meurt() {
        return this;
    }

    @Override
    public boolean estVivante() {
        return false;
    }

    @Override
    public void accepte(Visiteur v, Cellule c){
        v.visiteCelluleMorte(c);
    }
}
