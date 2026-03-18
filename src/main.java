public class main {
    public static void main(String[] args) {
        JeuDeLaVie jeu = new JeuDeLaVie();
        JeuDeLaVieUI ui = new JeuDeLaVieUI(jeu);
        jeu.attacheObservateur(ui);
        jeu.notifieObservateurs();
    }
}
