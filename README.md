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
- `VisiteurDayAndNight` : Visiteur implementant les regles du Day&Night (une cellule vivante meurt si elle a moins de 3 ou plus de 6 voisines, une cellule morte nait si elle a 3, 6 ou 7 voisines)
- `VisiteurVieSansMort` : Visiteur implementant les regles Immortel (les cellules ne meurent jamais, une cellule morte nait si elle a 3 voisines)
- `VisiteurCovid` : Visiteur implementant les regles Plague (une cellule vivante meurt si elle a plus de 2 voisines, une cellule morte nait si elle a exactement 1 voisine)
- `ObservateurConsole` : Observateur console qui affiche le numero de generation et le nombre de cellules vivantes a chaque actualisation
- `Structure` : Classe representant une structure sauvegardable avec un nom et une liste de coordonnees de cellules
- `Gestionstructure` : Classe gerant le chargement et la sauvegarde des structures dans un fichier texte

## Dev
pre 18/03
Partie 1 - fait, ajout des Singleton pour chaque état

Partie 2 - Implémentation de la partie 2, initaliser la grille de jeu et de la classe `JeuDeLaVie`, utilisation de math.random() pour la probabilité qu'une cellule soit vivante et implémentation de la méthode nombreVoisinesVivantes dans `Cellules`.

Partie 3 - Implémentation du DP Observateur. Ajout des interfaces `Observable` et `Observateur`. `JeuDeLaVie` implémente `Observable` et gère une liste d'observateurs. `JeuDeLaVieUI` implémente `Observateur` et hérite de `JPanel` pour l'affichage graphique. La méthode `notifieObservateurs()` parcourt la liste et appelle `actualise()` sur chaque observateur, ce qui déclenche un `repaint()` de la grille. Ajout du `main` pour instancier le jeu, l'interface graphique et enregistrer l'observateur.

Partie 4 - Implémentation du DP Commande. Ajout de la classe abstraite `Commande` avec un attribut `Cellule` et une méthode abstraite `executer()`. Ajout de `CommandeVit` et `CommandeMeurt` qui héritent de `Commande` et appellent respectivement `cellule.vit()` et `cellule.meurt()`. `JeuDeLaVie` gère une liste de commandes avec `ajouteCommandes()` et `executeCommande()` qui parcourt et exécute toutes les commandes enregistrées. Ce pattern permet de différer l'application des règles du jeu : on calcule d'abord toutes les transitions nécessaires, puis on les exécute en une seule passe.

Partie 5 - Implémentation du DP Visiteur. Ajout de la classe abstraite `Visiteur` avec les méthodes `visiteCelluleVivante()` et `visiteCelluleMorte()`. `VisiteurClassique` implémente les règles classiques de Conway : une cellule vivante meurt si elle a moins de 2 ou plus de 3 voisines, une cellule morte naît si elle a exactement 3 voisines. La méthode `accepte()` dans `Cellule` délègue à l'état, qui appelle la bonne méthode du visiteur. `JeuDeLaVie` distribue le visiteur à toutes les cellules via `distribueVisiteur()`. La méthode `calculeGenerationSuivante()` enchaîne les 3 étapes : distribuer le visiteur, exécuter les commandes, notifier les observateurs. Ajout d'un `setVisiteur()` pour permettre le changement de règles à l'exécution.

23/03 - Debut Partie 6 - Ajout de visiteurs pour les variantes HighLife et Day&Night. Ajout d'un menu dans l'interface graphique pour sélectionner la règle à appliquer, qui change le visiteur utilisé par `JeuDeLaVie`. Test de chaque variante pour vérifier que les règles sont correctement appliquées.

Suite Partie 6 - Ajout de deux nouveaux visiteurs : `VisiteurVieSansMort` (mode Immortel) ou les cellules ne meurent jamais et naissent normalement avec 3 voisines, et `VisiteurCovid` (mode Plague) ou une cellule vivante meurt si elle a plus de 2 voisines et une cellule morte nait avec seulement 1 voisine. Le `JComboBox` dans l'UI est mis a jour pour proposer les 5 regles : Classique, HighLife, Day & Night, Immortel et Plague.

Partie 7 - Ajout de `ObservateurConsole` qui implemente `Observateur`. A chaque appel de `actualise()`, il compte le nombre de cellules vivantes en parcourant la grille et affiche dans la console le numero de generation et le nombre de cellules vivantes. Enregistre dans le `main` comme deuxieme observateur de `JeuDeLaVie`.

Partie 8 - Implementation du systeme de structures. Ajout de la classe `Structure` qui contient un nom et une liste de coordonnees de cellules (`List<int[]>`). Ajout de `Gestionstructure` qui gere la lecture et l'ecriture des structures dans un fichier texte (`structures.txt`). Le format du fichier utilise `#` suivi du nom pour delimiter chaque structure, puis les coordonnees `x,y` ligne par ligne. Ajout dans `JeuDeLaVie` de `extractStructure()` pour extraire les cellules vivantes d'une zone rectangulaire, et `placeStructure()` pour poser une structure a une position donnee. Ajout dans `JeuDeLaVie` de `initializeGrilleVide()` pour creer une grille vide, et `initializeGrilleStrucutre()` pour initialiser la grille avec une structure centree.

Partie 9 - Amelioration de l'interface graphique `JeuDeLaVieUI`. Ajout du zoom avec la molette de la souris (facteur `zoomFactor` applique via `AffineTransform`), du deplacement de la grille par glisser-deposer (drag avec `offsetX`/`offsetY`), et d'un bouton Recentrer pour remettre le zoom et le decalage a zero. Ajout d'un mode dessin permettant de placer ou effacer des cellules par clic/drag (clic gauche = placer, clic droit = effacer). Ajout d'un panneau haut avec un `JComboBox` pour choisir la structure de depart, des champs pour modifier la taille de la grille, un slider pour la densite de cellules vivantes, et un bouton Nouvelle Grille. Ajout d'une section Structures dans le panneau bas avec un `JComboBox` listant les structures sauvegardees, un bouton Placer pour poser une structure par clic sur la grille, et un bouton Sauvegarder qui active un mode selection ou l'utilisateur clique sur 2 points de la grille puis donne un nom pour enregistrer la structure via `Gestionstructure`.

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