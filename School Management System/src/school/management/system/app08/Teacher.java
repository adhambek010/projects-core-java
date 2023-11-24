package school.management.system.app08;

/**
 * Created by Adkham 11.16.2023
 * This class is responsible for keeping the track of the teacher's name, id and salary.
 */
public class Teacher {
    /**
     * Unique Id for the teachers
     */
    private int id;
    /**
     * Teachers name
     */
    private String name;
    /**
     * Teachers salary
     */
    private int salary;

    /**
     * Creates teachers object by initializing values of the fields;
     * @param id The unique id for every teacher.
     * @param name The name of the teacher
     * @param salary Salary of the teacher
     */
    public Teacher(int id, String name, int salary){
        this.id = id;
        this.name = name;
        this.salary = salary;
    }
    /**
     * This method for getting Teacher objects unique id.
     * @return The unique id
     */
    public int getId(){
        return this.id;
    }
    /**
     * This method for getting Teacher object's Name
     * @return the name of the teacher
     */
    public String getName(){
        return this.name;
    }
    /**
     * This method for getting Teacher object's salary
     * @return the teacher's salary
     */
    public int getSalary(){
        return this.salary;
    }

    /**
     * set the new updated salary to the teacher
     * @param salary
     */
    public void  setSalary(int salary){
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}'+"\n";
    }
}
