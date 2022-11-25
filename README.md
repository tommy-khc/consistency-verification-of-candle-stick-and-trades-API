# Consistency Verification Of Candle Stick API And Trades API from Crypto.com

## Background

In stock price chart, you can see some rectanglar objects in green or red colour to represent the stock prices distribution in each day or each month. It is called Candle Stick. It has four components which are open, close, high, low in stock prices.</br>

![Candle Stick](https://user-images.githubusercontent.com/66003316/203911743-912b6ed2-c38b-4618-8a7d-a67a59bd3e16.jpg)
</br>

Trades mean trades between people in stcok market. It involves trade price and time etc.

[Crypto.com](https://crypto.com/) is a gobal crypto currency exchange company. The candle stick API and Trades API provided by this company will be the subjects of this project.

## Description

Candle sticks can be formed by trading prices and times from Trades API and Candle Stick API. </br>

This application can get 5 continuous candle sticks from Candle Sticks API and Trade API. Then it compares them and output the result of consistency of infromation from two APIs.

## Motivation

Building technology applications for financial market is challenging. The aim of this project is to build up a decent program that can find if there are bugs in two APIs with a strong proof from a popular crytocurrency exchange.

## Features

- Comparing real time market data of any instruments in any time frames
- Starting anlaysing data at the specific time user input

## Build status
It is not completed. The floating point problem in comparing numbers (i.e. prices and volume) hasn't been solved. Also, the program can check only 5 candle sticks but not all up coming candle sticks until user terminate the program. A bug related to multi threading which causes the program can not end automatically hasn't been solve.

## Code style
[Google Java Style](https://google.github.io/styleguide/javaguide.html)

## Screenshots

Input the type of candle sticks and the time to collect</br>
![Input the type of candle sticks and the time to collect](https://user-images.githubusercontent.com/66003316/203912467-9e4a742d-08a3-4baa-b97c-e7a66245761a.png)</br>

Showing API response data</br>
![data from api response](https://user-images.githubusercontent.com/66003316/203921604-fab89627-86b0-4292-9d0c-84a6f780bc12.png)</br>

Showing the consistency verification of selected candle sticks</br>
![Output](https://user-images.githubusercontent.com/66003316/203921968-033709d8-2185-4608-a7e7-7a61e654f2e8.png)</br>


## Tech/framework used
### Tech
- Multi threading
- Algorithm
- Restful API
- Polling

### Framework
- Junit 
- log4j2

## Installation
### Build source code to .jar:
#### In IntelliJ:
1. From the main meun, select File | ProjectStructure and click Artifacts
2. Click + and point to "Jar" and select "from modules with dependencies"
3. To the right of the Main Class Field, type "Main"
4. Below Jar files from libraries, click "extract to Target Jar" (all dependencies and src will be built in one .jar) 
5. Apply the changes and close the dialog
6. From the main meun, select Build | Build Artifacts
7. Click "Build" under action to build .jar
8. cvcat.jar is in \...\IdeaProjects\CVCAT\out\artifacts\CVCAT_jar

## Test
There are four test.classes in this project.
1. Donwload or clone this repo
2. Simply just run the tests in IDE
In Intellij, click the green arrow beside "public class" in each test.class.

## How to use?
### Run cvcat.jar:
1. open cmd and type following command:
```sh
cd {absolute directory of cvcat.jar}\cvcat.jar
java -jar cvcat.jar
```
2. Follow instructions
3. To restart application, close terminal and run it again

## Reference
API document from Crypto.com :</br>
[https://exchange-docs.crypto.com/spot/index.html?java#introduction](https://exchange-docs.crypto.com/spot/index.html?java#introduction)

## Room for Improvement
The multithreading and number comparisons in this program needs to be improved. </br>
Room for improvement:
- Program can exit thread and end itself after executing all codes
- Enhance precision of comparisons between stock prices and volume

To do:
- learn more about multithreading and replace the multithreading library currently use to other
- Solve rounding error in floating point comparisons

## License
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

