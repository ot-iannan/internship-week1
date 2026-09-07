\## Getting Started



\### 1. Clone the repo

git clone https://github.com/ot-iannan/internship-week1.git

cd internship-week1/WeekOne/src



\### 2. Run the app directly (requires Java 21+ installed)

javac SchoolSystem.java

java SchoolSystem



Expected output:

I am Ama, a student studying Computer Science.

I am Mr. Kofi, a teacher.

Mr. Kofi has been paid GHS 3500.0



\### 3. Run the tests

javac SchoolSystem.java SchoolSystemTest.java

java SchoolSystemTest



Expected output:

testStudentIntroduction PASSED

testTeacherPay PASSED

DummyPayable has been paid

testPayrollRunnerWorksWithAnyPayable PASSED



\### 4. Build and run with Docker (no Java installation needed)

docker build -t schoolsystem-app .

docker run schoolsystem-app



Expected output (same as step 2):

I am Ama, a student studying Computer Science.

I am Mr. Kofi, a teacher.

