package tim.brawl.body;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.Node;

public class Body extends EntityView {

    private boolean isDucking = false;

    private HitBox hitBox;
    private Node body, crouch;

    public Body() {
        Texture bodyT = FXGL.getAssetLoader().loadTexture("scientist/body/idle.png");
        body = bodyT;
        body.resize(64, 64);
        crouch = FXGL.getAssetLoader().loadTexture("scientist/body/crouch.png");
        crouch.setVisible(false);
        crouch.setTranslateY(15);

        hitBox = new HitBox(BoundingShape.box(bodyT.getWidth(), bodyT.getHeight()));

        this.addNode(body);
        this.addNode(crouch);
    }

    public void duck() {
        if (!isDucking) {
            body.setVisible(false);
            crouch.setVisible(true);
            isDucking = true;
        }
    }

    public void stand() {
        if (isDucking) {
            body.setVisible(true);
            crouch.setVisible(false);
            isDucking = false;
        }
    }

    public HitBox getHitBox() {
        return hitBox;
    }
}
