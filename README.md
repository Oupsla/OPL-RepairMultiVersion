Last update: 29/11/2016
# Repair with multi-version execution [![Build Status](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion.svg?branch=master)](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion) [![Coverage Status](https://coveralls.io/repos/github/Oupsla/OPL-RepairMultiVersion/badge.svg?branch=master)](https://coveralls.io/github/Oupsla/OPL-RepairMultiVersion?branch=master)
OPL - Theme 3: Automatic Diagnosis and Repair
## Authors
- [Denis Hamann](https://github.com/denishamann)
- [Nathan Bacquet](https://github.com/Apolloch)
- [Nicolas Delperdange](https://github.com/Oupsla)
- [Benjamin Coenen](https://github.com/bnjjj)

## Table of Contents
- **[Introduction](#introduction)**   
- **[Technical work](#technical-work)**   
- **[Evaluation](#evaluation)**
- **[Limitations](#limitation)**  
- **[Discussion](#discussion)**
- **[Conclusion](#conclusion)**
- **[Glossary](#glossary)**

## Introduction

Software systems are constantly evolving, with new versions and patches being released on a continuous basis. Unfortunately, software updates present a high risk, with many releases introducing new bugs and security vulnerabilities.

In this work, we propose a technique for improving the reliability and security of software updates. Software updates are an integral part of the software life-cycle, but present a high failure rate. Many users and administrators refuse to upgrade their software and rely instead on outdated versions, which often leaves them exposed to critical bugs and security vulnerabilities. One of the main reasons for which users hesitate to install updates is that a significant number of them result in failures. 

We wrote an application that embeds the history of a software application at runtime (using [Spoon](https://github.com/INRIA/spoon)). The evaluation section is an analysis of how it works on a real Github repository.
Our application is an improvement of the existing prototype [Code rewinder](https://github.com/dufaux/IDL-1). We will evaluate our application by showing that it can successfully detect potential crashes in several real applications.

We get into this problem using a simple but effective multiversion based approach. First, we generate a special version of the given program. This special version includes several versions of the program using the version manager. Then, our application works dynamically to run unit tests on all those differents versions. We can finaly observe the tracing of those tests.

Our goal is to improve the software update process in such a way as to encourage developpers to run the unit tests also on old versions of the software.

## Technical work
### But
Notre objectif principal lors de la réalisation de ce projet a été de rendre ce projet plus facilement utilisable (agnostique du système d'exploitation, meilleure interface de commandes, ...). 

### Overview
(Mockups, workflows)

### Algorithm

### Architecture
Tout le projet contenait déjà une base de code développé en Java, notre implémentation est donc resté dans ce langage. Pas seulement car il avait été réalisé en Java auparavant mais aussi par soucis de compatibilité avec [Spoon](http://spoon.gforge.inria.fr/ "spoon"), une librairie que l'on utilise pour extraire les méthodes du projet et les modifier.

En plus du code Java, ce projet est aussi composé de [Kotlin](https://kotlinlang.org/) qui est un langage de programmation orienté objet et fonctionnel, avec un typage statique qui permet de compiler pour la Machine virtuelle Java. Nous l'avons implémenté car il est totalement compatible avec Java et qu'il nous semblait intéressant d'apprendre un autre langage afin que l'on puisse aussi tirer un apprentissage purement technique de ce projet.

Auparavant la création des différentes version du projet que l'on souhaite tester et réparer était créer à partir d'un fichier *.sh* qui était un simple script bash qui exécutait des commandes bash et git. On a rapidement refait tout ce système pour n'avoir à gérer qu'un seul code en fonction du système d'exploitation et avons tout réécris en Java à l'aide de la librairie Java développée par la [fondation Eclipse](https://eclipse.org "Eclipse") qui se nomme [Jgit](https://eclipse.org/jgit "Jgit").

Spoon is an open source library to analyze, rewrite, transform, transpile Java source code. It parses source files to build a well-designed AST with powerful analysis and transformation API. L'utilité de Spoon dans ce projet est d'extraire les méthodes dans chaque versions pour les remplacer avec celles de la dernière version. Pour ce faire ...

### Utilisation
(screenshots, etc)

## Evaluation
Efficacité
Complexité
Facilité d'utilisation
...

## Performance
Dans les grands projets il y a énormément de versions différentes. Puisque notre algorithme par défaut reprend toute les versions d'un projet pour pouvoir ensuite rechercher le code dans ces versions antérieures qui corrigera le bug de la version actuelle. Le temps d'exécution peut vite être très important, de ce fait nous avons implémenté une limite en précisant le nombre de version maximum que nous voulons parcourir.


## Limitation

 - Le projet que l'on souhaite testé et réparer via notre outil devra obligatoirement être en Java.
 - Il faut que le projet avec lequel on veut utiliser cet outil soit déjà bien testé unitairement sinon on ne pourra rien en tirer puisque notre projet utilise ces tests pour pouvoir trouver à travers les versions une façon de réparer notre application.

## Discussion
Dans tout projet, lorsque nous réalisons des tests unitaires c'est dans un but précis, empêcher la régression. Avec ces tests unitaires le développeur s'en sers pour s'assurer au fur et à mesure des versions qu'il développe qu'il ne casse rien d'existant. S'il y a une erreur les tests ne passeront plus et le développeur sera directement conscient d'une régression. L'utilité de l'outils que nous proposons aujourd'hui pourrait être à débattre donc puisque pour utiliser cet outil le développeur a besoin d'avoir de bons tests dans son code mais s'il a des bons tests normalement il n'effectue pas de régression ou bien si c'est le cas il en est conscient. Dans ce cas de figure l'utilité de notre outil devient assez faible.

## Conclusion
## Glossary
## References

 - [Safe Software Updates via Multi-version Execution](http://srg.doc.ic.ac.uk/files/papers/mx-icse-13.pdf)
 - [Object-Level Recombination of Commodity Applications](http://people.scs.carleton.ca/~soma/pubs/bfoster-gecco-2010.pdf)
 - [Spoon](http://spoon.gforge.inria.fr/)

