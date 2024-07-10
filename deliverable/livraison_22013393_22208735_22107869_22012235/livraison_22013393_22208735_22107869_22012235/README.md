# Fil rouge Aide à la décision et intelligence artificielle


Projet guidé comportant 4 étapes :

  1. Modélisation d'un problème à travers des variables et des contraintes
  2. Résolution d'un problème de planification à travers les méthodes de recherche non informé(DFS, BFS) et  
     les méthodes de recherche heuristique (A*, Dijkstra)
  3. Résolution d'un problème de satisfaction de contraintes à travers des algorithmes de recherche systématiques 
     de solutions en particulier le backtracking et les techniques prospectives nommément le forward checking.
4.   Extraction de connaissances à partir d'un ensemble de données

Dans le cadre de ce projet, nous avons utilisé le langage de programmation Java. 

## Membres du groupe

  * [KISSAMI Safae]()
    * [groupe : 2B]()
    * [numéro étudiant: 22107869]()
  * [ZEKHNINI Cheyma]()
    * [groupe : 2B]()
    * [numéro étudiant: 22208735]()
  * [KITSOUKOU Manne Emile]()
    * [groupe : 2B]()
    * [numéro étudiant: 2213393]()
  * [OROU-GUIDOU Amirath Fara]()
    * [groupe : 2B]()
    * [numéro étudiant: 22012235]()

## Manuel d'utilisation

### Prérequis

  * [Last version of Java]()
  * [Ant]()
  * [An IDE or a text editor]()

### Utilisation

### Avec Ant

Vous pouvez lancez different taches avec ant :

  * `ant compile` : compile le projet
  * `ant jar` : crée un jar executable
  * `ant clean` : supprime les fichiers générés par ant
  * `ant doc` : génère la documentation du projet
  * `ant test` : lance les tests unitaires
  * `ant help` : affiche la liste des taches disponibles

### Avec un terminal

À partir la racine du projet, vous pouvez effectuer les commandes suivantes :

  * `javac -d build -classpath "lib/* $(find src -name "*.java")"` : compile le projet
  * `java -classpath "build:lib/*" test.TestRepresentation` : lance les tests relatifs à la représentation des problèmes
  * `java -classpath "build:lib/*" test.CSP` : lance les tests relatifs à la résolution de problèmes de satisfaction de contraintes
  * `java -classpath "build:lib/*" test.DataMining` : lance les tests relatifs à l'extraction de connaissances à partir d'un ensemble de données

## Bonus

### Partie 3

### Implémentation de AC3

Algorithm AC3 est un algorithme de propagation d'arc. Il permet de réduire le domaine des variables d'un problème de contraintes.
Ainsi, il fonctionne de la même manière que l'algorithme de propagation d'arc AC1. Cependant, il est plus efficace que l'algorithme AC1.
En effet, il permet de réduire le domaine des variables d'un problème de contraintes en un nombre réduit d'itérations. Cela est dû au fait
que l'algorithme AC3 utilise une file FIFO pour stocker les arcs à traiter. Ainsi, il est possible de traiter les arcs dans l'ordre dans lequel
ils ont été ajoutés à la file. De plus on rajoute dans la file uniquement les arcs qui ont été traités. Cela permet de réduire le nombre d'itérations
Cependant on peut toujours s'interroger sur l'efficacité de cet algorithm. En termes de temps d'exécution, il est possible de remarquer que cet algorithm
est plus efficace que l'algorithme AC1. Mais en terme d'occupation mémoire, il est possible de remarquer que cet algorithm est moins efficace que l'algorithme AC1.
Cela est dû au fait que l'algorithme AC3 utilise une file FIFO pour stocker les arcs à traiter. Ce qui implique que l'algorithme AC3 utilise plus de mémoire que l'algorithme AC1.




