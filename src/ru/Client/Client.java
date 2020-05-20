package ru.Client;

import ru.shared.commands.*;

import java.net.*;
import java.io.*;
import java.nio.channels.DatagramChannel;
/**
 * Класс Client для считыания команд, передачи их для выполнения на сервер и вывода результата выполнения
 */


public class Client {
    public static void main(String[] args) throws Exception{
        ClientCommandData clientCommandData = new ClientCommandData();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        DatagramChannel datagramChannel = null;
        DatagramSocket datagramSocket = null;
        datagramChannel = DatagramChannel.open();
        int port = 8000;
        InetAddress inetAddress = InetAddress.getLocalHost();
        datagramChannel.bind(null);
        while (true) {
            System.out.println("Please, enter your command, to get full list of commands, use \"help\" command.");
            String line = bufferedReader.readLine();
            if (!line.isEmpty()) {
                clientCommandData.setCommand(line);
                if (clientCommandData.getCommand() != null && clientCommandData.isValid()) {
                    Command command = clientCommandData.getCommand();
                    byte[] sendBuf;
                    byte[] receiveBuf = new byte[100000];
                    datagramSocket = datagramChannel.socket();
                    sendBuf = command.toByteArray();
                    DatagramPacket datagramPacket = new DatagramPacket(sendBuf, sendBuf.length, inetAddress, port);
                    datagramSocket.send(datagramPacket);
                    datagramPacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                    datagramSocket.receive(datagramPacket);
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(receiveBuf));
                    command = (Command) objectInputStream.readObject();
                    objectInputStream.close();
                    System.out.println("");
                    System.out.print(command.getMessage());
                    if (command.getMessage().contains("Logging out")){
                        System.exit(0);
                    }
                }
            }
        }
    }
}
