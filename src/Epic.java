import java.util.ArrayList;

public class Epic extends Var {
    private ArrayList<SubTask> subTasks;

    public Epic(String name, String description, int id, String status, ArrayList<SubTask> subTasks) {
        super(name, description, id, status);
        this.subTasks = subTasks;
    }

    public ArrayList<SubTask> getSubTasks() {

        return subTasks;
    }

    public void setSubTasks(ArrayList<SubTask> subTasks) {

        this.subTasks = subTasks;
    }

    public void clearSubTasks() {
        subTasks.clear();
    }

    public SubTask getSubTaskId (int id) {
       return subTasks.get(id - 1);
    }

}
