package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий команду help - вывод информации о коллекции в строковом представлении
 */
public class Info extends Command{
    /**
     * Обращение к методу {@link HumanBeingMap#toString()}
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        setMessage(humanBeingMap.toString());
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
