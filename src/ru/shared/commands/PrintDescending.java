package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий команду print_descending - вывод элементов по убыванию в строковом представлении
 */

public class PrintDescending extends Command{
    /**
     * Проверка коллекции на пустоту, а затем вывод элементов по убыванию значения ключей с ключами
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        if (humanBeingMap.getHumanBeingTreeMap().isEmpty()) {
            updateMessage("Collection is empty.");
        } else {
            for (int key : humanBeingMap.getHumanBeingTreeMap().descendingKeySet()) {
                updateMessage("Key: " + key + " Value: " + humanBeingMap.getHumanBeingTreeMap().get(key).toString() + "\n");
            }
        }
    }


    @Override
    public boolean isValid() {
        return true;
    }
}
