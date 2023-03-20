# SPRING-SHOP-API

This is a [Spring](https://spring.io/) application that simulates an online shop.

---

## Installation

To run this application, you'll need to have [Java](https://www.java.com/en/download/) and [Maven](https://maven.apache.org/) installed on your machine.

1. Clone this repository to your local machine.
2. Set the following **environment variables**:
```
SHOP_API_DB=localhost
SHOP_API_MAIL_HOST=smtp.office365.com
SHOP_API_MAIL_PASSWORD=your_password
SHOP_API_MAIL_PORT=587
SHOP_API_MAIL_USERNAME=your_username
```
3. Navigate to the root directory of the project.
4. Run the following command to launch the database container:
```
docker-compose -f docker-compose-db.yml up -d
```
4. Run the following command to build the application:
```
./mvnw clean install -DskipTests
```
5. Run the following command to start the application with test data:
```
./mvnw spring-boot:run -Ptestdata     
```

---

## Docker

If you just want to have the database container run the following command:
```
docker-compose -f docker-compose-db.yml up -d
```

If you want to run all the project using docker run the following commands:
- If the image does not exist we need to build it first then run docker compose
```
docker build -t spring-shop-api .
docker compose up -d
```
- If the image already exists we just need to run docker compose
```
docler compose up -d
```

---

## Swagger
To check api documentation launch the application and navigate to
[API Docs](http://localhost:8080/v3/api-docs).

To check swagger ui and all the available endpoints launch the application and navigate to [Swagger-UI](http://localhost:8080/swagger-ui/index.html#/).

## Usage

Once the application is running, you can make requests using **Postman**.