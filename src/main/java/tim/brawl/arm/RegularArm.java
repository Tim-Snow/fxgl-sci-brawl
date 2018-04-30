package tim.brawl.arm;

import com.almasb.fxgl.app.FXGL;
import javafx.scene.Node;

public class RegularArm extends Arm {

    private boolean holdingWeapon = false;

    private Node armL, armR, bentArm;

    public RegularArm() {
        int yOffset = 13;
        int xOffset = 5;

        bentArm = FXGL.getAssetLoader().loadTexture("scientist/arm/holding.png");
        bentArm.setTranslateY(yOffset);
        bentArm.setVisible(false);

        armL = FXGL.getAssetLoader().loadTexture("scientist/arm/idle.png");
        armL.setTranslateX(xOffset - 5);
        armL.setTranslateY(yOffset);

        armR = FXGL.getAssetLoader().loadTexture("scientist/arm/idle.png");
        armR.setTranslateX(10);
        armR.setTranslateY(yOffset);

        this.addNode(armL);
        this.addNode(armR);
    }

    public void stand() {
        bentArm.setVisible(false);
        armL.setVisible(true);
        armR.setVisible(true);
    }

    public void duck() {
        bentArm.setVisible(true);
        armL.setVisible(false);
        armR.setVisible(false);
    }

    public void pickUpWeapon() {
        if (!holdingWeapon) {

        }
    }

    @Override
    public void reload() {
        if (holdingWeapon) {

        }
    }

    @Override
    public void fire() {
        if (holdingWeapon) {

        } //todo: melee if not?
    }

    @Override
    public void changeDirection(boolean facingLeft) {
        if (facingLeft) {
            bentArm.setScaleX(-1);
        } else {
            bentArm.setScaleX(1);
        }
    }
}
