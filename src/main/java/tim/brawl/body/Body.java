package tim.brawl.body;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.view.EntityView;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.scene.Node;

import static com.almasb.fxgl.physics.BoundingShape.box;

public class Body extends EntityView {

    private HitBox hitBox;
    private Node body, crouch;

    public Body() {
        Texture bodyT = FXGL.getAssetLoader().loadTexture("scientist/body/idle.png");
        body = bodyT;
        body.resize(64, 64);
        crouch = FXGL.getAssetLoader().loadTexture("scientist/body/crouch.png");
        crouch.setVisible(false);
        crouch.setTranslateY(15);

        hitBox = new HitBox(box(bodyT.getWidth(), bodyT.getHeight()));

        this.addNode(body);
        this.addNode(crouch);
    }

    public void duck() {
        body.setVisible(false);
        crouch.setVisible(true);
    }

    public void stand() {
        body.setVisible(true);
        crouch.setVisible(false);
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    public void changeDirection(boolean facingLeft) {
        if (facingLeft) {
            crouch.setScaleX(-1);
            body.setScaleX(-1);
        } else {
            crouch.setScaleX(1);
            body.setScaleX(1);
        }
    }
}
