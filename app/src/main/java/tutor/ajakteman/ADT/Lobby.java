package tutor.ajakteman.ADT;

import java.util.ArrayList;

public class Lobby {

    int maxInQueue = 4;
    private ArrayList queue = new ArrayList();
    private ArrayList waitList = new ArrayList();

    public Lobby() {

    }

    public Lobby(int maxQueue){
        this.maxInQueue = maxQueue;
    }

    public void remove(Object obj, int pos) {
        if (queue.contains(obj)) {
            queue.remove(pos);
            if (waitList.size() > 0) {
                queue.add(waitList.get(0));
                waitList.remove(0);
            }
        } else if (waitList.contains(obj)) {
            waitList.remove(pos);
        }
    }

    public void add(Object obj) {
        if (queue.size() < maxInQueue) {
            queue.add(obj);
        } else {
            waitList.add(obj);
        }
    }

}
