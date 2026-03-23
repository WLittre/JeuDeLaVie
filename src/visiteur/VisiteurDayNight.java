package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

public class VisiteurDayNight extends Visiteur{
        public VisiteurDayNight(JeuDeLaVie jeu){
        super(jeu);
    }

    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb != 3 && nb != 4 && nb != 6 && nb != 7 && nb != 8){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 3 || nb == 6 || nb == 7 || nb == 8){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
