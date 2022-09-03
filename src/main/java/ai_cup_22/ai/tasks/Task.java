package ai_cup_22.ai.tasks;

import ai_cup_22.model.Unit;

public abstract class Task {

    public enum Status{
        FRESH,
        RUNNING,
        FAILED,
        SUCCEEDED,
        CANCELLED;
    }

    public enum Name{
        GO_TO_POINT;
    }

    private Name name;

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void run(Unit unit){};
    public Status execute(){
        return Status.FAILED;
    };
    public void exit(){};

}
