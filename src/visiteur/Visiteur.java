package visiteur;

import jeu.*;
import cellule.*;

public abstract class Visiteur {
    protected JeuDeLaVie jeu;
    public Visiteur(JeuDeLaVie jeu){
        this.jeu = jeu;
    }

    public abstract void visiteCelluleVivante(Cellule c);
    public abstract void visiteCelluleMorte(Cellule c);

}
