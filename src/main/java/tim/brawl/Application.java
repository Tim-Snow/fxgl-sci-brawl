package tim.brawl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;
import com.studiohartman.jamepad.ControllerManager;
import javafx.scene.paint.Color;
import tim.brawl.scenes.StartScene;

import static javafx.scene.paint.Color.BLACK;

public class Application extends GameApplication {

    private StartScene startScene;
    private ControllerManager controllers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1024);
        settings.setHeight(768);
        settings.setTitle("Scientist Brawl");

        settings.setIntroEnabled(false);
        settings.setMenuEnabled(false);
        settings.setMenuEnabled(false);
        settings.setManualResizeEnabled(false);
        settings.setFullScreenAllowed(false);
    }

    @Override
    protected void initInput() {
        super.initInput();

/*        getInput().addAction(new UserAction("") {
            @Override
            protected void onAction() {
                super.onAction();
            }
        }, ControllerButton.A);*/
    }

    @Override
    protected void initGame() {
        controllers = new ControllerManager();
        controllers.initSDLGamepad();

        startScene = new StartScene();

        getGameWorld().addEntityFactory(new ScientistEntityFactory());

        getGameWorld().setLevelFromMap("tiled.json");
    }

    @Override
    protected void initUI() {
        super.initUI();

        getGameScene().setBackgroundColor(Color.rgb(33, 255, 33));
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);

        controllers.update();
        startScene.update(controllers, tpf);
    }
}