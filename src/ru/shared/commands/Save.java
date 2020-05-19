package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий команду save - сохранение коллекции в файл
 */

public class Save {
    /**
     * Вызов метода {@link HumanBeingMap#saveHumanBeingMapInFile()}
     * @param value строковое значение, аргумент команды
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */

    public void execute(String value, HumanBeingMap humanBeingMap){
        humanBeingMap.saveHumanBeingMapInFile();

    }
}
