package ru.shared.commands;

import ru.shared.HumanBeingController.HumanBeing;
import ru.shared.HumanBeingController.HumanBeingMap;
import ru.shared.HumanBeingController.HumanBeingReader;

/**
 * Класс, реализующий команду update id - обновление элемента по id
 */

public class Update extends Command{
    private Long id;
    private HumanBeing humanBeing;
    /**
     * Проверка на наличие элемента с данным id, затем вызов метода {@link HumanBeingMap#updateHumanBeing(Long, Integer, HumanBeing)}
     * @param humanBeingMap класс с коллекцией, над которой производятся действия
     */
    @Override
    public void execute(HumanBeingMap humanBeingMap) {
            Integer key = humanBeingMap.getKeyById(id);
            if (key == null){
                setMessage("Element with this id doesn't exist. To get a list of elements use \"show\" command.\n");
            }else {
                humanBeingMap.updateHumanBeing(id, key, humanBeing);
                setMessage("Element " + humanBeing.toString() + " updated\n");
            }
    }

    /**Метод проверяет, чтобы id было целым числом
     * @return
     */
    @Override
    public boolean isValid() {
        try{
            id = Long.parseLong(getValue());
            HumanBeingReader humanBeingReader = new HumanBeingReader();
            humanBeing = humanBeingReader.getHumanBeing();
            return true;
        }catch (NumberFormatException e){
            System.out.println("Id must be integer number");
            return false;
        }
    }
}
