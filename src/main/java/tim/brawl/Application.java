package tim.brawl;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.settings.GameSettings;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

import static tim.brawl.ControllerManager.searchForControllers;

public class Application extends GameApplication {

    StartScreen startScreen;

    public static void main(String[] args) {
        ControllerManager.init();
        launch(args);
        //todo: make scene manager
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

        getInput().addAction(new UserAction("") {
            @Override
            protected void onAction() {
                super.onAction();
                searchForControllers();
            }
        }, KeyCode.ENTER);
    }

    @Override
    protected void initGame() {
        searchForControllers();

        startScreen = new StartScreen(getGameWorld());
    }

    @Override
    protected void initUI() {
        super.initUI();

        getGameScene().setBackgroundColor(Color.BLACK);
    }

    @Override
    protected void onUpdate(double tpf) {
        super.onUpdate(tpf);

        for (int i = 0; i < ControllerManager.totalControllers; i++) {
            if(ControllerManager.getPressed(i).get(BUTTONS.START)) {
                startScreen.spawnScientist();
            }
        }
    }
}
