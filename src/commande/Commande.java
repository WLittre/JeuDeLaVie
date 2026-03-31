package commande;

import cellule.Cellule;

/**
 * Classe abstraite des commandes
 * @author LWilliam
 * @version 1.0
 */
public abstract class Commande {
    protected Cellule cellule;

    /**
     * méthode abstraite qui exécute une autre selon l'état de la céllule
     */
    public abstract void executer();
}
