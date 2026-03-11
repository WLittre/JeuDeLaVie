public class CelluleEtatMort implements CelluleEtat{

    @Override
    public CelluleEtat vit(){
        return new CelluleEtatVivant();
    }
    @Override
    public CelluleEtat meurt(){
        return this;
    }
    @Override
    public boolean estVivante(){
        return true;
    }
    
}
