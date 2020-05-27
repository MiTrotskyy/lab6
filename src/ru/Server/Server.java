package ru.Server;

/** Класс Server */
public class Server {
    /**
     * Метод реализующий метод run класса CommandsExchangeserver
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new CommandsExchangeServer().run();
    }
}
