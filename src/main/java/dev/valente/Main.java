package dev.valente;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dev.valente.rabbitmq.RabbitProducer;


import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, TimeoutException {

        Scanner scanner = new Scanner(System.in);
        RabbitProducer producer = new RabbitProducer();




        System.out.print("Escreva uma mensagem para o sistema consumidor:");
        String inputUser;

        ConnectionFactory factory = new ConnectionFactory();

        factory.setHost("localhost");



            try (Connection connection = factory.newConnection();
                 Channel channel = connection.createChannel()){



                String exchangeName = "Teste";
                String queueName = "hello";
                String routingKey = "algumaroutingkey";

                channel.exchangeDeclare(exchangeName, "direct", true);
                channel.queueDeclare(queueName, false, false, false, null);
                channel.queueBind(queueName, exchangeName, routingKey);

               byte[] messageBodyBytes = producer.toString().getBytes();
               channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);



//                channel.queueDeclare("hello", false, false, false, null);
//                channel.basicPublish("", "hello", null, inputUser.getBytes());
//                System.out.println(" [x] Sent ´" + inputUser + "´");

            }



    }
}