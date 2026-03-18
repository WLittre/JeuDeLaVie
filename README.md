# Jeu de la Vie

Implémentation du Jeu de la Vie de Conway en Java utilisant le **pattern État**.

## Structure

- `CelluleEtat` : Interface définissant les états possibles d'une cellule
- `CelluleEtatVivant` : État vivant
- `CelluleEtatMort` : État mort
- `Cellule` : Classe principale utilisant le pattern État
- `SingletonEtat` : Classe secondaire regroupant les deux                 instances d'état
- `JeuDeLaVie` : Classe principale ou se situe la grille de jeu et son instanciation.
## Dev
pre 18/03
Partie 1 - fait, ajout des Singleton pour chaque état

Partie 2 - Implémentation de la partie 2, initaliser la grille de jeu et de la classe `JeuDeLaVie`, utilisation de math.random() pour la probabilité qu'une cellule soit vivante et implémentation de la méthode nombreVoisinesVivantes dans `Cellules`.

## Compilation

```bash
javac src/*.java