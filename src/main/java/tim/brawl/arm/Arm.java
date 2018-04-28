package tim.brawl.arm;

import com.almasb.fxgl.entity.view.EntityView;

public abstract class Arm extends EntityView {

    public abstract void stand();

    public abstract void duck();

    public abstract void changeDirection(boolean facingLeft);
}
