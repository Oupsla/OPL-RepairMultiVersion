Last update: 29/11/2016
# Repair with multi-version execution
OPL - Theme 3: Automatic Diagnosis and Repair
## Authors
- [Denis Hamann](#https://github.com/denishamann)
- [Nathan Bacquet](#https://github.com/Apolloch)
- [Nicolas Delperdange](#https://github.com/Oupsla)
- [Benjamin Coenen](#https://github.com/bnjjj)

## Table of Contents
- **[Introduction](#introduction)**   
- **[Technical work](#technical-work)**   
- **[Evaluation](#evaluation)**
- **[Limitations](#limitation)**  
- **[Conclusion](#conclusion)**
- **[Glossary](#glossary)**

## Introduction

Software systems are constantly evolving, with new versions and patches being released on a continuous basis. Unfortunately, software updates present a high risk, with many releases introducing new bugs and security vulnerabilities.

In this work, we propose a technique for improving the reliability and security of software updates. Software updates are an integral part of the software life-cycle, but present a high failure rate. Many users and administrators refuse to upgrade their software and rely instead on outdated versions, which often leaves them exposed to critical bugs and security vulnerabilities. One of the main reasons for which users hesitate to install updates is that a significant number of them result in failures. 

We wrote an application that embeds the history of a software application at runtime (using [Spoon](https://github.com/INRIA/spoon)). The evaluation section is an analysis of how it works on a real Github repository.
Our application is an improvement of the existing prototype [Code rewinder](https://github.com/dufaux/IDL-1). We will evaluate our application by showing that it can successfully detetect potential crashes in several real applications.

We get into this problem using a simple but effective multiversion based approach. First, we generate a special version of the given program. This special version includes several version of the program using de version manager. Then, our application works dynamically to run unit tests on all those differents versions. We can finaly observ the tracing of those tests.

Our goal is to improve the software update process in such a way as to encourage developpers to run the unit tests also on old versions of the software.

## Technical work
But?
Overview (Mockups, workflows)
Algorithme
Architecture (langage, librairies utilisées, modules et classes)
Implémentation (code/patterns/idioms élégants ou efficaces, taille)
Utilisation (screenshots, etc)
## Evaluation
Efficacité
Complexité
Performance
Facilité d'utilisation
...
## Limitation
## Conclusion
## Glossary
