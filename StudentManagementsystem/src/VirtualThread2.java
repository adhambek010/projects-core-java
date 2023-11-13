public class VirtualThread2 {
    public static void main(String[] args) {
       Runnable runnable = new Runnable() {
           @Override
           public void run() {
               for(int i = 1; i <= 5; i++){
                   System.out.println("Index : "+i);
               }
           }
       };
       Thread t1 = new Thread.ofVirtual().start(runnable);
    }
}
