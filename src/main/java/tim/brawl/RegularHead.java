package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.GameWorld;

import static com.almasb.fxgl.entity.Entities.builder;

class RegularHead implements Head {

    private Vec2 position;

    RegularHead(GameWorld gameWorld, Vec2 position) {
        this.position = position;

        Entity entity = builder()
//            .type(PLAYER)
                .at(this.position)
                .viewFromTextureWithBBox("scientist/head/side.png")
                .buildAndAttach(gameWorld);
    }
}
