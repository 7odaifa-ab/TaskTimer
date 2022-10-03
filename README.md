<div align="center"> <img  align="center" src="https://user-images.githubusercontent.com/86853497/190990657-64783dc4-e4c8-4b3d-a410-deba573e4129.png" />
 </div>
<h1 align="center">TaskTimer <a href="https://github.com/7odaifa-ab/TaskTimer"></a></h1>
<p align="center">
  <a target="_blank" href="https://github.com/7odaifa-ab/TaskTimer/releases/download/2.0/TaskTimer_Setup.exe"><img src="https://img.shields.io/badge/Download-V2.0-brightgreen"></a>
  <a target="_blank" href="https://github.com/7odaifa-ab/TaskTimer/releases"><img src="https://img.shields.io/badge/Releases-Versions%20List-lightgrey"></a>
  <a target="_blank" href="https://www.oracle.com/java/technologies/javase/18-0-2-relnotes.html"><img src="https://img.shields.io/badge/Java-18.0.2-orange?logo=java"></a>
  <a target="_blank" href="https://gradle.org/"><img src="https://img.shields.io/badge/Gradle-7.5%2B-green"></a>
  <a target="_blank" href="LICENSE"><img src="https://img.shields.io/badge/Licence-The%20Unlicens-blue"></a>
</p>

<p align="center">An accurate timer utility for running tasks/commands on the given interval Hours, Minutes, Seconds.</p>

<i><p align="center">
  Owner & Author: <a target="_blank" href="https://github.com/7odaifa-ab">Hudaifa Abdullah</a><br>
</p></i>

# Screenshots
<img src="https://user-images.githubusercontent.com/86853497/190987465-f46ae872-1a5c-40ee-8c47-fa5e8d4f7948.png" width="400" height="250" /> <img src="https://user-images.githubusercontent.com/86853497/190987524-ff046beb-c7f3-44a4-aac1-ac11273226c7.png" width="400" height="250" /> <img src="https://user-images.githubusercontent.com/86853497/190987730-443cec81-92c3-4452-ab88-b59268ab0f92.png" width="400" height="250" />

# Getting Started
Task Timer is a Java project that uses a `JDK-18.0.2`, ` JavaFX 17` for its Interface, and `Gradle` build for packing, along with IntelliJ IDEA

Task Timer uses the tool ` HibernationEnabler ` to run an elevated command made in `.Net 6` and Visual Studio 2022   


## Setup
```
1. get the requirement ready
2. download the source code
3. unzip it to a folder
4. chose to open an existing project from IntelliJ IDEA and navigate to the source code folder
5. sync the project to download the dependencies
6. open the Gradle menu and build the project 
```
Or check <a href = "https://github.com/7odaifa-ab/TaskTimer/releases">releases</a> for a ready packed installer

## Usage
it can send a Command to the Command prompt at the end of a given time interval e.g. Shutdown the Computer


## How it works
Task Timer uses `javafx.animation.Timeline` to start a timer and on Finish `ProcessBuilder` class start a CMD with a given argument to perform a single task

## Coming Features
* Multi-Langague support
* New themes
* auto update module

## Downside to be Fixed
None known till now ^_^
