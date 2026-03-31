package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

/**
 * Visiteur implementant les regles classiques du Jeu de la Vie de Conway
 * @author LWilliam
 * @version 1.0
 */
public class VisiteurClassique extends Visiteur{

    /**
     * Constructeur du visiteur classique
     * @param jeu l'instance du jeu en cours
     */
    public VisiteurClassique(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb < 2 || nb > 3){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 3){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
    
}
