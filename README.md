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
- `Visiteur` : Classe abstraite définissant les méthodes de visite d'une cellule vivante ou morte
- `VisiteurClassique` : Visiteur implémentant les règles classiques du Jeu de la Vie
- `VisiteurHighLife` : Visiteur implémentant les règles du HighLife (une cellule morte naît si elle a 3 ou 6 voisines vivantes)
- `VisiteurDayAndNight` : Visiteur implémentant les règles du Day&Night (une cellule vivante meurt si elle a moins de 3 ou plus de 6 voisines, une cellule morte naît si elle a 3, 6 ou 7 voisines)

## Dev
pre 18/03
Partie 1 - fait, ajout des Singleton pour chaque état

Partie 2 - Implémentation de la partie 2, initaliser la grille de jeu et de la classe `JeuDeLaVie`, utilisation de math.random() pour la probabilité qu'une cellule soit vivante et implémentation de la méthode nombreVoisinesVivantes dans `Cellules`.

Partie 3 - Implémentation du DP Observateur. Ajout des interfaces `Observable` et `Observateur`. `JeuDeLaVie` implémente `Observable` et gère une liste d'observateurs. `JeuDeLaVieUI` implémente `Observateur` et hérite de `JPanel` pour l'affichage graphique. La méthode `notifieObservateurs()` parcourt la liste et appelle `actualise()` sur chaque observateur, ce qui déclenche un `repaint()` de la grille. Ajout du `main` pour instancier le jeu, l'interface graphique et enregistrer l'observateur.

Partie 4 - Implémentation du DP Commande. Ajout de la classe abstraite `Commande` avec un attribut `Cellule` et une méthode abstraite `executer()`. Ajout de `CommandeVit` et `CommandeMeurt` qui héritent de `Commande` et appellent respectivement `cellule.vit()` et `cellule.meurt()`. `JeuDeLaVie` gère une liste de commandes avec `ajouteCommandes()` et `executeCommande()` qui parcourt et exécute toutes les commandes enregistrées. Ce pattern permet de différer l'application des règles du jeu : on calcule d'abord toutes les transitions nécessaires, puis on les exécute en une seule passe.

Partie 5 - Implémentation du DP Visiteur. Ajout de la classe abstraite `Visiteur` avec les méthodes `visiteCelluleVivante()` et `visiteCelluleMorte()`. `VisiteurClassique` implémente les règles classiques de Conway : une cellule vivante meurt si elle a moins de 2 ou plus de 3 voisines, une cellule morte naît si elle a exactement 3 voisines. La méthode `accepte()` dans `Cellule` délègue à l'état, qui appelle la bonne méthode du visiteur. `JeuDeLaVie` distribue le visiteur à toutes les cellules via `distribueVisiteur()`. La méthode `calculeGenerationSuivante()` enchaîne les 3 étapes : distribuer le visiteur, exécuter les commandes, notifier les observateurs. Ajout d'un `setVisiteur()` pour permettre le changement de règles à l'exécution.

23/03 - Debut Partie 6 - Ajout de visiteurs pour les variantes HighLife et Day&Night. Ajout d'un menu dans l'interface graphique pour sélectionner la règle à appliquer, qui change le visiteur utilisé par `JeuDeLaVie`. Test de chaque variante pour vérifier que les règles sont correctement appliquées.

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