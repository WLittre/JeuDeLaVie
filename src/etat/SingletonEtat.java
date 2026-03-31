package etat;

/**
 * Classe Singleton regroupant les deux instances uniques d'etat (vivant et mort)
 * @author LWilliam
 * @version 1.0
 */
public class SingletonEtat {
    private static final CelluleEtatVivant VIVANT = new CelluleEtatVivant();
    private static final CelluleEtatMort MORT = new CelluleEtatMort();

    private SingletonEtat() {}

    /**
     * Retourne l'instance unique de l'etat vivant
     * @return l'etat vivant
     */
    public static CelluleEtatVivant getVivant() {
        return VIVANT;
    }

    /**
     * Retourne l'instance unique de l'etat mort
     * @return l'etat mort
     */
    public static CelluleEtatMort getMort() {
        return MORT;
    }
}
