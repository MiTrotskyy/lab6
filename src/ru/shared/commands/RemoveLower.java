package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeing;
import ru.shared.HumanBeingController.HumanBeingMap;

import java.util.TreeMap;

/**
 * Класс, реализующий команду remove_lower id - удаление элементов, id которых ниже данного
 */

public class RemoveLower extends Command{
    private int id;
    /**
     * Проверка что id - целое число и вызов метода {@link HumanBeingMap#removeLower(int)}
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        TreeMap<Integer, HumanBeing> updatedMap = new TreeMap<>();
        humanBeingMap.getHumanBeingTreeMap().entrySet().stream()
                .filter(entry->entry.getValue().getId() >= id)
                .forEach(entry->updatedMap.put(entry.getKey(), entry.getValue()));
        humanBeingMap.setHumanBeingTreeMap(updatedMap);
        setMessage("Elements with id greater than " + id + " removed");
    }

    @Override
    public boolean isValid() {
        try{
            id = Integer.parseInt(getValue());
            return true;
        }catch (NumberFormatException e){
            System.out.println("Id must be a positive number");
            return false;
        }
    }
}
