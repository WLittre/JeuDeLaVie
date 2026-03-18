package etat;

import cellule.Cellule;
import visiteur.Visiteur;

public class CelluleEtatVivant implements CelluleEtat {
    @Override
    public CelluleEtat vit() {
        return this;
    }

    @Override
    public CelluleEtat meurt() {
        return SingletonEtat.getMort();
    }

    @Override
    public boolean estVivante() {
        return true;
    }

    @Override
    public void accepte(Visiteur v, Cellule c){
        v.visiteCelluleVivante(c);
    }
}
