import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your buying price per share : ");
        double buyingPrice = sc.nextDouble();
        double closingPrice = 0.1;
        int today = 1;
        while (true){
            System.out.println("Enter the closing price for day " + today);
            closingPrice = sc.nextDouble();
            if (closingPrice < 0.0)
                break;
            double earnings = closingPrice - buyingPrice;
            if (earnings > 0){
                System.out.println("After day " + today + ", you earned " + earnings + " per share.");
            } else if (earnings < 0.0) {
                System.out.println("After day " + today +", you earned " + (-earnings) + " per share.");
            }else {
                System.out.println("After day " + today + ", you have " + "no earnings per share.");
            }
            today ++;
        }
        sc.close();
    }
}
