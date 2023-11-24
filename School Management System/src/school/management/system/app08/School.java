package school.management.system.app08;

import java.util.ArrayList;

/**
 * Created by Adkham 11.16.2023.
 */
public class School {
    private ArrayList<Teacher> teachers;
    private ArrayList<Student> students;
    private static int totalMoneyEarned;
    private static int getTotalMoneySpent;

    /**
     * New school object is created
     * @param teachers container of the teachers in the school
     * @param students container of the students in the school
     */
    public School(ArrayList<Teacher> teachers, ArrayList<Student> students) {
        this.teachers = teachers;
        this.students = students;
        this.totalMoneyEarned = 0;
        this.getTotalMoneySpent = 0;
    }

    /**
     *
     * @return the list of teachers in the school
     */
    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    /**
     * Add teachers to the shcool
     * @param teachers will be added
     */
    public void addTeachers(Teacher teachers) {
        this.teachers.add(teachers);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    /**
     *
     * @param student  list of students in the school
     */
    public void addStudent(Student student) {
        this.students.add(student);
    }

    /**
     *
     * @return the total money earned by the school
     */
    public static int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }

    /**
     * Adds the total mo
     * @param totalMoneyEarned
     */
    public static void updeteTotalMoneyEarned(int moneyEarned) {
        totalMoneyEarned += moneyEarned;
    }

    /**
     *
     * @return the total money spent by the school.
     */
    public int getGetTotalMoneySpent() {
        return getTotalMoneySpent;
    }

    /**
     * update the money that is spent by the school\
     * which is the salary given by the school to its teachers.
     * @param moneySpent the money
     */
    public void updateTotalMoneySpent(int moneySpent) {
        totalMoneyEarned -= moneySpent;
    }
}
