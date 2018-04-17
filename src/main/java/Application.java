import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.settings.GameSettings;

public class Application extends GameApplication {

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

    public static void main(String[] args) {
        launch(args);

        StartScreen startScreen = new StartScreen();
    }

    @Override
    protected void initInput() {
        super.initInput();
    }

    @Override
    protected void initUI() {
        super.initUI();
    }
}
