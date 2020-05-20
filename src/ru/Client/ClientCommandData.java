package ru.Client;

import ru.shared.commands.*;

import java.util.*;
/**
 * Класс ClientCommandData для управления командами
 */


public class ClientCommandData {
    /** Поле comand */
    private Command command;
    /** Карта команд. Ключ - строковое название команды. Значение - классы, реализующие интерфейс Command*/
    private static Map<String, Command> commandMap = new TreeMap<>();
    /** Конструктор {@link ClientCommandData#commandMap}командами и их строковыми названиями*/
    public ClientCommandData(){
        commandMap.put("help", new Help());
        commandMap.put("info", new Info());
        commandMap.put("show", new Show());
        commandMap.put("insert", new Insert());
        commandMap.put("update", new Update());
        commandMap.put("remove_key", new RemoveKey());
        commandMap.put("clear", new Clear());
        commandMap.put("execute_script", new ExecuteScript());
        commandMap.put("exit", new Exit());
        commandMap.put("remove_greater", new RemoveGreater());
        commandMap.put("remove_lower", new RemoveLower());
        commandMap.put("remove_greater_key", new RemoveGreaterKey());
        commandMap.put("filter_by_mood", new FilterByMood());
        commandMap.put("filter_greater_than_car", new FilterGreaterThanCar());
        commandMap.put("print_descending", new PrintDescending());
    }
    public Command getCommand(){
        return this.command;
    }
/**
 * переданная строка разбивеются на две строки(по пробелу), если вторая строка пустая, управление передаётся команде с введённым названием и аргументом null, в обратном случае, в качестве аргумента подаётся вторая строка
 * @param input строчка, введённая пользователем*/

    public void setCommand(String input){
        if (input.isEmpty()){
            return;
        }
        this.command = null;
        String[] values = input.split(" ");
        if (values.length == 1){
            Command command = commandMap.get(values[0]);
            if (command != null){
                command.setValue(null);
                this.command = command;
            } else {
                System.out.println("Команды не существует");
            }

        }
        if (values.length == 2){
            Command command = commandMap.get(values[0]);
            if (command != null) {
                command.setValue(values[1]);
                this.command = command;
            } else{
                System.out.println("Команда не существует");
            }
        }
    }

    /**
     * Метод, котороый возвращает true, если введенные данные возможно использовать с командой, и false,если нельзя
     * @return true, если введенные данные возможно использовать с командой
     * @return false, если введенные данные нельзя использовать с командой
     */
    public boolean isValid(){
        return command.isValid();
    }
}
