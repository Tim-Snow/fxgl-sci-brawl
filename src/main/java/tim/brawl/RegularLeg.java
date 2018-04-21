package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import static com.almasb.fxgl.entity.Entities.builder;

class RegularLeg implements Leg {

    private Vec2 position;

    RegularLeg(GameWorld gameWorld, Vec2 position) {
        this.position = position;

        Entity legL = builder()
//            .type(PLAYER)
                .at(this.position.x - 3, this.position.y)
                .viewFromTextureWithBBox("scientist/leg/idle.png")
                .buildAndAttach(gameWorld);

        Entity legR = builder()
//            .type(PLAYER)
                .at(this.position.x + 3, this.position.y)
                .viewFromTextureWithBBox("scientist/leg/idle.png")
                .buildAndAttach(gameWorld);
    }

    @Override
    public void update() {

    }
}
