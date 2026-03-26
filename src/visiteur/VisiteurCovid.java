package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

public class VisiteurCovid extends Visiteur{
    public VisiteurCovid(JeuDeLaVie jeu) {
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb > 2){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 1){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
