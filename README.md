# Android Test Back Up

***Choix Technique***

Pour la partie Design de l'application, j'ai choisi d'utiliser un ConstraintLayout, car elle permet d'afficher les widgets les uns par rapport aux autres grâce à des contraintes, donc il m'a permis d'avoir une grande flexibilité
pour le ratingBar, j'ai préféré créer un projet différent et l'importer en tant que module, celà m'a permis de réaliser le design presque tel que précisé dans les instructions

Ne souhaitant utiliser que le minimum de dépendances et voulant avoir un programme propre, je n'ai utilisé en majorité que les Coroutines, ViewModel pour pouvoir récupérer et excécuter les thread de l'Api

ViewModelScope me permet d'annuler des coroutines si le ViewModel est effacé pour éviter d'utiliser trop de ressources,

J'ai quand même voulu ajouter une barre de progression afin que l'utilisateur puisse voir que les données sont en cours de chargement
Aussi, un texte et un bouton "refresh" en cas d'erreur.


***Difficultés rencontrées***

La première difficulté que j'ai rencontré est l'implémentation du programme pour l'écran d'accueil, c'est-à-dire pour le RatingBar, ayant déjà utilisé les modèles SVG pour d'autres langages de programmation
Ensuite, la deuxième difficulté que j'ai rencontrée est dans l'utilisation de l'architecture MVVM comme vous le savez, je n'ai que des connaissances académiques de l'utilisation de cet architecture mais celà m'a permis d'augmenter encore plus en compétence et de continuer vers cet avancé
Puis, ça ne m'a pas paru facile de réaliser ce test avec les différentes contraintes de l'API, les délais, les éventuels erreurs qui peuvent survenir et de les appréhender
Enfin, n'ayant jamais encore réalisé de test unitaire, et le délai imparti arrivant à échéance, je n'ai pas pu le réaliser, malgré celà, ce test m'a donné envie de maitriser cette partie.
J'aurais également souhaiter utiliser une base de donnée(RoomDatabase) et l'injection de dépendance pour stocker les données, celà aurait rendu le code plus professionnel et plus complet


***Patterns et dépendances***
 - la première utilisation du modèle MVVM dans un projet,
 - la première fois que je n'utilise que des Constraints Layout et ratingBar avec des SVG dans une application en Kotlin
 - L'utilisation des progressBar avec les coroutines
 

****Ce que j'aimerais améliorer si j'avais eu plus de temps****


 - Réaliser des tests unitaires avec Mockito, JUnit
 - Utiliser l'injection de dépendances, les bases de données
 - Améliorer la réalisation du design de l'application au niveau du progressBar
 - Mieux appréhender les autres éventuels erreurs pour que les utilisateurs ne s'en aperçoivent pas

