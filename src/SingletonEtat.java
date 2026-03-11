public class SingletonEtat {
    private static final CelluleEtatVivant VIVANT = new CelluleEtatVivant();
    private static final CelluleEtatMort MORT = new CelluleEtatMort();

    public static CelluleEtatVivant getVivant(){
        return VIVANT;
    }
    public static CelluleEtatMort getMort(){
        return MORT;
    }
}
