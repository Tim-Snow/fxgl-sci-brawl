package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyDef;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.studiohartman.jamepad.ControllerState;
import javafx.scene.Group;
import tim.brawl.arm.Arm;
import tim.brawl.arm.RegularArm;
import tim.brawl.body.Body;
import tim.brawl.head.Head;
import tim.brawl.head.RegularHead;
import tim.brawl.leg.Leg;
import tim.brawl.leg.RegularLeg;

class Scientist {

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
        physicsComponent.setBodyType(BodyType.DYNAMIC);

        entity = Entities.builder()
                .at(position)
                .with(physicsComponent)
                .bbox(new HitBox(BoundingShape.box(10, 45)))
                .buildAndAttach();

        Group group = new Group(head, leg, body, arm);

        entity.setView(group);
    }

    void update(ControllerState state, double tpf) {
        float DEAD_ZONE = 0.2f;
        if (state.leftStickX >= DEAD_ZONE || state.leftStickX <= -DEAD_ZONE) {
            physicsComponent.setVelocityX(state.leftStickX * (tpf * 10000));
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

    private void jump() {
        physicsComponent.setVelocityY(-300);
    }

    private void stand() {
        body.stand();
        arm.stand();
        head.stand();
    }

    private void duck() {
        body.duck();
        arm.duck();
        head.duck();
    }
}
