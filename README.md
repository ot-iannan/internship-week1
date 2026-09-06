\## Day 5 - Dockerfile



\### Dockerfile used

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY SchoolSystem.java .

RUN javac SchoolSystem.java

CMD \["java", "SchoolSystem"]



\### Build command

docker build -t schoolsystem-app .



\### Run command

docker run schoolsystem-app



\### Input

No user input required - the program runs a fixed School system demo

(creates a Student and Teacher, calls introduce() on both).



\### Output

I am Ama, a student studying Computer Science.

I am Mr. Kofi, a teacher.



\### Notes

\- Base image eclipse-temurin:21-jdk was chosen since it already includes

&#x20; the JDK, avoiding the need to install Java manually inside the image.

\- RUN compiles the code once during the image build; CMD defines what runs

&#x20; each time a new container starts from this image.

