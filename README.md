# Jeu de la Vie

Implémentation du Jeu de la Vie de Conway en Java utilisant le **pattern État**.

## Structure

- `CelluleEtat` : Interface définissant les états possibles d'une cellule
- `CelluleEtatVivant` : État vivant
- `CelluleEtatMort` : État mort
- `Cellule` : Classe principale utilisant le pattern État
- `SingletonEtat` : Classe secondaire regroupant les deux                 instances d'état
- `JeuDeLaVie` : Classe principale ou se situe la grille de jeu et son instanciation.
- `Observable` : Interface définissant les méthodes d'un observable
- `Observateur` : Interface définissant les méthodes d'un observateur
- `JeuDeLaVieUI` : Interface graphique du jeu, observateur de `JeuDeLaVie`
- `Commande` : Classe abstraite définissant une commande à exécuter sur une cellule
- `CommandeVit` : Commande qui fait vivre une cellule
- `CommandeMeurt` : Commande qui fait mourir une cellule

## Dev
pre 18/03
Partie 1 - fait, ajout des Singleton pour chaque état

Partie 2 - Implémentation de la partie 2, initaliser la grille de jeu et de la classe `JeuDeLaVie`, utilisation de math.random() pour la probabilité qu'une cellule soit vivante et implémentation de la méthode nombreVoisinesVivantes dans `Cellules`.

Partie 3 - Implémentation du DP Observateur. Ajout des interfaces `Observable` et `Observateur`. `JeuDeLaVie` implémente `Observable` et gère une liste d'observateurs. `JeuDeLaVieUI` implémente `Observateur` et hérite de `JPanel` pour l'affichage graphique. La méthode `notifieObservateurs()` parcourt la liste et appelle `actualise()` sur chaque observateur, ce qui déclenche un `repaint()` de la grille. Ajout du `main` pour instancier le jeu, l'interface graphique et enregistrer l'observateur.

Partie 4 - Implémentation du DP Commande. Ajout de la classe abstraite `Commande` avec un attribut `Cellule` et une méthode abstraite `executer()`. Ajout de `CommandeVit` et `CommandeMeurt` qui héritent de `Commande` et appellent respectivement `cellule.vit()` et `cellule.meurt()`. `JeuDeLaVie` gère une liste de commandes avec `ajouteCommandes()` et `executeCommande()` qui parcourt et exécute toutes les commandes enregistrées. Ce pattern permet de différer l'application des règles du jeu : on calcule d'abord toutes les transitions nécessaires, puis on les exécute en une seule passe.

## Compilation
```bash
make
```

## Exécution
```bash
make run
```

## Nettoyage
```bash
make clean
```