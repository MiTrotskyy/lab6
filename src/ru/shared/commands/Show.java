package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

import java.util.Map;

/**
 * Класс, реализующий команду show - вывод элементов коллекции в строковом представлении
 */

public class Show extends Command{
    /**
     * Проверка коллекции на пустоту, а затем вывод элементов с ключами
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */

    public void execute(HumanBeingMap humanBeingMap) {
        if (humanBeingMap.getHumanBeingTreeMap().isEmpty()){
            setMessage("Collection is empty.");
        }else {
            setMessage("");
            for (Map.Entry e : humanBeingMap.getHumanBeingTreeMap().entrySet()){
                updateMessage("Key: " + e.getKey() + " Value: " + e.getValue().toString() + "\n");
            }
        }
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
