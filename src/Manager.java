import java.util.ArrayList;
import java.util.HashMap;

public class Manager {

    private HashMap<Integer, Task> tasks = new HashMap<>();

    private int taskId = 0;

    public ArrayList<Task> allTasksList() { //Список всех задач

        return new ArrayList<>(tasks.values());
    }

    public void clearAllTasks() { //Удалить все задачи

        tasks.clear();
    }

    public Task getTask(int id) { //Получить задачу по идентификатору

        return tasks.get(id);
    }

    public void createTask(Task task) { //Создать задачу по идентификатору
        task.setId(++taskId);
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) { //Обновить задачу по идентификатору
        if (!tasks.containsKey(task.getId())) {
            return;
        }
        tasks.put(task.getId(), task);
    }

    public void deleteTask(int id) { //Удалить задачу по идентификатору

        tasks.remove(id);
    }

    public ArrayList<Epic> allEpicsList(Task task) { //Список всех epic
        HashMap<Integer, Epic> epicList = task.getEpics();
        return new ArrayList<>(epicList.values());
    }

    public void clearAllTaskEpics(Task task) { //Удалить все эпики
        HashMap<Integer, Epic> epicList = task.getEpics();
        epicList.clear();
    }

    public Epic getEpic(Task task, int id) { //Получить эпик по идентификатору
        HashMap<Integer, Epic> epicList = task.getEpics();
        return epicList.get(id);
    }

    public void createEpic(Task task, Epic epic) { //Создать эпик по идентификатору
        HashMap<Integer, Epic> epicsHash = task.getEpics();
        int num = epicsHash.size();
        epic.setId(++num);
        epicsHash.put(epic.getId(), epic);
    }

    public void updateEpic(Task task, Epic epic) { //Обновить эпик по идентификатору
        HashMap<Integer, Epic> epicsHash = task.getEpics();
        if (!epicsHash.containsKey(epic.getId())) {
            return;
        }
        epicsHash.put(epic.getId(), epic);
    }

    public void deleteEpic(int id, Task task) { //Удалить эпик по идентификатору
        HashMap<Integer, Epic> epicsHash = task.getEpics();
        epicsHash.remove(id);
    }

    public ArrayList<SubTask> getSubTasksList(Epic epic) { //Список всех подзадач

        return epic.getSubTasks();
    }

    public void clearAllSubTasks(Epic epic) { //Удалить все подзадачи

        epic.clearSubTasks();
    }

    public SubTask getSubTask(int id, Epic epic) { //Получить подзадачу по идентификатору

        return epic.getSubTaskId(id);
    }

    public void createSubTask(Epic epic, SubTask subTask) { //Создать подзадачу по идентификатору
        ArrayList<SubTask> subTasks = epic.getSubTasks();
        int subId = subTasks.size();
        subTask.setId(++subId);
        subTasks.add(subTask);
    }

    public void updateSubTask(Epic epic, SubTask subTask, int id) { //Обновить подзадачу по идентификатору
        ArrayList<SubTask> subTasks = epic.getSubTasks();
        if ((id - 1) > subTasks.size() || (id - 1) < 0) {
            return;
        }
        subTasks.set((id - 1), subTask);
    }

    public void deleteSubTask(Epic epic, int id) { //Удалить подзадачу по идентификатору
        ArrayList<SubTask> subTasks = epic.getSubTasks();
        if ((id - 1) > subTasks.size() || (id - 1) < 0) {
            return;
        }
        subTasks.remove(id - 1);
    }

    public void checkStatus() {

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            HashMap<Integer, Epic> epicsHash = task.getEpics();
            if (!epicsHash.isEmpty()) {
                int statCount = 0;
                for (int j = 1; j <= epicsHash.size(); j++) {
                    Epic epic = epicsHash.get(j);
                    ArrayList<SubTask> subTasks = epic.getSubTasks();
                    if (!subTasks.isEmpty()) {

                        int count = 0;
                        for (int n = 0; n < subTasks.size(); n++) {
                            SubTask subTask = subTasks.get(n);
                            String status = subTask.getStatus();
                            switch (status) {
                                case "NEW":
                                    break;
                                case "IN_PROGRESS":
                                    epic.setStatus(Status.IN_PROGRESS);
                                    break;
                                case "DONE":
                                    count += 1;
                                    epic.setStatus(Status.IN_PROGRESS);
                                    break;
                            }
                        }

                        if (count == subTasks.size() && count != 0) {
                            epic.setStatus(Status.DONE);
                        }
                    }
                    String epicStat = epic.getStatus();
                    switch (epicStat) {
                        case "NEW":
                            break;
                        case "IN_PROGRESS":
                            task.setStatus(Status.IN_PROGRESS);
                            break;
                        case "DONE":
                            statCount += 1;
                            task.setStatus(Status.IN_PROGRESS);
                            break;
                    }
                }
                if (statCount == epicsHash.size() && statCount != 0) {
                    task.setStatus(Status.DONE);
                }
            }
        }
    }

    public void printTable() {

        if (tasks.isEmpty()){
            System.out.println("Нет никаких задач");
            return;
        }

        for (int i = 1; i <= tasks.size(); i++) {
            Task task = tasks.get(i);
            HashMap<Integer, Epic> epicsHash = task.getEpics();
            if (epicsHash.isEmpty()) {
                System.out.println("Задача - " + task.getId());
                System.out.println(task);
            } else {
                System.out.println("Задача - " + task.getId());
                System.out.println(task);
                for (int j = 1; j <= epicsHash.size(); j++) {
                    Epic epic = epicsHash.get(j);
                    ArrayList<SubTask> subTasks = epic.getSubTasks();
                    if (subTasks.isEmpty()) {
                        System.out.println("Эпик - " + epic.getId());
                        System.out.println(epic);
                    } else {
                        System.out.println("Эпик - " + epic.getId());
                        System.out.println(epic);
                        System.out.println("Подзадачи");
                        for (int n = 0; n < subTasks.size(); n++) {
                            SubTask subTask = subTasks.get(n);
                            System.out.println(subTask);
                        }
                    }
                }
            }
        }
    }
}
