//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    // GOAL: different threads all count to million, then we calculate a total of billion, and then return how much time that takes
    public static void main(String [] args){

        long startTime = System.nanoTime();
        int a = 1;

        Thread [] array = new Thread[1000];
        int [] intArray = new int[1000];

        for (int i = 0; i < 1000; i++){
            int finalI = i;

            array[i] = new Thread() {
                @Override
                public void run() {
                    Integer num = 0;
                    for (int j = 0; j < 1000000; j++)
                        num += 1;
                    intArray[finalI] = num;
                }
            };
        }

        for (int i = 0; i < 1000; i++)
            array[i].start();

        for (int i = 0; i < 1000; i++){
            try {
                array[i].join();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        int total = 0;

        for (int i = 0; i < 1000; i++) {
            total += intArray[i];
        }

        System.out.println("The total is "+total);
        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Duration "+duration/1000000000.0+ " seconds");

        startTime = System.nanoTime();
        int timer = 0;
        for (int i = 1;i <= 1000000000; i++){
            timer += 1;
        }

        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Duration "+duration/1000000000.0 + " seconds");
        System.out.println("Timer total "+timer);
        
        System.out.println("lets see if this branch stuff wnats to work today or if it wants me to cry");
    }

}