package ru.Client;

import ru.shared.commands.*;

import java.util.*;

public class ClientCommandData {

    private Command command;

    private static Map<String, Command> commandMap = new TreeMap<>();

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
    public boolean isValid(){
        return command.isValid();
    }
}
