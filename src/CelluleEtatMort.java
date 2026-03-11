public class CelluleEtatMort implements CelluleEtat{

    @Override
    public CelluleEtat vit(){
        return SingletonEtat.getVivant();
    }
    @Override
    public CelluleEtat meurt(){
        return this;
    }
    @Override
    public boolean estVivante(){
        return false;
    }
    
}
