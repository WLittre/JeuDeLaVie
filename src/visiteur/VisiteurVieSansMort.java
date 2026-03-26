package visiteur;

import cellule.Cellule;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

public class VisiteurVieSansMort extends Visiteur{
     public VisiteurVieSansMort(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb < 2 || nb > 3){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 3){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
