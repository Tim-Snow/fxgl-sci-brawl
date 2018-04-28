package tim.brawl.head;

import com.almasb.fxgl.app.FXGL;
import javafx.scene.Node;

public class RegularHead extends Head {

    private boolean isDucking = false;

    private Node head;

    public RegularHead() {
        int xOffset = 9;
        int yOffset = 11;

        head = FXGL.getAssetLoader().loadTexture("scientist/head/side.png");
        head.setRotate(45);
        head.setVisible(false);

        head.setTranslateX(xOffset);
        head.setTranslateY(yOffset);

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
}
