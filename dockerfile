FROM amazoncorretto:17
ENV SHOP_API_MAIL_HOST=test1
ENV SHOP_API_MAIL_PORT=1234
ENV SHOP_API_MAIL_USERNAME=test3
ENV SHOP_API_MAIL_PASSWORD=test4
ENV SHOP_DB_HOST=spring-shop-api-db
WORKDIR /app
COPY target/spring-shop-api-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "spring-shop-api-0.0.1-SNAPSHOT.jar"]