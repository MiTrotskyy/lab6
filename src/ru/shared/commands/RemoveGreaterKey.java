package ru.shared.commands;

import ru.shared.HumanBeingController.*;

import java.util.TreeMap;

/**
 * Класс, реализующий команду remove_greater_by_key key, удаляющуй все элементы у которых ключ превышает заданный
 */
public class RemoveGreaterKey extends Command{
    private Integer key;
    /**
     * Метод, который удаляет элементы у которых ключ превышает заданный
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        TreeMap<Integer, HumanBeing> updatedMap = new TreeMap<>();
        humanBeingMap.getHumanBeingTreeMap().keySet().stream()
                .filter(entry->entry <= key)
                .forEach(entry->updatedMap.put(key, humanBeingMap.getHumanBeingTreeMap().get(key)));
        humanBeingMap.setHumanBeingTreeMap(updatedMap);
        setMessage("Elements with key greater than " + key + " removed");
    }

    /** Переопределнный метод, который проверяет, что key-целое число
     * @return
     */
    @Override
    public boolean isValid() {
        try{
            key = Integer.parseInt(getValue());
            return true;
        }catch(NumberFormatException e){
            System.out.println("Key must be integer");
            return false;
        }
    }
}
