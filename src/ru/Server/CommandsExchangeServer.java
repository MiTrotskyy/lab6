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
    static Logger LOGGER = Logger.getLogger(CommandsExchangeServer.class.getName());
    private String name;
    private DatagramSocket datagramSocket;
    private HumanBeingMap humanBeingMap;

    /** Конструктор Commandexchangeserver
     * @throws IOException
     */

    public CommandsExchangeServer() throws IOException {
        LOGGER.log(Level.INFO, "Открытие сокета");
        datagramSocket = new DatagramSocket(8000);
        LOGGER.log(Level.INFO, "Создание объекта класса HumanBeingMap");
        humanBeingMap = new HumanBeingMap();
    }

    public void run() {
        try {
            LOGGER.log(Level.INFO, "");
            datagramSocket.setSoTimeout(60000);
            while (true) {
                byte[] buf = new byte[100000];
                //Получение пакета от клиента
                DatagramPacket datagramPacket = new DatagramPacket(buf, buf.length);
                datagramSocket.receive(datagramPacket);
                LOGGER.log(Level.INFO, "Получен пакет от клиента");
                ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(buf));
                Command command = (Command) objectInputStream.readObject();
                objectInputStream.close();
                LOGGER.log(Level.INFO, "Из пакета получена команда");
                //Проверка что всё в порядке
                command.execute(humanBeingMap);
                LOGGER.log(Level.INFO, "Команда выполнена");

                //Отправка ответа клиенту
                InetAddress inetAddress = datagramPacket.getAddress();
                int port = datagramPacket.getPort();
                LOGGER.log(Level.INFO, "Получен адрес и порт клиента");
                buf = command.toByteArray();
                datagramPacket = new DatagramPacket(buf, buf.length, inetAddress, port);
                LOGGER.log(Level.INFO, "Команда сериалезирована и засунута в пакет");
                datagramSocket.send(datagramPacket);
                LOGGER.log(Level.INFO, "Пакет с командой отправлен");
            }
        } catch (SocketTimeoutException e) {
            datagramSocket.close();
            LOGGER.log(Level.INFO, "Канал закрыт");
            System.out.println("Истекло время ожидания, приём данных окончен");
        } catch (IOException | ClassNotFoundException e) {
            LOGGER.log(Level.WARNING, "Что-то пошло не так");
            e.printStackTrace();
        }
    }
}
