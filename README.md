# cvcat - ConsistencyVerificationOfCandleStickAndTradesAPI

Description: this application can check 5 continuous candle sticks and verify consistency between get-trade and get-candleStick API. 
Java 11, Junit 5, log42j and maven are adopted in this project. <br/>

How to build .jar in IntelliJ:
1. File -> ProjectStructure -> Artifacts -> + > Jar -> from modules with dependencies -> choose Main for Main Class -> Jar files from libraries : tick: extract to Target Jar (all dependencies and src will be built in one .jar) -> OK -> Apply or Ok
2. Build -> Build Artifacts
Then cvcat.jar is in /.../IdeaProjects/CVCAT/out/artifacts/CVCAT_jar

run java application: <br />
1. open cmd <br />
2. cd to .jar's directory <br />
3. java -jar cvcat.jar <br />
4. follow instruction <br />
5. To restart application, close terminal and follow step 1 to 4 <br />

Remarks: don't know how to stop all threads so that application keeps running even though all codes are executed.

API Document: https://exchange-docs.crypto.com/spot/index.html?java#introduction 
