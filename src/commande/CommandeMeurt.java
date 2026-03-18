package commande;

import cellule.Cellule;

public class CommandeMeurt extends Commande {
    public CommandeMeurt(Cellule c) {
        cellule = c;
    }

    @Override
    public void executer() {
        cellule.meurt();
    }
}
