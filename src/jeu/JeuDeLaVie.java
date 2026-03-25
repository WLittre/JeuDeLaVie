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
        this.xMax = 200;
        this.yMax = 200;
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
    }
}
