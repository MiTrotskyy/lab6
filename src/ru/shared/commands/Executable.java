package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * интерфейс для работы с командами
 */

public interface Executable {
    /**
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    void execute(HumanBeingMap humanBeingMap);
}
