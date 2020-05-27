package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий функцию filter_greater_than_car carName, выводящий элементы, у которых поле car больше заданного
 */

public class FilterGreaterThanCar extends Command{
    private String carName;
    /**
     * Метод рализующий проверку коллекции на пустоту и вывод элементов,если поле Car больше заданного
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        if (humanBeingMap.getHumanBeingTreeMap().isEmpty()){
            updateMessage("Collection is empty.");
        }else {
            humanBeingMap.getHumanBeingTreeMap().entrySet().stream()
                    .filter(entry -> entry.getValue().getCar().getName().compareTo(carName) > 0)
                    .forEach(entry -> updateMessage("Key: " + entry.getKey() + " Value: " + entry.getValue().toString() + "\n"));
        }
    }

    /** Переопределнный метод справляется с ошибкой, которая возникает при заполненным полем car
     * @return
     */
    @Override
    public boolean isValid() {
        if (getValue().isEmpty()) {
            System.out.println("Invalid input. Field must not be empty.");
            return false;
        }else{
            carName = getValue();
            return true;
        }
    }
}
