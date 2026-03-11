public class CelluleEtatVivant implements CelluleEtat{
    @Override
    public CelluleEtat vit(){
        return this;
    }
    @Override
    public CelluleEtat meurt(){
        return SingletonEtat.getMort();
    }
    @Override
    public boolean estVivante(){
        return true;
    }
}
