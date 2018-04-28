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

class Scientist {

    private boolean isDucking = false, facingLeft = false;

    private PhysicsComponent physicsComponent;

    private Entity entity;
    private Arm arm;
    private Leg leg;
    private Body body;
    private Head head;

    Scientist(Vec2 position) {
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
    }

    void update(ControllerState state, double tpf) {
        float DEAD_ZONE = 0.2f;
        if (state.leftStickX >= DEAD_ZONE || state.leftStickX <= -DEAD_ZONE) {
            boolean preFacingLeft = facingLeft;
            facingLeft = !(state.leftStickX > 0);

            if (preFacingLeft != facingLeft) {
                changeDirection();
            }

            if (!isDucking) {
                physicsComponent.setVelocityX(state.leftStickX * (tpf * 10000));
            }
        } else {
            physicsComponent.setVelocityX(0);
        }

        if (state.aJustPressed)
            jump();

        if (state.leftStickY <= -0.5)
            duck();
        else
            stand();
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
