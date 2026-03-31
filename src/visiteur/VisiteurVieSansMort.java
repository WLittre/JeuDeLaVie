package visiteur;

import cellule.Cellule;
import commande.CommandeVit;
import jeu.JeuDeLaVie;

/**
 * Visiteur implementant les regles Immortel (les cellules ne meurent jamais)
 * @author LWilliam
 * @version 1.0
 */
public class VisiteurVieSansMort extends Visiteur{
     /**
     * Constructeur du visiteur Immortel
     * @param jeu l'instance du jeu en cours
     */
     public VisiteurVieSansMort(JeuDeLaVie jeu) {
        super(jeu);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void visiteCelluleVivante(Cellule c) {
        int nb = c.nombreVoisinesVivantes(jeu);
        if(nb < 2 || nb > 3){
            jeu.ajouteCommandes(new CommandeVit(c));
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
