package school.management.system.app08;

/**
 * Created by Adkham on 11.16.2023
 * This class is responsible for keeping the track of students
 */
public class Student {
    /**
     * unique id for every student
     */
    private int id;
    /**
     * Students name
     */
    private String sName;
    /**
     * Grade of student
     */
    private int grade;
    /**
     * Fees that paid by Student
     */
    private int feesTotal;
    /**
     * The paid fee for the student
     */
    private int feesPaid;

    /**
     * Constructor creates a student object by initializing fields.
     * @param id Id of the student
     * @param sName Name of the student
     * @param grade grade of the student
     */
    public Student(int id, String sName, int grade) {
        this.feesTotal = 0;
        this.id = id;
        this.sName = sName;
        this.grade = grade;
        this.feesPaid = 0;
    }
    /**
     * upgrade the grade for the student
     * @param grade sets the new grade for the student.
     */
    public void setGrade(int grade){
        this.grade = grade;
    }

    /**
     * Keep adding the fees to feesPaid Field
     * Add the fees to the fees paid.
     * The school will receive the funds.
     * @param fees
     */
    public void payFees(int fees){
        feesPaid += fees;
        School.updeteTotalMoneyEarned(feesPaid);;
    }

    /**
     *  getting student id outside the class
     *  this method for encapsulation
     * @return id of the Student
     */
    public int getId() {
        return id;
    }

    /**
     * getting student name outside the class
     * for hiding data (encapsulation)
     * @return Name of the student
     */
    public String getsName() {
        return sName;
    }

    /**
     * getting student grade
     * @return student grade
     */
    public int getGrade() {
        return grade;
    }

    /**
     * getting total amount of the payment
     * @return total amount of the payment
     */
    public int getFeesTotal() {
        return feesTotal;
    }

    /**
     * getting last payed payment
     * @return paid fee
     */
    public int getFeesPaid() {
        return feesPaid;
    }

    /**
     *
     * @return the remaining fees.
     */
    public int getRemainingFees(){
        return this.feesTotal - this.feesPaid;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sName='" + sName + '\'' +
                ", grade=" + grade +
                ", feesTotal=" + feesTotal +
                ", feesPaid=" + feesPaid +
                '}'+"\n";
    }
}
