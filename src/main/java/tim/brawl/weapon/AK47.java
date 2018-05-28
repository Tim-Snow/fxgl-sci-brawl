package tim.brawl.weapon;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.Entities;
import com.almasb.fxgl.physics.PhysicsComponent;

public class AK47 extends Weapon {

    public AK47(Vec2 position) {
        Entities.builder()
                .at(position)
                .with(new PhysicsComponent())
                .viewFromTextureWithBBox("weapons/ak47.png")
                .buildAndAttach();
    }

    @Override
    public void fire() {
        System.out.println("fire");
    }
}
