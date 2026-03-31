package visiteur;

import cellule.Cellule;
import commande.CommandeMeurt;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

/**
 * Visiteur implementant les regles HighLife (nait si 3 ou 6 voisines)
 * @author LWilliam
 * @version 1.0
 */
public class VisiteurHighLife extends Visiteur{
    /**
     * Constructeur du visiteur HighLife
     * @param jeu l'instance du jeu en cours
     */
    public VisiteurHighLife(JeuDeLaVie jeu){
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
        if(nb == 3 || nb == 6){
            jeu.ajouteCommandes(new CommandeVit(c));
        }
    }
}
