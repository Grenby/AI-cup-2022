package ai_cup_22.ai.states;

import ai_cup_22.Holder;
import ai_cup_22.model.Unit;
import ai_cup_22.model.UnitOrder;
import ai_cup_22.model.Vec2;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;

public enum UnitState implements State<Unit> {

    RANDOM_RUN(){

        final Vector2 targetPos = new Vector2();


        private void setRandPos(){
            targetPos.setToRandomDirection();
            targetPos.scl((float)(Holder.game.getZone().getCurrentRadius() * Math.random() * 0.75f));
            targetPos.add(Holder.game.getZone().getCurrentCenter());
        }


        @Override
        public void enter(Unit unit) {
            Holder.logger.info("STATE:","unit:"+ unit.getId()+"in state"+RANDOM_RUN);
            setRandPos();
        }

        @Override
        public void update(Unit unit) {
            UnitOrder o = Holder.getUnitOrder(unit);
            o.getTargetDirection().set(targetPos).sub(unit.getPosition());
            o.getTargetVelocity().set(targetPos).sub(unit.getPosition()).nor().scl((float)Holder.constants.getMaxUnitForwardSpeed());
        }

        @Override
        public void exit(Unit unit) {

        }

        public boolean end(Unit unit){
            double r = Holder.constants.getUnitRadius();
            return unit.getPosition().dst(targetPos) < r*r;
        }

    };




    @Override
    public void enter(Unit unit) {

    }

    @Override
    public void update(Unit unit) {

    }

    @Override
    public void exit(Unit unit) {

    }

    @Override
    public boolean onMessage(Unit unit, Telegram telegram) {
        return false;
    }
}
