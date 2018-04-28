package tim.brawl.leg;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.Node;

public class RegularLeg extends Leg {

    HitBox hitBox;

    public RegularLeg() {

        int xOffset = 5;
        int yOffset = 35;

        Texture legT = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");

        Node legL = legT;
        legL.setTranslateX(xOffset - 3);
        legL.setTranslateY(yOffset);

        Node legR = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");
        legR.setTranslateX(xOffset + 3);
        legR.setTranslateY(yOffset);

        hitBox = new HitBox(new Point2D(legL.getTranslateX(), legL.getTranslateY()), BoundingShape.box(legT.getWidth(), legT.getHeight()));

        this.addNode(legL);
        this.addNode(legR);
    }

    public HitBox getHitBox() {
        return hitBox;
    }
}
