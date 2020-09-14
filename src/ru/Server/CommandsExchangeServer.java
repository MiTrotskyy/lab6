package ru.Server;

import ru.shared.HumanBeingController.HumanBeingMap;
import ru.shared.commands.Command;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Класс CommandsExchangeServer реализующий получение, проверку и отправление обратно клиенту пакетиков
 */
public class CommandsExchangeServer {
    final int BYTE_ARRAY_SIZE = 100000;
    static Logger LOGGER = Logger.getLogger(CommandsExchangeServer.class.getName());
    private String name;
    private DatagramSocket datagramSocket;
    private HumanBeingMap humanBeingMap;

    /** Конструктор Commandexchangeserver
     * @throws IOException
     */

    public CommandsExchangeServer() throws IOException {
    }

    public void run() {
        try{
            System.out.println("Enter port");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferedReader.readLine();
            int port = Integer.parseInt(line);
            datagramSocket = new DatagramSocket(port);
            LOGGER.log(Level.INFO, "Datagram socket opened");
            humanBeingMap = new HumanBeingMap();
            LOGGER.log(Level.INFO, "HumanBeingMap object created");

            while (true) {
                byte[] buf = new byte[BYTE_ARRAY_SIZE];
                //Получение пакета от клиента
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(datagramPacket);
                LOGGER.log(Level.INFO, "Packet from client received");
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf));
                Command command = (Command) objectInputStream.readObject();
                objectInputStream.close();
                LOGGER.log(Level.INFO, "Received command from packet");
                //Проверка что всё в порядке
                command.execute(humanBeingMap);
                LOGGER.log(Level.INFO, "Command worked");

                //Отправка ответа клиенту
                InetAddress inetAddress = datagramPacket.getAddress();
                port = datagramPacket.getPort();
                LOGGER.log(Level.INFO, "Client adress and port received");
                buf = command.toByteArray();
                datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);
                LOGGER.log(Level.INFO, "Command serialised and put into packet");
                datagramSocket.send(datagramPacket);
                LOGGER.log(Level.INFO, "Packet with command send to client");
            }
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Exception happened");
            e.printStackTrace();
        } catch (NumberFormatException e){
            System.out.println("Invalid port");
        }
    }
}
