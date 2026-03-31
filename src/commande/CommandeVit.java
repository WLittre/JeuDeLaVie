package commande;

import cellule.Cellule;

/**
 * Class concraite qui envois la commande lorsqu'une cellule doit mourir
 * @author Wlittre
 * @version 1.0
 */
public class CommandeVit extends Commande {
    /**
     * Initialisation d'une commande sur une cellule
     * @param c Une cellule
     */
    public CommandeVit(Cellule c) {
        cellule = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executer() {
        cellule.vit();
    }
}
