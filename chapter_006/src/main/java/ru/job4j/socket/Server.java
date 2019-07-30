package ru.job4j.socket;

import java.io.*;
import java.net.*;

public class Server {

    public final Socket socket;

    public Server(Socket socket) {
        this.socket = socket;
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
        String answer;
        switch (ask) {
            case "Hello Oracle" :
                answer = "Hello, dear friend, I'm an oracle.";
                break;
            case "Say my name" :
                answer = "My name";
                break;
            case "Which weekday is today?" :
                answer = "Please, see the calendar, I am tired";
                break;
            case "What do you know about my future?" :
                answer = "I am almost sure, you will wake up tomorrow morning";
                break;
            default:
                answer = "I don't understand you";
                break;
        }
        answer = answer + System.lineSeparator();
        return answer;
    }

    public static void main(String[] args) {
        try (Socket socket = new ServerSocket(15432).accept()) {
            new Server(socket).runServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
