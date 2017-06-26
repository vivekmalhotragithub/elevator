FROM java:8
VOLUME /images
ADD elevator-1.0-SNAPSHOT.jar elevator-1.0.jar
RUN bash -c 'touch /elevator-1.0.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/elevator-1.0.jar"]