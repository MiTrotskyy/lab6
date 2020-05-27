package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeing;
import ru.shared.HumanBeingController.HumanBeingMap;

import java.util.TreeMap;

/**
 * Класс, реализующий команду remove_greater id - удаляет элементы с id выше данного
 */
public class RemoveGreater extends Command{
    private int id;
    /**
     * Проверка что id - целое число и вызов метода {@link HumanBeingMap#removeGreater(int)}
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
//        humanBeingMap.removeGreater(id);
//            Collection collection = humanBeingTreeMap.keySet();
//            TreeMap<Integer, HumanBeing> lowerMap = new TreeMap<>();
//            for (Object o : collection) {
//                int key = (int) o;
//                if (humanBeingTreeMap.get(key).getId() <= id) {
//                    lowerMap.put(key, humanBeingTreeMap.get(key));
//                }
//            }
//            humanBeingTreeMap = lowerMap;
//        humanBeingMap.removeGreater(id);
        TreeMap<Integer, HumanBeing> updatedMap = new TreeMap<>();
        humanBeingMap.getHumanBeingTreeMap().entrySet().stream()
                .filter(entry->entry.getValue().getId() <= id)
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
