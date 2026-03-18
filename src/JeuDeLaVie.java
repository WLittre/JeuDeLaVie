import java.util.Random;

public class JeuDeLaVie {
    private Cellule grille[][];
    private int xMax;
    private int yMax;
    private double probaVie;
    public JeuDeLaVie(){
        probaVie = 0.5;
    }

    public void initializeGrille(){
        grille = new Cellule[xMax][yMax];
        for(int i = 0; i<xMax ; i++){
            for(int j = 0; j<yMax ; j++){
                if(Math.random()< probaVie){
                    grille[i][j].vit();
                }else{
                    grille[i][j].meurt();
                }
            }
        }
    }

    public Cellule getGrilleXY(int x,int y){
        return grille[x][y];
    }
    public int getxMax(){
        return xMax;
    }
    public int getyMax(){
        return yMax;
    }
}
