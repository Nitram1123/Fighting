# Combat à mort

Ce projet J2EE implémente un jeu de carte appelé Combat à mort avec un chat afin que les joueurs puisse communiquer entre eux.

Le jeu est entièrement textuel et est conçu pour être accessible aux personnes non voyantes avec un lecteur d’écran. Le développement est pensé de manière à ce que l’ajout d’image soit facilement réalisable.

Le projet est structuré dans une architecture MVC. Il est créé dans un cadre pédagogique dans le but de travailler avec les technologies J2EE. Donc, il reste relativement basic, mais la maintenance doit être pensée afin de pouvoir ajouter de futures fonctionnalités. Par exemple, on pourrait ajouter un système de login et la persistance des partis en base de données. Ça permettrait de faire des statistiques pour chaque joueur.

Principalement, il y a deux contrôleurs et deux JSP. Un pour l’index et l’autre pour gérer le jeu.

On utilise JQuery pour synchroniser les messages de chat et l’historique des parties en court avec Ajax.

La JSP Index et le contrôleur permettent d’afficher les règles, de faire communiquer les joueurs entre eux et de démarrer une nouvelle partie.

Lorsque le lien pour jouer est activé, il y a deux scénarios possibles.

On commence par créer un personnage

S’il n’y a pas un autre joueur en attente
Le joueur est ajouté dans la liste d’attente
Il est envoyé sur la page Wait.jsp dans laquelle il y a un petit script qui vérifie à tout les seconde s’il y a un autre joueur qu’il l’a invité

Sinon, on crée une nouvelle partie et on ajoute un joueur en attente plus le joueur courant.

Dans le contrôleur Game, il y a une liste de partie en cour. Chacune d’entre elle est identifiable par une clé étant le pseudo du personnage pris dans la file d’attente. On considère qu’un personnage ne peut pas jouer deux parties en même temps et qu’il ne peut avoir deux personnages avec le même pseudo.

Les joueurs peuvent suivre le déroulement d’une partie grâce à une liste de messages contenus dans chaque objet Game. Le principe reste le même que le chat de l’index. Par contre, le serveur peut également ajouter des messages dans la liste.



##Créer le projet :
    
    
Créer un projet avec Gradle (recommandée):

    installation de Gradle : http://gradle.org/

    utilisation de gradle :

    1°/ télécharger le projet à partir de ce site

    2°/ construire le projet avec la commande "gradle build"

    3°/ Tester le projet via Jetty avec la commande "gradle jettyRunWar" et utiliser un navigateur

    4°/ optionnel : convertir le projet en un projet Eclipse avec la commande "gradle eclipse" puis importer le projet dans Eclipse (File -> import -> existing project into Eclipse -> browse ...)
    
    5°/ Pour une utilisation sous Eclipse : télécharger et décompresser apache/tomcat 7 version binaire, sous Eclipse File : Window -> preferences -> server -> runtime environment -> add (aller chercher apache/tomcat), pour lancer le serveur : clic droit sur le projet -> Run as ->  Run on server -> next ...
    
    
