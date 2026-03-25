import jeu.JeuDeLaVie;
import jeu.JeuDeLaVieUI;
import visiteur.VisiteurClassique;
import observateur.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
        ObservateurConsole console = new ObservateurConsole(jeu);
        jeu.attacheObservateur(ui);
        jeu.setVisiteur(new VisiteurClassique(jeu));
        jeu.notifieObservateurs();
    }
}
