//Exercise 1: Creating Threads Using the `Thread` Class
class NumberThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("NumberThread: " + i);
        }
    }
}

//Exercise 2: Creating Threads Using the `Runnable` Interface
class LetterRunnable implements Runnable {
    public void run() {
        char letter = 'A';
        for (int i = 0; i < 5; i++) {
            System.out.println("LetterRunnable: " + (char) (letter + i));
        }
    }
}

//Exercise 4: Managing Multiple Threads
class TimestampRunnable implements Runnable {
    public void run() {
        System.out.println("TimestampRunnable: " + System.currentTimeMillis() + " - " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) throws InterruptedException {
        //Exercise 1
        System.out.println("Starting Exercise 1");
        NumberThread numberThread = new NumberThread();
        numberThread.start();
        numberThread.join();
        System.out.println("Finished Exercise 1\n");

        //Exercise 2
        System.out.println("Starting Exercise 2");
        Thread letterThread = new Thread(new LetterRunnable());
        letterThread.start();
        letterThread.join();
        System.out.println("Finished Exercise 2\n");

        //Exercise 3: Demonstrating Thread States
        System.out.println("Starting Exercise 3");
        Thread stateThread = new Thread(() -> {
            try {
                System.out.println("Exercise 3 - Inside run method, Thread state: " + Thread.currentThread().getState());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted");
            }
        });
        System.out.println("Exercise 3 - Before start, Thread state: " + stateThread.getState());
        stateThread.start();
        Thread.sleep(100);
        System.out.println("Exercise 3 - After start, Thread state: " + stateThread.getState());
        Thread.sleep(200);
        System.out.println("Exercise 3 - During sleep, Thread state: " + stateThread.getState());
        stateThread.join();
        System.out.println("Exercise 3 - After completion, Thread state: " + stateThread.getState());
        System.out.println("Finished Exercise 3\n");

        //Exercise 4
        System.out.println("Starting Exercise 4");
        for (int i = 0; i < 5; i++) {
            new Thread(new TimestampRunnable(), "Thread-" + i).start();
        }
        Thread.sleep(1000);
        System.out.println("Finished Exercise 4\n");

        //Exercise 5: Thread Interruption
        System.out.println("Starting Exercise 5");
        Thread infiniteLoopThread = new Thread(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println("Exercise 5 - Running in loop");
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.println("Exercise 5 - Thread interrupted");
            }
        });
        infiniteLoopThread.start();
        Thread.sleep(3000);
        infiniteLoopThread.interrupt();
        infiniteLoopThread.join();
        System.out.println("Finished Exercise 5");
    }
}
