package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

/**
 * Visiteur implementant les regles Plague (meurt si plus de 2 voisines, nait si 1 voisine)
 * @author LWilliam
 * @version 1.0
 */
public class VisiteurCovid extends Visiteur{
    /**
     * Constructeur du visiteur Plague
     * @param jeu l'instance du jeu en cours
     */
    public VisiteurCovid(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb > 2){
            jeu.ajouteCommandes(new CommandeMeurt(c));
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleMorte(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb == 1){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
