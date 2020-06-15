package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;
import ru.shared.basicClasses.Mood;

import java.util.ArrayList;

/**
 * Класс, реализующий команду filter_by_mood mood, вывод элементов коллекции по значению поля mood
 */
public class FilterByMood extends Command{
    private Mood mood;
    /**
     * Значение проверяется на наличие в {@link Mood}, при наличии находятся и выводятся все элементы карты в {@link HumanBeingMap} у которых поле mood равно заданному
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
//        for (Map.Entry e : humanBeingMap.getHumanBeingTreeMap().entrySet()) {
//            if (humanBeingMap.getHumanBeingTreeMap().get(e.getKey()).getMood().equals(mood)) {
//                updateMessage("Key: " + e.getKey() + " Value: " + e.getValue().toString() + "\n");
//            }
//        }
        if (humanBeingMap.getHumanBeingTreeMap().isEmpty()){
            updateMessage("Collection is empty.");
        }else {
            humanBeingMap.getHumanBeingTreeMap().entrySet().stream()
                    .filter(entry -> entry.getValue().getMood().equals(mood))
                    .forEach(entry -> updateMessage("Key: " + entry.getKey() + " Value: " + entry.getValue().toString() + "\n"));
        }
    }

    @Override
    public boolean isValid() {
        ArrayList<String> moodList = Mood.getArrayList();
        try {
            if (!(moodList.contains(getValue().toUpperCase()))) {
                System.out.println("Invalid input. Please use this command with mood from the list: " + String.join(", ", moodList));
                return false;
            }else {
                mood = Mood.valueOf(getValue().toUpperCase());
                return true;
            }
        }catch(NullPointerException e){
            System.out.println("Invalid input. Please use this command with mood from the list: " + String.join(", ", moodList));
            return false;
        }
    }
}
