package tim.brawl;

import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;

public class ScientistEntityFactory implements EntityFactory {

    @Spawns("platform")
    public Entity newPlatform(SpawnData data) {
        return Entities.builder()
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"), data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("ak47")
    public Entity newAk47(SpawnData data) {
        return Entities.builder()
                .from(data)
                .viewFromTextureWithBBox("weapons/ak47.png")
                .with(new PhysicsComponent())
                .build();
    }
}
