FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY SchoolSystem.java .
RUN javac SchoolSystem.java
CMD ["java", "SchoolSystem"]

