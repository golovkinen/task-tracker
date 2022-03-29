import java.util.HashMap;

public class Task extends Var {
    private HashMap<Integer, Epic> epics;

    public Task (String name, String description, int id, String status, HashMap<Integer, Epic> epics) {
        super(name, description, id, status);
        this.epics = epics;
    }

    public HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public void setEpics(HashMap<Integer, Epic> epics) {
        this.epics = epics;
    }
}
