FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY Task.java TaskManager.java TaskStorage.java Main.java .

RUN javac Task.java TaskManager.java TaskStorage.java Main.java

WORKDIR /data

ENTRYPOINT ["java", "-cp", "/app", "Main"]