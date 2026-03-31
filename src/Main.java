import jeu.JeuDeLaVie;
import jeu.JeuDeLaVieUI;
import visiteur.VisiteurClassique;
import observateur.*;

/**
 * Classe principale du programme, instancie le jeu et les observateurs
 * @author LWilliam
 * @version 1.0
 */
public class Main {
    /**
     * Point d'entree du programme, cree le jeu, l'UI et la console puis lance la simulation
     * @param args arguments de la ligne de commande
     * @throws InterruptedException si le thread est interrompu
     */
    public static void main(String[] args) throws InterruptedException {
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
        ObservateurConsole console = new ObservateurConsole(jeu);
        jeu.attacheObservateur(ui);
        jeu.attacheObservateur(console);
        jeu.setVisiteur(new VisiteurClassique(jeu));
        jeu.notifieObservateurs();
    }
}
