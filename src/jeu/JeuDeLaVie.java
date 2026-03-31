package jeu;

import java.util.ArrayList;
import java.util.List;

import cellule.*;
import commande.*;
import etat.*;
import observateur.*;
import visiteur.*;


/**
 * Classe principale ou se situe la grille de jeu et son instanciation
 * @author LWilliam
 * @version 1.0
 */
public class JeuDeLaVie implements Observable {
    private Cellule grille[][];
    private int xMax;
    private int yMax;
    private double probaVie;

    private List<Observateur> observateurs;
    private List<Commande> commandes;

    private Visiteur visiteur;
    
    /**
     * Constructeur par defaut, initialise une grille 100x100 avec une probabilite de vie de 0.2
     */
    public JeuDeLaVie() {
        probaVie = 0.2;
        this.xMax = 100;
        this.yMax = 100;
        observateurs = new ArrayList<>();
        commandes = new ArrayList<>();
        initializeGrille();
    }

    /**
     * Initialise la grille avec des cellules vivantes ou mortes selon la probabilite de vie
     */
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

    /**
     * Initialise la grille avec toutes les cellules mortes
     */
    public void initializeGrilleVide(){
        grille = new Cellule[xMax][yMax];
        for (int i = 0; i < xMax; i++) {
            for (int j = 0; j < yMax; j++) {
                grille[i][j] = new Cellule(i, j, SingletonEtat.getMort());
            }
        }
    }

    /**
     * Initialise la grille vide puis place une structure centree
     * @param cellules liste des coordonnees des cellules vivantes de la structure
     */
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
    /**
     * {@inheritDoc}
     */
    @Override
    public void attacheObservateur(Observateur o) {
        observateurs.add(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void detacheObservateur(Observateur o) {
        observateurs.remove(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void notifieObservateurs() {
        for (Observateur o : observateurs) {
            o.actualise();
        }
    }

    /*
     * COMMANDES -------------------------------------
     */
    /**
     * Ajoute une commande a la liste des commandes a executer
     * @param c la commande a ajouter
     */
    public void ajouteCommandes(Commande c) {
        commandes.add(c);
    }

    /**
     * Execute toutes les commandes enregistrees
     */
    public void executeCommande() {
        for (Commande c : commandes) {
            c.executer();
        }
    }

    /*
     * Visiteur -------------------------------------
     */
    /**
     * Distribue le visiteur a toutes les cellules de la grille
     */
    public void distribueVisiteur(){
        for(int i = 0; i < xMax; i++){
            for(int j = 0; j < yMax; j++){
                grille[i][j].accepte(visiteur);
            }
        }
    }

    /**
     * Calcule la generation suivante : distribue le visiteur, execute les commandes et notifie les observateurs
     */
    public void calculeGenerationSuivante(){
        this.distribueVisiteur();
        this.executeCommande();      
        commandes.clear();           
        this.notifieObservateurs();
    }
    /**
     * Retourne la cellule a la position (x, y) de la grille
     * @param x coordonnee x
     * @param y coordonnee y
     * @return la cellule a la position donnee
     */
    public Cellule getGrilleXY(int x, int y) {
        return grille[x][y];
    }

    /**
     * Getter de la taille maximale en x de la grille
     * @return la taille en x
     */
    public int getxMax() {
        return xMax;
    }

    /**
     * Getter de la taille maximale en y de la grille
     * @return la taille en y
     */
    public int getyMax() {
        return yMax;
    }
    /**
     * Setter de la probabilite de vie d'une cellule a l'initialisation
     * @param p la probabilite entre 0 et 1
     */
    public void setProba(double p){
        probaVie = p;
    }

    /**
     * Setter de la taille maximale en x de la grille
     * @param xMax la nouvelle taille en x
     */
    public void setxMax(int xMax){
        this.xMax = xMax;
    }
    /**
     * Setter de la taille maximale en y de la grille
     * @param yMax la nouvelle taille en y
     */
    public void setyMax(int yMax){
        this.yMax = yMax;
    }
    /**
     * Getter de la probabilite de vie
     * @return la probabilite de vie
     */
    public double getProba(){
        return probaVie;
    }
    /**
     * Setter du visiteur utilise pour le calcul des regles du jeu
     * @param v le visiteur a utiliser
     */
    public void setVisiteur(Visiteur v) {
        this.visiteur = v;
        System.out.println("Visiteur changé : " + v.getClass().getSimpleName());
    }

    /* Gestion des structures */
    /**
     * Extrait les cellules vivantes d'une zone rectangulaire de la grille
     * @param x1 coordonnee x du premier coin
     * @param y1 coordonnee y du premier coin
     * @param x2 coordonnee x du deuxieme coin
     * @param y2 coordonnee y du deuxieme coin
     * @return la liste des coordonnees relatives des cellules vivantes
     */
    public List<int[]> extractStructure(int x1,int y1,int x2,int y2){
        int minX = Math.min(x1, x2), minY = Math.min(y1, y2);
        int maxX = Math.max(x1, x2), maxY = Math.max(y1, y2);
        List<int[]> cellules = new ArrayList<>();
        for (int i = minX; i <= maxX; i++){
            for (int j = minY; j <= maxY; j++) {
                if (i >= 0 && i < xMax && j >= 0 && j < yMax && grille[i][j].estVivante()){
                    cellules.add(new int[]{i - minX, j - minY});
                }
            }
        }
        return cellules;
    }

    /**
     * Place une structure sur la grille a la position donnee
     * @param cellules liste des coordonnees relatives des cellules vivantes
     * @param px position x de placement
     * @param py position y de placement
     */
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
