# android-test

### Introduction

Dans ce test, tu vas devoir **construire une UI qui affiche des données** provenant d'une (fake) API.

Nous avons déjà créé le service `FakeDestinationFetchingService` qui va retourner les données. Attention, cette API est un peu lente et ne marche pas toujours très bien ;)

**L'écran d'accueil correspond à la vue Destinations.**

Pour réaliser ce test, tu peux **forker ce repo** et faire l'implémentation de ton coté, tu nous enverras ensuite l'URL de ton repo.

Chez Evaneos, nous accordons beaucoup d'importance à la **qualité du code et à la qualité de l'expérience utilisateur** de nos produits. Dans ce test, notre but n'est pas tant de savoir si tu parviendras à construire ces écrans mais plutôt **la manière dont tu le feras**. N'hésite pas à faire attention aux détails, à être **rigoureux et prévoyant** sur l'expérience utilisateur (lenteur API, qualité UI) et sur **la propreté, la structure et la clarté du code**. N'hésite pas à nous solliciter si tu as des questions.

Nous souhaitons aussi que tu nous montres ta capacité à **implémenter des tests unitaires** (tu n'es pas obligé de tout couvrir, mais essaie de porter une attention particulière à ceux que tu écriras).

Bonus : utiliser de l'injection de dépendances.

### Contraintes

- Utiliser une architecture **MVVM**
- Utiliser des **coroutines**

### Maquettes

iOS : [https://www.figma.com/file/4yIJXkSfo9xACHgG2KN0Yu/App-TestAlternantsMobileDestinationGuide?node-id=1%3A39](https://www.figma.com/file/4yIJXkSfo9xACHgG2KN0Yu/App-TestAlternantsMobileDestinationGuide?node-id=1%3A39)

Android : [https://www.figma.com/file/4yIJXkSfo9xACHgG2KN0Yu/App-TestAlternantsMobileDestinationGuide?node-id=3%3A77](https://www.figma.com/file/4yIJXkSfo9xACHgG2KN0Yu/App-TestAlternantsMobileDestinationGuide?node-id=3%3A77)

### Vue Destinations

Cet écran acceuille les destinations que le service retourne. 

Les destinations doivent être listées par ordre alphabétique.

Lorsque l'utilisateur sélectionne une destination, l'app affiche la vue Détails qui correspond à cette destination.

### Vue Détails

Cet écran n'affiche qu'une webview qui a pour URL celle fournie dans l'objet `DestinationDetails`

### Règles

- Limite autant que possible les dépendances externes et justifie leur utilisation.
- Ne modifie pas le code du module `data`. Contacte-nous si tu penses qu'il y a un problème !
- Essaie de faire des commits régulièrement, nous aimerions avoir un aperçu de l'évolution de ton test.

Lorsque tu auras terminé, nous te demandons d'expliquer tes choix techniques, les difficultés rencontrées, les patterns et dépendances que tu as utilisés pour la première fois et ce que tu aurais ajouté ou modifié si tu avais eu plus de temps à allouer au test, dans un fichier readme.
