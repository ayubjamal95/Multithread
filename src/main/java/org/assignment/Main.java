package org.assignment;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {
    private static final int MAX_MESSAGES_IN_QUEUE = 1;
    public static void main(String[] args) {
        BlockingQueue<String> firstToSecond = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);
        BlockingQueue<String> secondToFirst = new ArrayBlockingQueue<String>(MAX_MESSAGES_IN_QUEUE);

        Initiator firstPlayer = new Initiator(firstToSecond, secondToFirst);
        Player secondPlayer = new Player(secondToFirst, firstToSecond);


        Thread t1=  new Thread(firstPlayer);
        Thread t2 = new Thread(secondPlayer);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.exit(0);
        }
        catch (Exception e) {
            System.out.println("Interrupted");
        }

    }
}