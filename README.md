How to run
1. import weatherReportDemo project to IDE
2. use maven command "clean package" to build jar file
3. run java -jar target/WeatherReportDemo-0.0.1-SNAPSHOT.jar 
4. open browser type in http://localhost:8080 to view weather data for city of london and hongkong

Or 

Jar file is uploaded under target folder, you can simply run jar file
----------------------------------------------------------------------------------------------------------------------------------------------------------
To do
1. City id is hard coded, and should get complete city id list
2. whenever change the city name, the whole page is refreshed in order to display updated information. Single page application should be built such as using reactjs
3. Utility temp convert, i am assuming that all input parameter are valid . i should do more error handling in case input non digit value
4. Better comment 
5. tostring is not good enough, and should be redesigned
6. more test cases 
