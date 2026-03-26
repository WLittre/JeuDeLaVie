package jeu;

import java.util.ArrayList;
import java.util.List;

import cellule.*;
import commande.*;
import etat.*;
import observateur.*;
import visiteur.*;


public class JeuDeLaVie implements Observable {
    private Cellule grille[][];
    private int xMax;
    private int yMax;
    private double probaVie;

    private List<Observateur> observateurs;
    private List<Commande> commandes;

    private Visiteur visiteur;
    
    public JeuDeLaVie() {
        probaVie = 0.2;
        this.xMax = 100;
        this.yMax = 100;
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();
        initializeGrille();
    }

    public void initializeGrille() {
        grille = new Cellule[xMax][yMax];
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                if (Math.random() < probaVie) {
                    grille[i][j] = new Cellule(i, j, SingletonEtat.getVivant());
                } else {
                    grille[i][j] = new Cellule(i, j, SingletonEtat.getMort());
                }
            }
        }
    }

    public void initializeGrilleVide(){
        grille = new Cellule[xMax][yMax];
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                grille[i][j] = new Cellule(i, j, SingletonEtat.getMort());
            }
        }
    }

    public void initializeGrilleStrucutre(List<int[]> cellules){
        initializeGrilleVide();
        int maxCx = 0;
        int maxCy = 0;
        for(int[] c : cellules){
            if(c[0]>maxCx)maxCx = c[0];
            if(c[1]>maxCy)maxCy = c[1];
        }
        int offsetX = xMax/2 - maxCx/2;
        int offsetY = yMax/2 - maxCy/2;
        for(int[] c : cellules){
            int x = offsetX + c[0];
            int y = offsetY + c[1];
            if(x>=0 && x < xMax && y>=0 && y < yMax){
                grille[x][y].vit();
            }
        }
    }
    /*
     * OBSERVATEURS -------------------------------------
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    @Override
    public void notifieObservateurs() {
        for (Observateur o : observateurs) {
            o.actualise();
        }
    }

    /*
     * COMMANDES -------------------------------------
     */
    public void ajouteCommandes(Commande c) {
        commandes.add(c);
    }

    public void executeCommande() {
        for (Commande c : commandes) {
            c.executer();
        }
    }

    /*
     * Visiteur -------------------------------------
     */
    public void distribueVisiteur(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(visiteur);
            }
        }
    }

    public void calculeGenerationSuivante(){
        this.distribueVisiteur();
        this.executeCommande();      
        commandes.clear();           
        this.notifieObservateurs();
    }
    public Cellule getGrilleXY(int x, int y) {
        return grille[x][y];
    }

    public int getxMax() {
        return xMax;
    }

    public int getyMax() {
        return yMax;
    }
    public void setProba(double p){
        probaVie = p;
    }

    public void setxMax(int xMax){
        this.xMax = xMax;
    }
    public void setyMax(int yMax){
        this.yMax = yMax;
    }
    public double getProba(){
        return probaVie;
    }
    public void setVisiteur(Visiteur v) {
        this.visiteur = v;
        System.out.println("Visiteur changé : " + v.getClass().getSimpleName());
    }

    /* Gestion des structures */
    public List<int[]> extractStructure(int x1,int y1,int x2,int y2){
        int minX = Math.min(x1, x2), minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2), maxY = Math.max(y1, y2);
        List<int[]> cellules = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                if (i >= 0 && i < xMax && j >= 0 && j < yMax && grille[i][j].estVivante()) {
                    cellules.add(new int[]{i - minX, j - minY});
                }
            }
        }
        return cellules;
    }

    public void placeStructure(List<int[]>cellules,int px,int py){
        for(int[] c : cellules){
            int x = px + c[0];
            int y = py + c[1];
            if( x>=0 && x < xMax && y >= 0 && y < yMax ){
                grille[x][y].vit();
            }
        }
        notifieObservateurs();
    }
}
