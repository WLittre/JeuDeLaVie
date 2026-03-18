package etat;

import cellule.*;
import visiteur.*;

public interface CelluleEtat {
    public abstract CelluleEtat vit();
    public abstract CelluleEtat meurt();
    public abstract boolean estVivante();
    public abstract void accepte(Visiteur v, Cellule c);
}
