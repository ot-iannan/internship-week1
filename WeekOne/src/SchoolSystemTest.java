public class SchoolSystemTest {
    public static void main( String[] args){
        testStudentIntroduction();
        testTeacherPay();
        testPayrollRunnerWithAnyPayable();

    }
    static void testStudentIntroduction() {
        Student student = new Student("Alice", "Computer Science");
        String expected = "Alice";

        if (student.getName().equals(expected)){
            System.out.println("testStudentIntroduction passed");
        } else {
            System.out.println("testStudentIntroduction failed");

        }

    }

    static void testTeacherPay(){
        Teacher teacher = new Teacher ("Bob", 50000);

        if(teacher.getName().equals("Bob") && teacher instanceof Payable){
            System.out.println("testTeacherPay passed");

        } else {
            System.out.println("testTeacherPay failed");
        }
    }

    static void testPayrollRunnerWithAnyPayable(){
        Payable dummy = new DummyPayable();
        PayrollRunner runner= new PayrollRunner(dummy);
        runner.run();

        if (dummy instanceof Payable){
            System.out.println("testPayrollRunnerWithAnyPayable passed");

        } else {
            System.out.println("testPayrollRunnerWithAnyPayable failed");
        }
    }



}

class DummyPayable implements Payable{
    public void pay(){
        System.out.println("DummyPayable has been paid");
    }
}
