# Repair with multi-version execution [![Build Status](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion.svg?branch=master)](https://travis-ci.org/Oupsla/OPL-RepairMultiVersion)
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
- **[References](#references)**

## Introduction

Software systems are constantly evolving, with new versions and patches being released on a continuous basis. Unfortunately, software updates present a high risk, with many releases introducing new bugs and security vulnerabilities.

In this work, we propose a technique for improving the reliability and security of software updates. Software updates are an integral part of the software life-cycle, but present a high failure rate. Many users and administrators refuse to upgrade their software and rely instead on outdated versions, which often leaves them exposed to critical bugs and security vulnerabilities. One of the main reasons for which users hesitate to install updates is that a significant number of them result in failures.

We wrote an application that embeds the history of a software application at runtime (using [Spoon](https://github.com/INRIA/spoon)). The evaluation section is an analysis of how it works on a real Github repository.
Our application is a [Code rewinder](https://github.com/dufaux/IDL-1) v2. We will evaluate our application by showing that it can successfully detects potential crashes in several real applications.

## Technical work
### Goal
Our goal is to improve the software update process in such a way as to encourage developpers to run the unit tests also on old versions of the software. 
The main goal is to improve the existing prototype and make it really work. An additional goal is also to improve the easy to use.

### Overview

We get into this problem using a simple but effective multiversion based approach. First, we generate a special version of the given program. This special version includes several versions of the program using the version manager. Then, our application works dynamically to run unit tests on all those differents versions. We can finaly observe the tracing of those tests.

![working_scheme](https://s30.postimg.org/8fp73h4b5/15870726_10154268280013177_490723363_n.png/ "How it works ?")

### Architecture
The project is coded in Java and works with [Spoon](http://spoon.gforge.inria.fr/ "spoon"), the library shortly described below that we used to extract methods from a project and modify them.

There are also parts that are coded in [Kotlin](https://kotlinlang.org/) which is a functional oriented object programing language with a static typing that can also be compiled for being executed by the Java Virtual Machine. It is fully compatible with Java.

For the creation of different versions of a project that we want to test and repair we used [Jgit](https://eclipse.org/jgit "Jgit") to be OS independent.

[Spoon](https://github.com/INRIA/spoon) is an open source library to analyze, rewrite, transform, transpile Java source code. It parses source files to build a well-designed AST with powerful analysis and transformation API. The role of [Spoon](https://github.com/INRIA/spoon) in this project is to extract the methods of  each version and to replace them with those from the last version.

### Use
Simply configure the app via the app_conf.json file then compile with maven (cmd : "mvn compile") then run the class main.App
the config file should look like this:<br/>
```
{
  "junit_jar":"C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar",
  "github_project":"OPL-TestRepo",
  "github_user_name":"Oupsla",
  "src_path_in_project" : "src/main/"
}
```
with :
* junit_jar : the location of junit
* github_project : the project to test
* github_user_name : your github username
* src_path_in_project : the sources location within the "github_project"

## Evaluation
The software we produced is achieving the goal we expressed in the first part of this report.
here is an example of the production of this software :
```
gitUsername = Oupsla
gitProject = OPL-TestRepo
srcPath = src/main/
classPath = C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar
number of commits = 3
Calling Git to generate 3 folders from : https://github.com/Oupsla/OPL-TestRepo
Cloning from https://github.com/Oupsla/OPL-TestRepo to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo\master
Cloning repository: C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo\master\.git
Commits of branch: refs/heads/master
\-------------------------------------
Cloning repository from commit : bb5d35f3b98986b73cda28092f1858390e48548d to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo\bb5d35f3b98986b73cda28092f1858390e48548d
Cloning repository from commit : 3e0211fa3b8ca145543356707c90f63c70754aad to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo\3e0211fa3b8ca145543356707c90f63c70754aad
Cloning repository from commit : e1b39df35bb4ee1f31f7827ceea093a527dd532a to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo\e1b39df35bb4ee1f31f7827ceea093a527dd532a
Number of max commit reached : 3
Adding sniper to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo/3e0211fa3b8ca145543356707c90f63c70754aad/src/main/
with classPath = C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar
Adding sniper to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo/bb5d35f3b98986b73cda28092f1858390e48548d/src/main/
with classPath = C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar
Adding sniper to C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo/e1b39df35bb4ee1f31f7827ceea093a527dd532a/src/main/
with classPath = C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar
Spoon the master as the new project template = C:\Users\Apolloch\IdeaProjects\OPL-RepairMultiVersion\tmp\oplOPL-TestRepo/master/src/main/
with classPath = C:/Users/Apolloch/.m2/repository/junit/junit/4.12/junit-4.12.jar
\------ Run Tests ------
MODIF CLASS main.A
MODIF METHOD barA
runCount = 4
FailureCount = 0

\------ Result ------
Version found :
all tests are green when using the version of the commit 3e0211fa3b8ca145543356707c90f63c70754aad for the method main.A.barA
You can modify the files by yourself or just change the value of the field barA_version by 0 in the file file://C:/Users/Apolloch/IdeaProjects/OPL-RepairMultiVersion/spooned/bin/main/A.class
\---- End of program ----


---- End of program ----
```

Because we don't manage the depedencies, the usability of this project is restraint.

## Limitation

 - For now, the project we want to test and repair via our tool must be written in Java.
 - The unit tests written for the project must be well written otherwise the tool will not be able to draw anything from it since our project uses these tests to be able to find through the versions a way to repair the application.
 - It's necessary to limit the number of commits in order to have a fast execution.

## Discussion
We use unit tests in our tool for a specific purpose: prevent regression. Those unit tests are used by developers to assure that each new version that they develop don’t break the current content.
If errors occur the tests will no longer pass and developers will be directly aware of the regression. The utility of the tool that we propose can be discussed because this tool relies on good unit tests. But normally if those unit tests are good, developers don’t do regression (or if they do, they are aware of it). It this case, the utility of our tool is reduced.

We can imagine our tool in the future combined with multi-version execution or [JAFF](https://goo.gl/K4Nb3g).

Multi-version execution tools run a new version in parallel with an old one. Then, by selecting the output of the more reliable version when their executions diverge, it can increase the overall reliability of the software. The goal is to have the multi-version software system be at least as reliable and secure as each individual version by itself.

[Arcuri](https://sites.google.com/site/arcuri82/)’s JAFF can repair faults automatically in Java programs by identifying parts that fail unit tests and evolving limited software patches.

In the future, we can imagine to provide a GUI but at this point it seemed unnecessary because our system does not have many options or alternatives of treatment. If a GUI is developped, we could also provide a feature where the user can select relevant commit (for example, it would be not necessary to select a commit that edits documentation because this version will not resolve a test).

The base of our system retrieve all versions of a project but in most of projects, there are a lot of commits. Therefore the user can specify a number of commits (from the most recent to the specified number) that the script will retrieve. The execution time is therefore relative to the number of commits that our system has to process. This means that the user have to select between efficiency or speed. In order to launch without any efforts on public project we have to improve the dependencies management to download all the depencies automatically.


## Conclusion
Software update cannot be avoided during the maintenance process. It also presents a failure risk and releases can introduce new bugs. We propose a tool that matches all our goals, able to run multi-version unit tests which is a prove of concept that can automatically prevent regression bugs. Coupled with tools like multi-version execution or JAFF, our goal is to enable both users and developers to benefit from new features and bug fixes provided by new versions, without renouncing the stability of older versions.

## Glossary
## References

 - [Safe Software Updates via Multi-version Execution](http://srg.doc.ic.ac.uk/files/papers/mx-icse-13.pdf)
 - [Object-Level Recombination of Commodity Applications](http://people.scs.carleton.ca/~soma/pubs/bfoster-gecco-2010.pdf)
 - [Spoon](http://spoon.gforge.inria.fr/)
 - [Evolutionary Repair of Faulty Software](https://goo.gl/K4Nb3g)
