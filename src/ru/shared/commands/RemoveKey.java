package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeingMap;

/**
 * Класс, реализующий команду remove_key key - удаление элемента по ключу
 */

public class RemoveKey extends Command{
    private Integer key;
    /**
     * Метод,который реализует проверку на наличие элменетта с заданным ключом и
     * его удаление с помощью метода{@Link HumanBeingMap#removeHumanBeing}
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        if (humanBeingMap.getHumanBeingTreeMap().get(key) == null){
            setMessage("Element with this key doesn't exist. To get a list of elements use \"show\" command.");
        }else{
            setMessage("Element " + humanBeingMap.getHumanBeingTreeMap().get(key).toString() + " removed");
            humanBeingMap.removeHumanBeing(key);
        }
    }
    /** Переопределнный метод,который проверяет, что key-целое число
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
