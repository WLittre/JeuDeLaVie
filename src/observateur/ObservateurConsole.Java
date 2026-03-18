public class ObservateurConsole implements Observateur{
    private int generation;
    private int celluleVivante;
    private JeuDeLaVie jeu;

    public ObservateurConsole(JeuDeLaVie jeu){
        this.jeu = jeu;
        generation = 0;
    }

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
        System.out.println("Generation : " + generation + "Nombre de cellule : " + celluleVivante);
    }
}