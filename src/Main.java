import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();

        Task task1 = new Task ("Переезд", "Переезд из Зажопинска в Выселки", 0, Status.NEW, new HashMap<>());
        manager.createTask(task1);
        Epic epic1 = new Epic("Вещи", "Собрать все, что нажито непосильным трудом", 0, Status.NEW, new ArrayList<>());
        manager.createEpic(task1, epic1);
        Epic epic2 = new Epic("Мебель", "Разобрать и упаковать мебель", 0, Status.NEW, new ArrayList<>());
        manager.createEpic(task1, epic2);
        Epic epic3 = new Epic("Транспорт", "Оценить масштаб бедствия и вызвать грузоперевозчика", 0, Status.NEW, new ArrayList<>());
        manager.createEpic(task1, epic3);
        SubTask subTask1 = new SubTask("Спальня", "Постельное белье, подушки, одеяла", 0, Status.NEW);
        manager.createSubTask(epic1, subTask1);
        SubTask subTask2 = new SubTask("Гостинная", "Проектор, ресивер, колонки, ПС, медиаплеер", 0, Status.NEW);
        manager.createSubTask(epic1, subTask2);
        SubTask subTask3 = new SubTask("Ванная", "Принадлежности, полотенца, химия, аптечка", 0, Status.NEW);
        manager.createSubTask(epic1, subTask3);
        SubTask subTask4 = new SubTask("Спальня", "Кровать, тумбочки, стол туалетный, банкета", 0, Status.NEW);
        manager.createSubTask(epic2, subTask4);
        SubTask subTask5 = new SubTask("Гостинная", "Диван, стол обеденный, стулья, кресла", 0, Status.NEW);
        manager.createSubTask(epic2, subTask5);

        manager.printTable();

        //subTask1.setStatus (Status.DONE);
        //manager.updateSubTask(epic1, subTask1, 1);
        subTask2.setStatus (Status.DONE);
        manager.updateSubTask(epic1, subTask2, 2);
        subTask3.setStatus (Status.DONE);
        manager.updateSubTask(epic1, subTask3, 3);
        subTask4.setStatus (Status.DONE);
        manager.updateSubTask(epic2, subTask4, 4);
        subTask5.setStatus (Status.IN_PROGRESS);
        manager.updateSubTask(epic2, subTask5, 5);
        epic3.setStatus(Status.DONE);
        manager.updateEpic(task1, epic3);

        manager.checkStatus();
        manager.printTable();

       System.out.println(manager.allTasksList());
       System.out.println(manager.allEpicsList(task1));
      System.out.println(manager.getSubTasksList(epic1));

      manager.clearAllSubTasks(epic2);
        System.out.println(manager.getSubTasksList(epic2));

      manager.clearAllTaskEpics(task1);
        manager.checkStatus();

        manager.printTable();





    }
}
