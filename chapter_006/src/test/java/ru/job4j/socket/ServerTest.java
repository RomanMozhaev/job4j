package ru.job4j.socket;

import com.google.common.base.Joiner;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringJoiner;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServerTest {
    private final static String LS = System.lineSeparator();

    private void testServer(String input, String expect) throws IOException {
        Socket socket = mock(Socket.class);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.runServer();
        assertThat(out.toString(), is(expect));
    }

    @Test
    public void whenHelloThenYouAreDearFriend() throws IOException {
        String input = Joiner.on(LS).join("Hello Oracle", "exit");
        String expect = Joiner.on(LS).join(
                "Hello, dear friend, I'm an oracle." + LS,
                "I don't understand you",
                LS
                );
        testServer(input, expect);
    }

    @Test
    public void whenExitThenIdontUnderstand() throws IOException {
        String input = "exit";
        String expect = Joiner.on(LS).join(
                "I don't understand you",
                LS
        );
        testServer(input, expect);
    }

    @Test
    public void whenWhatThenWakeUp() throws IOException {
        String input = Joiner.on(LS).join("Hello Oracle", "exit");
        String expect = Joiner.on(LS).join(
                "Hello, dear friend, I'm an oracle." + LS,
                "I don't understand you",
                LS
        );
        testServer(input, expect);
    }

}