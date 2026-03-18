import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JeuDeLaVie implements Observable{
    private Cellule grille[][];
    private int xMax;
    private int yMax;
    private double probaVie;

    private List<Observateur> observateurs;
    private List<Commande> commandes;
    public JeuDeLaVie(){
        probaVie = 0.2;
        this.xMax = 200;
        this.yMax = 200;
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();
        initializeGrille();
    }

    public void initializeGrille(){
        grille = new Cellule[xMax][yMax];
        for(int i = 0; i<xMax ; i++){
            for(int j = 0; j<yMax ; j++){
                if(Math.random()< probaVie){
                    grille[i][j] = new Cellule(i, j, SingletonEtat.getVivant());
                }else{
                    grille[i][j] = new Cellule(i, j, SingletonEtat.getMort());
                }
            }
        }
    }

    /*
    *
    *   OBSERVATEURS -------------------------------------
    * 
    */
    public void attacheObservateur(Observateur o){
        observateurs.add(o);
    }
    public void detacheObservateur(Observateur o){
        observateurs.remove(o);
    }

    public void notifieObservateurs(){
        for(Observateur o : observateurs){
            o.actualise();
        }
    }
    /*
    *
    *   COMMANDES -------------------------------------
    * 
    */
    public void ajouteCommandes(Commande c){
        commandes.add(c);
    }
    
    public void executeCommande(){
        for(Commande c : commandes){
            c.executer();
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
