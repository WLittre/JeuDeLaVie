public class CelluleEtatVivant implements CelluleEtat{
    @Override
    public CelluleEtat vit(){
        return this;
    }
    @Override
    public CelluleEtat meurt(){
        return new CelluleEtatMort();
    }
    @Override
    public boolean estVivante(){
        return false;
    }
}
