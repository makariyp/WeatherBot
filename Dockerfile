FROM amazoncorretto:17
LABEL authors="makariyp"
COPY ./build/libs/WeatherBot-0.0.1.jar ./WeatherBot-0.0.1.jar

CMD ["java", "-jar", "WeatherBot-0.0.1.jar"]