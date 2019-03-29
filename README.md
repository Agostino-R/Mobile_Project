# Projet Mobile 3A

## Mise en contexte 

Ce projet s'est fait dans le cadre du projet mobile de 3ème année. On a eu 2 mois pour développer une application 
mobile sur Android Studio. J'ai choisi d'articuler mon projet autour de l'univers des animes, car c'est un thème 
qui me passionne.
<br>
<br>

## Consignes du projet
### Les contraintes obligatoires ...
Afin de nous donner un axe de réflexion, les contraintes que devait respecter le projet étaient les suivantes:

* Utiliser au moins une recyclerView;
* Utiliser au moins 2 Activity;
* Récupérer des données via un appel API REST;
* Utiliser la mémoire cache.
### ... et les plus!
<br>
On pouvait aussi ajouter des fonctionnalitées supplémentaires, j'ai implementé les suivantes :
<br>
<br>

* Utiliser une architecture logicielle (MVC, MVP ou MVVM) : J'ai choisi d'utiliser la structure MVC;
* Utiliser les notifications PUSH;
* Faire un GitFlow;
* Faires des animations entre les écrans (j'ai utilisé des SplashScreen);
* J'ai implémenté une fonction de recherche;
* En fonction de l'activity sur laquelle on est, j'ai mit en place des filtres (Spinner et EditText);
* J'ai implementé une liste d'animes à voir que j'ai appelé "To Watch List" (Liste de préférés stockée en cache);
* Mise en place de liens HyperText.

## Présentation de l'API
### Introduction
L'API que j'ai utilisée est une API officieuse de <I>MyAnimeList</I>. Vous pouvez retrouver l'API 
sur <I>https://jikan.docs.apiary.io/</I>.
<br>
L'API se présente sous cette forme:

<p align="center">
  <img src = "https://image.noelshack.com/fichiers/2019/13/5/1553863973-json.png">
</p>

Ici on peut voir la fiche de l'anime <I>FullMetal Alchemist Brotherhood</I>.
<br>
### Comment ça marche?
Quand on fait un appel API, on reçoit en réponse un fichier JSON. Ce fichier est ensuite converti grâce à l'outil GSON, 
afin qu'on puisse récupérer les données, et les mettre dans des variables de notre programmes 
(très souvent dans des structures de données).
<br>
<B>ATTENTION :</B> il faut que le nom des variables dans le programme soient STRICTEMENT équivalents à ceux du fichier JSON.
On peux "contourner" ce problème en utiliasnt l'annotation @SerializedName.
<br>
### Les informations utiles à mon programme
J'ai utilisé les appels suivants:<br>
* Top;
* Season Archive;
* Schedule;
* Season Later ;
* Search;
* Anime.

## Mon application
### Les écrans
Sur mon application il y a 8 Activity. Les voici:<br>
* Le menu principal ou on choisit quelle liste voir;
* Le top des animes;
* Les animes par saison (exemple: Ete 2018);
* Les animes qui sont en train de sortir (triés par jour);
* Les animes qui sortiront bientôt;
* Une recherche;
* La liste "To Watch List";
* La description d'un anime. <br>
### Les screenshots
Les écrans se ressemblent, on va donc mettre ceux avec des spécificités:<br>
Voci le menu principal:

<p align="center">
  <img width ="216" height="440" src = "https://image.noelshack.com/fichiers/2019/13/5/1553866370-56310292-376734246250436-2209618922691362816-n.jpg">
</p>



On a ici dans l'ordre: le top, les animes par saison et la recherche:<br>
![alt text](https://image.noelshack.com/fichiers/2019/13/5/1553868272-ok.png)
<br><br>
Et voici l'activity affichant la description d'un anime:

<p align="center">
  <img width ="216" height="440" src = "https://image.noelshack.com/fichiers/2019/13/5/1553868289-55559455-405820159965361-4924667732760199168-n.jpg">
</p>
<p align="center">
  <img width ="216" height="440" src = "https://image.noelshack.com/fichiers/2019/13/5/1553868289-53478352-337159240253608-5446351631811608576-n.jpg">
</p>

### Les SplashScreens
J'ai ajouté dans mon programmes 2 SplashScreen. Le premier se lance au tout début de l'application. Voici un image:
<p align="center">
  <img width ="216" height="440" src = "https://image.noelshack.com/fichiers/2019/13/5/1553895020-launch.jpg">
</p>
On a aussi un écran de chargement qui ressemble à cela:
<p align="center">
  <img width ="216" height="440" src = "https://image.noelshack.com/fichiers/2019/13/5/1553895334-goku-anim.jpg">
</p>
L'image de fond est la même mais dans le chargement on a Son Goku qui fait un Kamehameha!
<br>
Pour réaliser cela j'ai utilisé une feuille de sprite récupérée sur <I>https://www.spriters-resource.com/</I>
, j'ai découpé les images qui m'interéssaient et ensuite j'ai mis en place l'animation. 

## Conclusion
J'ai vraiment aimé faire ce projet. La programmation mobile a été un coup de coeur pour moi car  j'ai appris quelque chose que je pourrais appliquer dans le futur. Si un jour je ne trouve pas une application sur téléphone, je pourrais la faire! De plus développer une application sur téléphone est une activité sympatique, au delà du côté pratique. 
