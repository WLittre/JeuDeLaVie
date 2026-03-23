package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

public class VisiteurHighLife extends Visiteur{
    public VisiteurHighLife(JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb < 2 || nb > 3){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 3 || nb == 6){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
