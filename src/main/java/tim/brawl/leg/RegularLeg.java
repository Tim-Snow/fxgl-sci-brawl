package tim.brawl.leg;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.scene.Node;

public class RegularLeg extends Leg {

    public RegularLeg() {

        int xOffset = 5;
        int yOffset = 35;

        Node legL = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");
        HitBox hitBox = new HitBox(BoundingShape.box(10, 10));
        legL.setTranslateX(xOffset - 3);
        legL.setTranslateY(yOffset);

        Node legR = FXGL.getAssetLoader().loadTexture("scientist/leg/idle.png");
        legR.setTranslateX(xOffset + 3);
        legR.setTranslateY(yOffset);

        this.addNode(legL);
        this.addNode(legR);
    }
}
