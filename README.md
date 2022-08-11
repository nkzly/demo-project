# demo-project
Simple Dockerfile based demo-project

To make jar using maven
```
mvn clean package

```
To build image:
```
docker build -f Dockerfile -t app:v.0.1 .

```
To run image:
```
docker run -p 8090:8090 app:v.0.1
```

To call service:
```
GET http://localhost:8080/v1/coins/list
DELETE http://localhost:8080/v1/coins/list/usd-coin
```
