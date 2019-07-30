package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.*;
import java.net.Socket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ClientTest {

    private final static String LS = System.lineSeparator();

    private void testClient(String question, String answer) throws IOException {

        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream inConsole = new ByteArrayInputStream(question.getBytes());
        ByteArrayInputStream inServ = new ByteArrayInputStream(answer.getBytes());
        when(socket.getInputStream()).thenReturn(inServ);
        when(socket.getOutputStream()).thenReturn(out);
        System.setIn(inConsole);
        Client client = new Client(socket);
        client.runClient();
        assertThat(out.toString(), is(question + LS));
        System.setIn(System.in);
    }

    @Test
    public void whenExitThenExit() throws IOException {
        String question = "exit";
        String answer = Joiner.on(LS).join(
                "I don't understand you",
                LS
        );
        testClient(question, answer);
    }

    @Test
    public void whenHelloThenHello() throws IOException {
        String question = Joiner.on(LS).join(
                "Hello Oracle",
        "exit"
        );
        String answer = Joiner.on(LS).join(
                "Hello, dear friend, I'm an oracle." + LS,
                "I don't understand you",
                LS
        );
        testClient(question, answer);
    }


}