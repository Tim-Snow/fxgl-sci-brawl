package tim.brawl.head;

import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.physics.HitBox;

public abstract class Head extends EntityView {

    public abstract void stand();

    public abstract void duck();

    public abstract HitBox getHitBox();

    public abstract void changeDirection(boolean facingLeft);
}
