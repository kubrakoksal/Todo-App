[TodoApp.postman_collection.json](https://github.com/kubrakoksal/Todo-App/files/14649185/TodoApp.postman_collection.json)# Todo List Application

This application is a demo project includes following operations
- Login
- Register
- CRUD Todo Operations

## Technology Stack
* Java 17
* Spring Boot
* Spring Data Couchbase
* Couchbase DB
* Swagger
* Maven
* Docker
* Junit and Mockito
* JWT token

## Installation
### PreRequests
1. Docker (for Dockerized database, front-end, and back-end applications)
2. Maven (for building and running tests)

### Build and Run App Locally (To use run you can skip this step)
```bash
git clone https://github.com/kubrakoksal/Todo-App.git
cd Todo-App
# build application and run tests
mvn clean package
# run only tests 
mavn test
```

If you want to run project locally change  following properties based on your db informations\
- COUCHBASE_CONNECTION_HOST
- COUCHBASE_USERNAME
- COUCHBASE_PASSWORD
- COUCHBASE_BUCKET

### Run Project With Docker
#### 1. Run Couchdb Database
```bash
docker run -d --name my-couchbase -p 8091-8094:8091-8094 -p 11210:11210 -e CLUSTER_USERNAME=user -e CLUSTER_PASSWORD=password -e CLUSTER_NAME=Example_Cluster -e SERVICES=data,index,query,fts -e BUCKET=app_bucket -e NODE_INIT_INDEX_PATH=/opt/couchbase/var/lib/couchbase/indexes -e RBAC_USERNAME=user -e RBAC_PASSWORD=pwd123 bentonam/couchbase-docker
```
Change environment variables (CLUSTER_USERNAME, CLUSTER_PASSWORD, CLUSTER_NAME, BUCKET) as you wish <br>  <br>
Please log in at [http://localhost:8091/ui/index.html](http://localhost:8091/ui/index.html) and wait until the page below is opened (It may take some time).<br><br>
![image](https://github.com/kubrakoksal/Todo-App/assets/47196852/6bd0ebaf-ea36-498f-a90f-ff70bbfca598)<br>
Login to the database using the CLUSTER_USERNAME and CLUSTER_PASSWORD values.<br>

#### 2. Run Todo App
```bash
docker run -d -p 8080:8080 -e PORT=8080 -e COUCHBASE_CONNECTION_HOST=couchbase://ip -e COUCHBASE_USERNAME=user -e COUCHBASE_PASSWORD=password -e COUCHBASE_BUCKET=app_bucket kkoksal/todo-app-be
```
!! Change ip based on your db machine ip (ex: ipconfig windows)<br>
!! Use the same values for the COUCHBASE_ variables that you use when running the database<br>
Usage -> TodoApp.postman_collection.json

#### 3. Run Todo App FE (Optional)
```bash
docker run -d -p 8081:8081 -e PORT=8081 -e VUE_APP_IP=ip -e VUE_APP_PORT=8080  kkoksal/todo-app-fe npm run serve
```
http://ip:port/login (http://localhost:8081/login)<br>
!! Change VUE_APP_IP and VUE_APP_PORT based on your be app configurations (ex: ipconfig windows)<br>
