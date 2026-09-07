Day 5 - Dockerfile

Dockerfile used

FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY SchoolSystem.java .

RUN javac SchoolSystem.java

CMD \["java", "SchoolSystem"]



\Build command

docker build -t schoolsystem-app .



\ Run command

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

## SOLID Principles Applied

- **S**RP - Each class has one job: Person/Student/Teacher hold data and identity,
  PayrollRunner only handles paying, SchoolSystem only wires things together.
- **O**CP - New Payable types (e.g. a Contractor class) can be added without changing
  any existing class.
- **L**SP - Student and Teacher can always be used wherever a Person is expected.
- **I**SP - pay() lives only in Payable, so Student is never forced to implement it.
- **D**IP - PayrollRunner depends on the Payable interface, not on Teacher directly -
  proven by testPayrollRunnerWorksWithAnyPayable, which pays an unrelated DummyPayable
  class with no changes to PayrollRunner.
