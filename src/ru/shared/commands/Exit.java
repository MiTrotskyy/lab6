package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий команду exit. Заканчивает выполнение программы.
 */
public class Exit extends Command{
    /**
     * Заканчивает выполнение программы вне зависимости от аргументов
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    public void execute(HumanBeingMap humanBeingMap) {
        setMessage("Logging out\n");
        humanBeingMap.saveHumanBeingMapInFile();
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
