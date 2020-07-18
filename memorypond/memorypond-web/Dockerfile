FROM java:8

# Add Author info
LABEL maintainer="leejonghee342@gmail.com"

# Add a volume to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

#spring profile
ARG SPRING_PROFILE=${SPRING_PROFILE:-develop}
RUN echo "building ${SPRING_PROFILE} environment!!"

# The application's jar file
ARG JAR_FILE=/build/libs/memorypond-web-1.0.0-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} memorypond.jar

# Run the jar file
CMD java -jar /memorypond.jar --spring.profiles.active="${SPRING_PROFILE}"
#ENTRYPOINT ["java","-Dspring.profiles.active=develop","-jar","/memorypond.jar"]
