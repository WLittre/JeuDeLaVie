package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

/**
 * Visiteur implementant les regles Day and Night
 * @author LWilliam
 * @version 1.0
 */
public class VisiteurDayNight extends Visiteur{
        /**
     * Constructeur du visiteur Day and Night
     * @param jeu l'instance du jeu en cours
     */
        public VisiteurDayNight(JeuDeLaVie jeu){
        super(jeu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb != 3 && nb != 4 && nb != 6 && nb != 7 && nb != 8){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 3 || nb == 6 || nb == 7 || nb == 8){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
