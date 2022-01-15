FROM amazoncorretto:17.0.0-alpine

COPY /*.jar /deployment/app.jar

ENTRYPOINT java -jar $JAVA_OPTS /deployment/app.jar