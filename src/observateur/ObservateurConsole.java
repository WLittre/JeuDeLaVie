package observateur;
import jeu.JeuDeLaVie;

/**
 * Observateur console qui affiche le numero de generation et le nombre de cellules vivantes
 * @author LWilliam
 * @version 1.0
 */
public class ObservateurConsole implements Observateur{
    private int generation;
    private int celluleVivante;
    private JeuDeLaVie jeu;

    /**
     * Constructeur de l'observateur console
     * @param jeu l'instance du jeu a observer
     */
    public ObservateurConsole(JeuDeLaVie jeu){
        this.jeu = jeu;
        generation = 0;
    }

    /**
     * {@inheritDoc}
     */
    public void actualise(){
        celluleVivante = 0;
        generation++;
        for(int x = 0; x<jeu.getxMax();x++){
            for(int y = 0; y<jeu.getyMax();y++){
                if(jeu.getGrilleXY(x,y).estVivante()){
                    celluleVivante++;
                }
            }
        }
        System.out.println("Generation: " + generation + "Cellule vivante : " + celluleVivante);
    }
}