package ru.job4j.socket;

import java.io.*;
import java.net.*;
import java.util.HashMap;

public class Server {

    private final Socket socket;
    private final HashMap<String, String> map = new HashMap<>();

    public Server(Socket socket) {
        this.socket = socket;
        this.initAnswersMap();
    }

    public void runServer() throws IOException {
        String ask;
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        do {
            System.out.println("wait command ...");
            ask = in.readLine();
            System.out.println(ask);
            out.println(getAnswer(ask));
        } while (!"exit".equals(ask));
    }

    private String getAnswer(String ask) {
        return this.map.getOrDefault(ask, "I don't understand you") + System.lineSeparator();
    }

    private void initAnswersMap() {
        this.map.put("Hello Oracle", "Hello, dear friend, I'm an oracle.");
        this.map.put("Say my name", "My name");
        this.map.put("Which weekday is today?", "Please, see the calendar, I am tired");
        this.map.put("What do you know about my future?", " am almost sure, you will wake up tomorrow morning");
    }



    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(15432).accept()) {
            new Server(socket).runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
