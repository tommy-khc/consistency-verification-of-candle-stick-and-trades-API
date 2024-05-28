# Consistency Verification Of Candle Stick API And Trades API from Crypto.com

## Background

In the stock price chart, you can see some rectangular objects in green or red colour to represent the distribution of stock price on each day or each month. It is called Candlestick. It has four components which are open, close, high and low in stock prices.</br>

![Candlestick](https://user-images.githubusercontent.com/66003316/203911743-912b6ed2-c38b-4618-8a7d-a67a59bd3e16.jpg)
</br>

Trades mean trades between people in the stock market. It involves trade price and time etc.

[Crypto.com](https://crypto.com/) is a global cryptocurrency exchange company. The candle stick API and Trades API provided by this company will be the subjects of this project.

## Description

Candlesticks can be formed by trading prices and times from Trades API and Candlestick API. </br>

This application can get 5 continuous candlesticks from Candlesticks API and Trade API. Then it compares them and outputs the result of the consistency of information from the two APIs.

## Motivation

After I graduated in 2019, I wanted to challenge myself by building technology applications for the financial market. This project aims to build up a decent program that can find if there are bugs in two APIs with strong proof from a popular cryptocurrency exchange.

## Features

- Comparing real-time market data of any instruments in any time frames
- Starting analyzing data at the specific time user input

## Build status
It is not completed. The floating point problem in comparing numbers (i.e. prices and volume) hasn't been solved. Also, the program can check only 5 candlesticks but not all upcoming candlesticks until the user terminates the program. A bug related to multi-threading which causes the program can not to end automatically hasn't been solved.

## Code style
[Google Java Style](https://google.github.io/styleguide/javaguide.html)

## Screenshots

Input the type of candlesticks and the time to collect</br>
![Input the type of candlesticks and the time to collect](https://user-images.githubusercontent.com/66003316/203912467-9e4a742d-08a3-4baa-b97c-e7a66245761a.png)</br>

Showing API response data</br>
![data from API response](https://user-images.githubusercontent.com/66003316/203921604-fab89627-86b0-4292-9d0c-84a6f780bc12.png)</br>

Showing the consistency verification of selected candlesticks</br>
![Output](https://user-images.githubusercontent.com/66003316/203921968-033709d8-2185-4608-a7e7-7a61e654f2e8.png)</br>


## Tech/framework used
### Tech
- Multithreading
- Algorithm
- Restful API
- Polling

### Framework
- Junit 
- log4j2

## Installation
### Build source code to .jar:
#### In IntelliJ:
1. From the main menu, select File | ProjectStructure and click Artifacts
2. Click + and point to "Jar" and select "from modules with dependencies"
3. To the right of the Main Class Field, type "Main"
4. Below Jar files from libraries, click "extract to Target Jar" (all dependencies and src will be built in one .jar) 
5. Apply the changes and close the dialogue
6. From the main menu, select Build | Build Artifacts
7. Click "Build" under action to build .jar
8. cvcat.jar is in \...\IdeaProjects\CVCAT\out\artifacts\CVCAT_jar

## Test
There are four tests in this project.
1. Download or clone this repo
2. Simply just run the tests in IDE
In Intellij, click the green arrow beside "public class" in each test.class.

## How to use it?
### Run cvcat.jar:
1. open cmd and type the following command:
```sh
cd {absolute directory of cvcat.jar}\cvcat.jar
java -jar cvcat.jar
```
2. Follow instructions
3. To restart the application, close the terminal and run it again

## Reference
API document from Crypto.com:</br>
[https://exchange-docs.crypto.com/spot/index.html?java#introduction](https://exchange-docs.crypto.com/spot/index.html?java#introduction)

## Room for Improvement
The multithreading and number comparisons in this program need to be improved. </br>
Room for improvement:
- Program can exit the thread and end itself after executing all codes
- Enhance the precision of comparisons between stock prices and volume

To do:
- learn more about multithreading and replace the multithreading library currently use with other
- Solve rounding errors in floating point comparisons

## License
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)

