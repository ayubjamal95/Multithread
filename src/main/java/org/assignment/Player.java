package org.assignment;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;

public class Player implements Runnable {
    protected final BlockingQueue<String> sent;
    protected final BlockingQueue<String> received;
    private BigInteger numberOfMessagesSent = new BigInteger("1");
    private BigInteger predefinedMessages = new BigInteger("10");

    public Player(BlockingQueue<String> sent, BlockingQueue<String> received) {
        this.sent = sent;
        this.received = received;
    }

    @Override
    public void run() {

        while (!numberOfMessagesSent.equals(predefinedMessages)) {
            String receivedMessage = receive();
            reply(receivedMessage);
        }
    }

    public String receive() {
        String receivedMessage;
        try {
            receivedMessage = received.take();
        } catch (InterruptedException interrupted) {
            String error = String.format(
                    "Player [%s] failed to receive message on iteration [%d].",
                    this, numberOfMessagesSent);
            throw new IllegalStateException(error, interrupted);
        }
        return receivedMessage;
    }

    public void reply(String receivedMessage) {
        String reply = receivedMessage + " " + numberOfMessagesSent;
        try {
            sent.put(reply);
            System.out.printf("Player [%s]: [%s].%n", this, reply);
            numberOfMessagesSent = numberOfMessagesSent.add(BigInteger.ONE);
            Thread.sleep(1000);
        } catch (InterruptedException interrupted) {
            String error = String.format(
                    "Player [%s] failed to send message [%s] on iteration [%d].",
                    this, reply, numberOfMessagesSent);
            throw new IllegalStateException(error);
        }
    }
}
