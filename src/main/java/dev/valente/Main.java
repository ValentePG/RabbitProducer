package dev.valente;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, TimeoutException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Escreva uma mensagem para o sistema consumidor:");
        String inputUser;

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");

        do {
            inputUser = scanner.nextLine();
            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()){

                channel.queueDeclare("hello", false, false, false, null);
                channel.basicPublish("", "hello", null, inputUser.getBytes());
                System.out.println(" [x] Sent ´" + inputUser + "´");
            }
        }  while(!inputUser.equals("0"));


    }
}