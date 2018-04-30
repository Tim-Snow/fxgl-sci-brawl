package tim.brawl.leg;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.Objects;

import static com.almasb.fxgl.physics.BoundingShape.box;

public class RegularLeg extends Leg {

    private HitBox hitBox;
    private AnimatedTexture legs;
    private AnimationChannel animIdle, animWalk;

    public RegularLeg() {
        int xOffset = 1;
        int yOffset = 35;

        animIdle = new AnimationChannel("scientist/leg/legs.png", 1, 10, 11, Duration.minutes(1), 0, 0);
        animWalk = new AnimationChannel("scientist/leg/legs.png", 5, 10, 11, Duration.seconds(1), 0, 4);

        legs = new AnimatedTexture(animIdle);

        legs.setTranslateX(xOffset);
        legs.setTranslateY(yOffset);

        hitBox = new HitBox(new Point2D(legs.getTranslateX(), legs.getTranslateY()), box(1, legs.getImage().getHeight()));

        this.addNode(legs);
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void changeDirection(boolean facingLeft) {
        if (facingLeft) {
            legs.setScaleX(-1);
        } else {
            legs.setScaleX(1);
        }
    }

    @Override
    public void setMoving(boolean isMoving) {
        if (isMoving) {
            if (legs.getAnimationChannel() == animIdle) {
                legs.loopAnimationChannel(animWalk);
            }
        } else {
            legs.loopAnimationChannel(animIdle);
        }
    }
}
