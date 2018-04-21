package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import static com.almasb.fxgl.entity.Entities.builder;

class RegularArm implements Arm {

    private Vec2 position;

    RegularArm(GameWorld gameWorld, Vec2 position) {
        this.position = position;

        Entity armL = builder()
//            .type(PLAYER)
                .at(this.position.x - 5, this.position.y)
                .viewFromTextureWithBBox("scientist/arm/idle.png")
                .buildAndAttach(gameWorld);

        Entity armR = builder()
//            .type(PLAYER)
                .at(this.position.x + 5, this.position.y)
                .viewFromTextureWithBBox("scientist/arm/idle.png")
                .buildAndAttach(gameWorld);
    }

    @Override
    public void update() {

    }
}
