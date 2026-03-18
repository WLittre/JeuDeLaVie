public class Cellule{
    private CelluleEtat etat;
    private int x;
    private int y;
    public Cellule(int x, int y, CelluleEtat etat){
        this.etat = etat;
        this.x = x;
        this.y = y;
    }

    public int nombreVoisinesVivantes(JeuDeLaVie jeu){
        int nbCellule = 0;
        for(int i = x; i <= x + 1; i++){
            for(int j = y; j <= y + 1; j++){
                if (i == x && j == y) continue;
                if (i >= 0 && i < jeu.getxMax() && j >= 0 && j < jeu.getyMax()) {
                    if (jeu.getGrilleXY(i, j).estVivante()) {
                        nbCellule++;
                    }
                }
            }
        }
        return nbCellule;
    }
    public void vit(){
        etat = etat.vit();
    }
    public void meurt(){
        etat = etat.meurt(); 
    }
    public boolean estVivante(){
        return etat.estVivante();
    }
}
