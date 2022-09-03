package ai_cup_22.ai;

import ai_cup_22.ai.states.UnitState;
import ai_cup_22.model.Unit;
import com.badlogic.gdx.ai.fsm.DefaultStateMachine;
import com.badlogic.gdx.ai.fsm.State;

import java.util.ArrayList;

public class MyAI {

    ArrayList<Unit> enemies = null;

    DefaultStateMachine<Unit, State<Unit>> stateMachine = new DefaultStateMachine<>(
            null,
            null
    );

    Unit u = null;

    public void setEnemies(ArrayList<Unit> enemies) {
        this.enemies = enemies;
    }

    public void updateBehavior(Unit unit){
        stateMachine.setOwner(unit);
        u = unit;
        State<Unit> state = stateMachine.getCurrentState();
        if (stateMachine.getCurrentState() == null)
            stateMachine.changeState(UnitState.RANDOM_RUN);
        if (stateMachine.getCurrentState() == UnitState.RANDOM_RUN)
            updateRandState();
        stateMachine.update();
    }

    void updateRandState(){
        //if (UnitState.RANDOM_RUN)
    }

}
