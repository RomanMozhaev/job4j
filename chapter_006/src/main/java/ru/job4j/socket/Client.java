package ru.job4j.socket;

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * The Client side of hte Oracle program
 * the server return the Oracle's answers on your questions
 */
public class Client {

    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public void runClient() throws IOException {
        String answer;
        String question;
        PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
        Scanner console = new Scanner(System.in);
        do {
            question = console.nextLine();
            out.println(question);
            answer = in.readLine();
            while (!answer.isEmpty()) {
                System.out.println(answer);
                answer = in.readLine();
            }
        } while (!question.equals("exit"));
    }

    public static void main(String[] args) {
        try (Socket socket = new Socket(InetAddress.getByName("127.0.0.1"), 15432)) {
            new Client(socket).runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
