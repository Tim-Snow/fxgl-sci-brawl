package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import static com.almasb.fxgl.entity.Entities.builder;

class Scientist extends Entity {

    private Vec2 position;
    private Arm arm;
    private Leg leg;
    private Head head;

    Scientist(GameWorld gameWorld, Vec2 position) {
        this.position = position;

        Entity entity = builder()
//            .type(PLAYER)
                .at(this.position)
                .viewFromTextureWithBBox("scientist/body/idle.png")
                .buildAndAttach(gameWorld);

        arm = new RegularArm(gameWorld, new Vec2(this.position.x + 5, this.position.y + 13));
        leg = new RegularLeg(gameWorld, new Vec2(this.position.x + 5, this.position.y + 35));
//        head = new RegularHead(gameWorld, position);
    }

    void update() {
        arm.update();
        leg.update();
    }
}
