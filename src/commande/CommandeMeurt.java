package commande;

import cellule.Cellule;

/**
 * Class concraite qui envois la commande lorsqu'une cellule doit mourir
 * @author WLittre
 * @version 1.0
 */
public class CommandeMeurt extends Commande {
    /**
     * Initialisation d'une commande sur une cellule
     * @param c Une cellule
     */
    public CommandeMeurt(Cellule c) {
        cellule = c;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executer() {
        cellule.meurt();
    }
}
