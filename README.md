HOW TO RUN:
=============================================

Java version: 17

To run application:
1. mvn clean install
2. Run spring-boot app with profile (or default):
- mvn spring-boot:run -Dspring-boot.run.profiles=prod
- mvn spring-boot:run

3. App will be ready on: localhost:8080


PROFILES:
- prod - use datasource based on external file
- default/dev/test - use local datasource file


Description:
=============================================

Application based on Dijkstra algorithm to find the shortest path from one country to another. For This example:
- Dijkstra node/vortex - defined as country

- Dijkstra node distance - by calculate vector of countries co-ordinates ("latlng" param) - Between source country and his border countries.
  Of course, it is not very precise, but it helps to estimate the cost of the road


Known bugs:
- Example: from: Poland, to: Mongolia application will find the shortest path as: ["POL", "RUS", "MNG"] - it's not true. It's because provided data don't include information about "separated" countries.