package tim.brawl.scenes;

import com.almasb.fxgl.core.math.Vec2;
import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;
import tim.brawl.Scientist;
import tim.brawl.weapon.AK47;

import java.util.HashMap;
import java.util.Map;

public class StartScene {

    private Map<Integer, Scientist> scientists;

    public StartScene() {
        scientists = new HashMap<>();

        AK47 ak47 = new AK47(new Vec2(500, 500));
    }

    public void update(ControllerManager controllers, double tpf) {
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
