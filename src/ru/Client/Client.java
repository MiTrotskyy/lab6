package ru.Client;

import ru.shared.commands.*;

import java.net.*;
import java.io.*;
import java.nio.channels.DatagramChannel;
/**
 * Класс Client для считывания команд, передачи их для выполнения на сервер и вывода результата выполнения
 */


public class Client {
    public static void main(String[] args) throws Exception {
        ClientCommandData clientCommandData = new ClientCommandData();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        DatagramChannel datagramChannel = null;
        DatagramSocket datagramSocket = null;
        datagramChannel = DatagramChannel.open();
        String line;
        int port = 8000;
        InetAddress inetAddress;
        try {
            System.out.println("Enter port");
            line = bufferedReader.readLine();
            port = Integer.parseInt(line);
        }catch (NumberFormatException e){
            System.out.println("Invalid port. Port now set to 8000");
        }
        try {
            System.out.println("Enter hostname");
            line = bufferedReader.readLine();
            inetAddress =  InetAddress.getByName(line);
        }catch(UnknownHostException e){
            System.out.println("IP for this host not found. Client is now working on localhost");
            inetAddress = InetAddress.getLocalHost();
        }
        datagramChannel.bind(null);
        datagramSocket = datagramChannel.socket();
        while (true) {
                System.out.println("Please, enter your command, to get full list of commands, use \"help\" command.");
                line = bufferedReader.readLine();
                if (!line.isEmpty()) {
                    clientCommandData.setCommand(line);
                    if (clientCommandData.getCommand() != null && clientCommandData.isValid()) {
                        Command command = clientCommandData.getCommand();
                        byte[] sendBuf;
                        byte[] receiveBuf = new byte[100000];
                        sendBuf = command.toByteArray();
                        DatagramPacket datagramPacket = new DatagramPacket(sendBuf, sendBuf.length, inetAddress, port);
                        datagramSocket.send(datagramPacket);
                        datagramSocket.setSoTimeout(60000);
                        try {
                            datagramPacket = new DatagramPacket(receiveBuf, receiveBuf.length);
                            datagramSocket.receive(datagramPacket);
                            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(receiveBuf));
                            command = (Command) objectInputStream.readObject();
                            objectInputStream.close();
                            System.out.println("");
                            System.out.print(command.getMessage());
                            if (command.getMessage().contains("Logging out")) {
                                datagramChannel.close();
                                System.exit(0);
                            }
                        } catch (SocketTimeoutException e) {
                            System.out.println("No response from server.");
                        }
                    }
                }
            }
        }
    }
