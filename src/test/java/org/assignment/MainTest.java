package org.assignment;

import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MainTest {

    @Test
    void testInitiatorSendInitMessage() {
        BlockingQueue<String> sent = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> received = new ArrayBlockingQueue<>(1);

        Initiator initiator = new Initiator(sent, received);
        Initiator spyInitiator = Mockito.spy(initiator);

        // Simulate sending the initial message
        spyInitiator.sendInitMessage();

        // Verify that the sent queue contains the correct initial message
        String sentMessage = sent.poll();
        assertEquals("message 0", sentMessage);
    }

    @Test
    void testInitiatorSendMessageAndReceiveReply() throws InterruptedException {
        BlockingQueue<String> sent = new ArrayBlockingQueue<>(1);
        BlockingQueue<String> received = new ArrayBlockingQueue<>(1);

        Initiator initiator = new Initiator(sent, received);
        Player player = new Player(received, sent);

        Initiator spyInitiator = Mockito.spy(initiator);
        Player spyPlayer = Mockito.spy(player);
        Thread initiatorThread = new Thread(spyInitiator);
        Thread playerThread = new Thread(spyPlayer);
        initiatorThread.start();
        playerThread.start();
        Thread.sleep(5000);

        Mockito.verify(spyPlayer).reply("message 0");
        Mockito.verify(spyInitiator).reply("message 0 1");

        Mockito.verify(spyPlayer, Mockito.times(5)).reply(ArgumentMatchers.anyString());
        Mockito.verify(spyInitiator, Mockito.times(5)).reply(ArgumentMatchers.anyString());

        assertEquals("message 0 1 1 2 2 3 3 4 4 5 5", sent.take());
    }

}





