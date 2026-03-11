public class Cellule{
    private CelluleEtat etat;

    public Cellule(CelluleEtat etat){
        this.etat = etat;
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
