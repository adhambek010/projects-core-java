package school.management.system.app08;

import java.util.ArrayList;

/**
 * Created by Adkham on 11.17.2023
 */
public class Main {
    public static void main(String[] args) {
        Teacher lizzy = new Teacher(111,"Lizzy",500);
        Teacher mellisa = new Teacher(222,"Melisa", 100);
        Teacher john = new Teacher(333, "John", 600);

        Student jamshid = new Student(112, "Jamshid", 4);
        Student rakshit = new Student(113, "Rakshit", 3);
        Student turdi = new Student(114, "Turdi", 4);

        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teachers.add(lizzy);
        teachers.add(mellisa);
        teachers.add(john);

        ArrayList<Student> students = new ArrayList<Student>();
        students.add(jamshid);
        students.add(rakshit);
        students.add(turdi);

        School school = new School(teachers,students);
        rakshit.payFees(5000);
        turdi.payFees(6000);
        jamshid.payFees(10000);

        System.out.println("School has earned : $"+school.getTotalMoneyEarned());
        System.out.println(school.getStudents());
        System.out.println(school.getTeachers());

    }
}
