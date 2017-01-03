# Repair with multi-version execution [![Build Status](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion.svg?branch=master)](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion)
OPL - Theme 3: Automatic Diagnosis and Repair
## Authors
- [Denis Hamann](https://github.com/denishamann)
- [Nathan Bacquet](https://github.com/Apolloch)
- [Nicolas Delperdange](https://github.com/Oupsla)
- [Benjamin Coenen](https://github.com/bnjjj)



**Table of Contents**

- [Repair with multi-version execution](#Repair with multi-version execution)
	- [Introduction](#Introduction)
	- [Technical work](#Technical work)
		- [Goal](#Goal)
		- [Overview](#Overview)
		- [Algorithm](#Algorithm)
		- [Architecture](#Architecture)
		- [Use](#Use)
	- [Evaluation](#Evaluation)
	- [Ease of use](#Ease of use)
	- [Performance](#Performance)
	- [Limitation](#Limitation)
	- [Discussion](#Discussion)
	- [Conclusion](#Conclusion)
	- [Glossary](#Glossary)
	- [References](#References

## Introduction

Software systems are constantly evolving, with new versions and patches being released on a continuous basis. Unfortunately, software updates present a high risk, with many releases introducing new bugs and security vulnerabilities.

In this work, we propose a technique for improving the reliability and security of software updates. Software updates are an integral part of the software life-cycle, but present a high failure rate. Many users and administrators refuse to upgrade their software and rely instead on outdated versions, which often leaves them exposed to critical bugs and security vulnerabilities. One of the main reasons for which users hesitate to install updates is that a significant number of them result in failures.

We wrote an application that embeds the history of a software application at runtime (using [Spoon](https://github.com/INRIA/spoon)). The evaluation section is an analysis of how it works on a real Github repository.
Our application is an improvement of the existing prototype [Code rewinder](https://github.com/dufaux/IDL-1). We will evaluate our application by showing that it can successfully detect potential crashes in several real applications.

We get into this problem using a simple but effective multiversion based approach. First, we generate a special version of the given program. This special version includes several versions of the program using the version manager. Then, our application works dynamically to run unit tests on all those differents versions. We can finaly observe the tracing of those tests.

Our goal is to improve the software update process in such a way as to encourage developpers to run the unit tests also on old versions of the software.

## Technical work
### Goal
Notre objectif principal lors de la réalisation de ce projet a été de rendre ce projet plus facilement utilisable (agnostique du système d'exploitation, meilleure interface de commandes, ...).

### Overview
(Mockups, workflows)

### Algorithm

### Architecture
We retrieved the project [Code rewinder](https://github.com/dufaux/IDL-1) that was coded in Java so we decided to keep this language for our implementation. Not only because a base was already existing in Java, but also because of compatibility with [Spoon](http://spoon.gforge.inria.fr/ "spoon"), the library shortly described below that we used to extract methods from a project and modify them.

Besides the Java code, this project is also composed of [Kotlin](https://kotlinlang.org/) which is a functional oriented object programing language with a static typing that can also be compiled for being executed by the Java Virtual Machine. We implement it because it was fully compatible with Java and it was interesting for us to learn another language so that we could draw some purely technical skills of this project.

For the creation of different versions of a project that we want to test and repair, [Code rewinder](https://github.com/dufaux/IDL-1) creates a simple batch script file that executes bash and git commands. We quickly redo the whole system in order to manage only one code independently of the operating system it is running on. Everything has been rewritten in Java with using [Jgit](https://eclipse.org/jgit "Jgit"), a Java library developed by the [Eclipse Foundation](https://eclipse.org "Eclipse").

[Spoon](https://github.com/INRIA/spoon) is an open source library to analyze, rewrite, transform, transpile Java source code. It parses source files to build a well-designed AST with powerful analysis and transformation API. The role of [Spoon](https://github.com/INRIA/spoon) in this project is to extract the methods of  each version and to replace them with those from the last version.

### Use
(screenshots, etc)

## Evaluation
Efficacité
Complexité
Facilité d'utilisation
...

## Ease of use
The project has been refactored to not use a bash script to retrieve all the versions of a github repository. This mean that the project is not limited by this script anymore and can be use on any platform.  
The user can launch our script simply by providing a github username and a project name (this project must be public) and our system will take care of everything.
We could have provided a GUI but at this point it seemed unnecessary because our system does not have many options or alternatives of treatment.


## Performance
The base of our system retrieve all versions of a project but in most of projects, there are a lot of commits and branches so we had to change that.
The user can now specify a number of commits (from the most recent to the number) that the script will retrieve. The execution time is therefore relative to the number of commit that our system has to process. This mean that the user have to select between efficiency or speed.

If a GUI is developped, we can also provide the functionnality that a user select revelant commit (it is not necessary for example, to select a commit that edit documentation because this version will not resolve a test).

## Limitation

 - For now, the project we want to test and repair via our tool must be written in Java.
 - The unit tests written for the project must be well written otherwise the tool will not be able to draw anything from it since our project uses these tests to be able to find through the versions a way to repair the application.
 - The project should not be too large otherwise it is necessary to limit the number of commits.

## Discussion
We use unit tests in our tool for a specific purpose: prevent regression. Those unit tests are used by developers to assure that each new version that they develop don’t break the current content.
If errors occur the tests will no longer pass and developers will be directly aware of the regression. The utility of the tool that we propose can be discussed because this tool relies on good unit tests. But normally if those unit tests are good, developers don’t do regression (or if they do, they are aware of it). It this case, the utility of our tool is reduced.

We can imagine our tool in the future combined with multi-version execution or [JAFF](https://goo.gl/K4Nb3g).

Multi-version execution tools run a new version in parallel with an old one. Then, by selecting the output of the more reliable version when their executions diverge, it can increase the overall reliability of the software. The goal is to have the multi-version software system be at least as reliable and secure as each individual version by itself.

[Arcuri](https://sites.google.com/site/arcuri82/)’s JAFF can repair faults automatically in Java programs by identifying parts that fail unit tests and evolving limited software patches.


## Conclusion
Software update cannot be avoided during the maintenance process. It also presents a failure risk and releases can introduce new bugs. We propose a tool able to run multi-version unit tests which is a prove of concept that can automatically prevent regression bugs. Coupled with tools like multi-version execution or JAFF, our goal is to enable both users and developers to benefit from new features and bug fixes provided by new versions, without renouncing the stability of older versions.

## Glossary
## References

 - [Safe Software Updates via Multi-version Execution](http://srg.doc.ic.ac.uk/files/papers/mx-icse-13.pdf)
 - [Object-Level Recombination of Commodity Applications](http://people.scs.carleton.ca/~soma/pubs/bfoster-gecco-2010.pdf)
 - [Spoon](http://spoon.gforge.inria.fr/)
 - [Evolutionary Repair of Faulty Software](https://goo.gl/K4Nb3g)
