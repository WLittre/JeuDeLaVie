package observateur;

/**
 * Interface definissant les methodes d'un observateur
 * @author LWilliam
 * @version 1.0
 */
public interface Observateur {
    /**
     * Methode appelee par l'observable pour actualiser l'observateur
     */
    public void actualise();
}
