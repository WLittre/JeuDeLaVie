# Jeu de la Vie

Implémentation du Jeu de la Vie de Conway en Java utilisant le **pattern État**.

## Structure

- `CelluleEtat` : Interface définissant les états possibles d'une cellule
- `CelluleEtatVivant` : État vivant
- `CelluleEtatMort` : État mort
- `Cellule` : Classe principale utilisant le pattern État

## Compilation

```bash
javac src/*.java