package observateur;

/**
 * Interface definissant les methodes d'un observable
 * @author LWilliam
 * @version 1.0
 */
public interface Observable {
    /**
     * Attache un observateur a la liste des observateurs
     * @param o l'observateur a attacher
     */
    public void attacheObservateur(Observateur o);
    /**
     * Detache un observateur de la liste des observateurs
     * @param o l'observateur a detacher
     */
    public void detacheObservateur(Observateur o);
    /**
     * Notifie tous les observateurs attaches
     */
    public void notifieObservateurs();
}
