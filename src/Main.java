import jeu.JeuDeLaVie;
import jeu.JeuDeLaVieUI;
import visiteur.VisiteurClassique;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
        jeu.attacheObservateur(ui);
        jeu.setVisiteur(new VisiteurClassique(jeu));
        jeu.notifieObservateurs();

        while (true) {
            Thread.sleep(100);
            jeu.calculeGenerationSuivante();
        }
    }
}
