package tim.brawl.head;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.Node;

import static com.almasb.fxgl.physics.BoundingShape.box;

public class RegularHead extends Head {

    private boolean isDucking = false;

    private Node head;
    private HitBox hitBox;

    public RegularHead() {
        int xOffset = 9;
        int yOffset = 11;

        Texture headT = FXGL.getAssetLoader().loadTexture("scientist/head/side.png");

        head = headT;
        head.setRotate(45);
        head.setVisible(false);

        head.setTranslateX(xOffset);
        head.setTranslateY(yOffset);

        hitBox = new HitBox(new Point2D(xOffset, yOffset), box(headT.getWidth(), headT.getHeight()));

        this.addNode(head);
    }

    public void stand() {
        if (isDucking) {
            head.setVisible(false);
            isDucking = false;
        }
    }

    public void duck() {
        if (!isDucking) {
            head.setVisible(true);
            isDucking = true;
        }
    }

    public HitBox getHitBox() {
        return hitBox;
    }

    @Override
    public void changeDirection(boolean facingLeft) {
        if (facingLeft) {
            head.setScaleX(-1);
            head.setRotate(-90);
            head.setTranslateX(-head.getTranslateX());
        } else {
            head.setScaleX(1);
            head.setRotate(90);
            head.setTranslateX(-head.getTranslateX());
        }
    }

    @Override
    public void doAction() {

    }
}
