FROM openjdk:11-jdk
RUN addgroup --system spring && adduser --system spring && adduser spring spring
USER spring:spring
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["sh", "-c", "java -Dtwilio.key=${TWILIO_KEY} -Dtwilio.phone.from=${TWILIO_PHONE_FROM} -Dtwilio.phone.to=${TWILIO_PHONE_TO} -Dtwilio.sid=${TWILIO_SID} -jar /app.jar"]