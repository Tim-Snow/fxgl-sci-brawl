package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.studiohartman.jamepad.ControllerState;
import javafx.scene.Group;
import tim.brawl.arm.Arm;
import tim.brawl.arm.RegularArm;
import tim.brawl.body.Body;
import tim.brawl.head.Head;
import tim.brawl.head.RegularHead;
import tim.brawl.leg.Leg;
import tim.brawl.leg.RegularLeg;

import static com.almasb.fxgl.physics.box2d.dynamics.BodyType.DYNAMIC;

public class Scientist {

    private boolean isDucking = false, facingLeft = false, isDodging = false;
    private int dodgeVelocity = 0;

    private PhysicsComponent physicsComponent;

    private Entity entity;
    private Arm arm;
    private Leg leg;
    private Body body;
    private Head head;

    public Scientist(Vec2 position) {
        leg = new RegularLeg();
        body = new Body();
        arm = new RegularArm();
        head = new RegularHead();

        physicsComponent = new PhysicsComponent();
        physicsComponent.setBodyType(DYNAMIC);
        physicsComponent.setGenerateGroundSensor(true);

        entity = Entities.builder()
                .at(position)
                .with(physicsComponent)
                .bbox(leg.getHitBox())
                .bbox(head.getHitBox())
                .bbox(body.getHitBox())
                .buildAndAttach();

        Group group = new Group(head, leg, body, arm);

        entity.setView(group);

/*        int count = 1;
        for (HitBox hitBox : entity.getBoundingBoxComponent().hitBoxesProperty()) {
            Entities.builder()
                    .at(hitBox.getMinXWorld(), hitBox.getMinYWorld())
                    .viewFromNode(new Rectangle(hitBox.getWidth(), hitBox.getHeight(), Color.rgb(22 * count, 22 *count, 22 * count)))
                    .buildAndAttach();

            count++;
        }*/
    }

    public void update(ControllerState state, double tpf) {
        float DEAD_ZONE = 0.2f;
        if (state.leftStickX >= DEAD_ZONE || state.leftStickX <= -DEAD_ZONE) {
            boolean preFacingLeft = facingLeft;
            facingLeft = !(state.leftStickX > 0);

            if (preFacingLeft != facingLeft) {
                changeDirection();
            }

            leg.setMoving(true);

            if (!isDucking) {
                physicsComponent.setVelocityX(state.leftStickX * (tpf * 10000));
            }
        } else {
            leg.setMoving(false);
            physicsComponent.setVelocityX(0);
        }

        if (state.leftStickY <= -0.5)
            duck();
        else
            stand();

        if (state.rightTrigger >= 0.5)
            arm.fire();

        if (state.aJustPressed)
            jump();

        if (state.xJustPressed)
            arm.pickUpWeapon();

        if (state.yJustPressed)
            arm.reload();

        if (state.bJustPressed)
            head.doAction();

        if (state.lbJustPressed || state.rbJustPressed)
            dodge();
    }

    private void dodge() {
        if (isDodging) {

        }

        if (!isDucking && !isDodging) {
            isDodging = true;

            if (facingLeft) {
                physicsComponent.setVelocityX(-1000);
            } else {
                physicsComponent.setVelocityX(1000);
            }
        }
    }

    private void changeDirection() {
        body.changeDirection(facingLeft);
        head.changeDirection(facingLeft);
        arm.changeDirection(facingLeft);
        leg.changeDirection(facingLeft);
    }

    private void jump() {
        if (physicsComponent.isOnGround()) {
            physicsComponent.setVelocityY(-300);
        }
    }

    private void stand() {
        if (isDucking) {
            body.stand();
            arm.stand();
            head.stand();
            isDucking = false;
        }
    }

    private void duck() {
        if (!isDucking) {
            body.duck();
            arm.duck();
            head.duck();
            isDucking = true;
        }
    }
}
