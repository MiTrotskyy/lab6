package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeing;
import ru.shared.HumanBeingController.HumanBeingMap;
import ru.shared.HumanBeingController.HumanBeingReader;

/**
 * Класс, реализующий команду insert key {element} - вставка в коллекцию элемента по ключу
 */

public class Insert extends Command{
    private Integer key;
    private HumanBeing humanBeing;
    /**
     * Создаётся объект класса {@link HumanBeingReader}, из него HumanBeing добавляется в коллекцию
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
        if (humanBeingMap.getHumanBeingTreeMap().get(key) == null) {
            humanBeingMap.addHumanBeing(key, humanBeing);
            setMessage("Element "+ humanBeing.toString() + " added in collection");
        }else{
            setMessage("Element with this key already exist. First delete it with \"remove_key\" command.");
        }
    }

    @Override
    public boolean isValid() {
        try{
            key = Integer.parseInt(getValue());
            HumanBeingReader humanBeingReader = new HumanBeingReader();
            humanBeing = humanBeingReader.getHumanBeing();
            return true;
        }catch(NumberFormatException e){
            System.out.println("Key must be integer");
            return false;
        }
    }
}