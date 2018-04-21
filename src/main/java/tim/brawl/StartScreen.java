package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.entity.GameWorld;

class StartScreen {

    GameWorld gameWorld;

    StartScreen(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    void spawnScientist() {
        Scientist scientist = new Scientist(gameWorld, new Vec2(300, 300));

    }
}
