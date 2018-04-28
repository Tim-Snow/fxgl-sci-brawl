package tim.brawl;

import com.almasb.fxgl.core.math.Vec2;
import com.almasb.fxgl.scene.GameScene;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.util.HashMap;
import java.util.Map;

class StartScreen {

    private GameScene gameScene;
    private Map<Integer, Scientist> scientists;

    StartScreen(GameScene gameScene) {
        this.gameScene = gameScene;

        scientists = new HashMap<>();
    }

    void update(ControllerManager controllers, double tpf) {
        for (int i = 0; i < scientists.values().size(); i++) {
            scientists.get(i).update(controllers.getState(i), tpf);
        }

        for (int i = 0; i < controllers.getNumControllers(); i++) {
            ControllerState currState = controllers.getState(i);

            if (currState.isConnected) {
                if (currState.startJustPressed) {
                    spawnScientist(i);
                }
            } else {
                removeScientist(i);
            }
        }
    }

    private void spawnScientist(int controllerId) {
        if (scientists.get(controllerId) == null) {
            scientists.put(controllerId, new Scientist(new Vec2(300, 500)));
        }
    }

    private void removeScientist(int controllerId) {
        if (scientists.get(controllerId) != null)
//            scientists.get(controllerId).removeFromWorld();

            scientists.remove(controllerId);
    }
}
