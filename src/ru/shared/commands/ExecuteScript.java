package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * Класс, реализующий команду execute_script filename, исполняющую команды из данного файла
 */
public class ExecuteScript extends Command{
    private Map<String, Command> commands = new TreeMap<String, Command>();
    private ArrayList<String> workingScripts = new ArrayList<>();

    public ExecuteScript(ArrayList<String> workingScripts) {
        this();
        this.workingScripts = workingScripts;
    }
    public ExecuteScript(){
        commands.put("help", new Help());
        commands.put("info", new Info());
        commands.put("show", new Show());
        commands.put("insert", new Insert());
        commands.put("update", new Update());
        commands.put("remove_key", new RemoveKey());
        commands.put("clear", new Clear());
        commands.put("exit", new Exit());
        commands.put("remove_greater", new RemoveGreater());
        commands.put("remove_lower", new RemoveLower());
        commands.put("remove_greater_key", new RemoveGreaterKey());
        commands.put("filter_by_mood", new FilterByMood());
        commands.put("filter_greater_than_car", new FilterGreaterThanCar());
        commands.put("print_descending", new PrintDescending());
    }

    /**
     * Переопределенный метод реализующий проверку на вызов сразу двух execute_script, если команда отстутсвует-создается
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap){
        String fileName = getValue();
        try (BufferedReader bufferedReader = new BufferedReader((new FileReader(fileName)))){
            workingScripts.add("execute_script " + fileName);
            String input = bufferedReader.readLine();
            while (input != null){
                if (input.contains("execute_script")){
                    if (workingScripts.contains(input)) {
                        updateMessage(input.split(" ")[1] + " is already working. Command skipped.\n");
                    }else{
                        Command command = new ExecuteScript(workingScripts);
                        System.out.println(input.split(" ")[1]);
                        command.setValue(input.split(" ")[1]);
                        command.execute(humanBeingMap);
                        this.updateMessage(command.getMessage());
                    }
                }
                else {
                    executeFromFile(input, humanBeingMap);
                }
                input = bufferedReader.readLine();
            }
        } catch (IOException ex){
            updateMessage("File "+ fileName + " not found or access denied.\n");
        } catch (Exception e) {
            updateMessage("Invalid input in execute_script "+ fileName + "\n");
            e.printStackTrace();
        }
        workingScripts.remove("execute_script " + fileName);
    }

    /**
     * переданная строка разбивеются на две строки(по пробелу), если вторая строка пустая, управление передаётся команде execute_script, в обратном случае, в качестве аргумента подаётся вторая строка
     * @param input
     * @param humanBeingMap
     */
    private void executeFromFile(String input, HumanBeingMap humanBeingMap){
        if (input.isEmpty()){
            return;
        }
        String[] values = input.split(" ");
        if (values.length == 1){
            String message;
            Command command = commands.get(values[0]);
            if (command != null){
                command.setValue(null);
                command.execute(humanBeingMap);
                message = command.getMessage();
            } else {
                message = "Команды не существует, команда пропущена\n";
            }
            updateMessage(message);
        }
        if (values.length == 2){
            String message;
            Command command = commands.get(values[0]);
            if (command != null) {
                command.setValue(values[1]);
                if (command.isValid()) {
                    command.execute(humanBeingMap);
                }
                message = command.getMessage();
            }else{
                message ="Команда не существует, команда пропущена\n";
            }
            updateMessage(message);
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
