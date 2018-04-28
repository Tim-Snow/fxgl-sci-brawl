package tim.brawl.leg;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import static com.almasb.fxgl.physics.BoundingShape.box;

public class RegularLeg extends Leg {

    private HitBox hitBox;
    private Node legL, legR;

    public RegularLeg() {

        int xOffset = 5;
        int yOffset = 35;

        Texture legT = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");

        legL = legT;
        legL.setTranslateX(xOffset - 3);
        legL.setTranslateY(yOffset);

        legR = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");
        legR.setTranslateX(xOffset + 3);
        legR.setTranslateY(yOffset);

        hitBox = new HitBox(new Point2D(legL.getTranslateX(), legL.getTranslateY()), box(legT.getWidth(), legT.getHeight()));

        this.addNode(legL);
        this.addNode(legR);
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void changeDirection(boolean facingLeft) {
        if (facingLeft) {
            legL.setScaleX(-1);
            legR.setScaleX(-1);
        } else {
            legL.setScaleX(1);
            legR.setScaleX(1);
        }
    }
}
