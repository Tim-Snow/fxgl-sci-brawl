package tim.brawl.leg;

import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.physics.HitBox;

public abstract class Leg extends EntityView {
    public abstract HitBox getHitBox();

    public abstract void changeDirection(boolean facingLeft);

    public abstract void setMoving(boolean b);
}
