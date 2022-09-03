package ai_cup_22.ai.tasks;

import ai_cup_22.model.Unit;

import java.util.ArrayList;
import java.util.Arrays;

public class SequenceTask extends Task{

    ArrayList<Task> children = new ArrayList<>();
    Unit u;
    int current = 0;

    public SequenceTask(Task... tasks) {
        children.addAll(Arrays.asList(tasks));
    }

    @Override
    public void run(Unit unit) {
        this.u = unit;
        children.get(current).run(u);
    }

    @Override
    public Status execute() {
        Status status = children.get(current).execute();
        while (status == Status.SUCCEEDED) {
            children.get(current).exit();
            current++;
            children.get(current).run(u);
            status = children.get(current).execute();
        }
        if (status != Status.RUNNING)
            children.get(current).exit();
        return status;
    }

    @Override
    public void exit() {
        if(current<children.size())
            children.get(current).exit();
    }
}
