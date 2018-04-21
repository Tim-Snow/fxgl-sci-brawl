package tim.brawl;

import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static net.java.games.input.Controller.Type.GAMEPAD;
import static tim.brawl.BUTTONS.*;

enum BUTTONS {A, B, X, Y, START, BACK, LB, RB, LT, RT}

class ControllerManager {

    private static final int DEADZONE = 10;

    static int totalControllers = 0;

    private static Map<BUTTONS, Boolean> pressed;
    private static ArrayList<Controller> foundControllers = null;

    static void init() {
        searchForControllers();
        totalControllers = foundControllers.size();
        pressed = new HashMap<>();

        for (BUTTONS button : BUTTONS.values()) {
            pressed.put(button, false);
        }
    }

    static void searchForControllers() {
        foundControllers = new ArrayList<>();

        Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();

        for (Controller controller : controllers) {
            if (controller.getType() == GAMEPAD) {
                foundControllers.add(controller);
            }
        }

        totalControllers = foundControllers.size();
    }

    static Controller getController(int id) {
        return foundControllers.get(id);
    }

    static Map<BUTTONS, Boolean> getPressed(int id) {
        if (foundControllers.get(id) != null) {
            for (Map.Entry<BUTTONS, Boolean> entry : pressed.entrySet()) {
                pressed.put(entry.getKey(), false);
            }

            foundControllers.get(id).poll();

            Component[] components = foundControllers.get(id).getComponents();
            for (int i = 0; i < components.length; i++) {
                Component component = components[i];
                Component.Identifier componentIdentifier = component.getIdentifier();

                // Buttons
                if (componentIdentifier.getName().matches("^[0-9]*$")) { // If the component identifier name contains only numbers, then this is getPressed button.

                    boolean isItPressed = true;
                    if (component.getPollData() == 0.0f)
                        isItPressed = false;

                    String buttonIndex;
                    buttonIndex = component.getIdentifier().toString();

                    if (isItPressed) {
                        System.out.println(buttonIndex);
                        switch (buttonIndex){
                            case "0":   pressed.put(A,      true);  break;
                            case "1":   pressed.put(B,      true);  break;
                            case "2":   pressed.put(X,      true);  break;
                            case "3":   pressed.put(Y,      true);  break;
                            case "4":   pressed.put(LB,     true);  break;
                            case "5":   pressed.put(RB,     true);  break;
                            case "6":   pressed.put(BACK,   true);  break;
                            case "7":   pressed.put(START,  true);  break;
                            case "8":   pressed.put(LT,     true);  break;
                            case "9":   pressed.put(RT,     true);  break;
                            default:    break;
                        }
                    }

                    continue;
                }

                // Hat switch
                if (componentIdentifier == Component.Identifier.Axis.POV) {
                    float hatSwitchPosition = component.getPollData();
                    continue;
                }

                // Axes
                if (component.isAnalog()) {
                    float axisValue = component.getPollData();
                    int axisValueInPercentage = getAxisValueInPercentage(axisValue);

                    // X axis
//                    if (componentIdentifier == Component.Identifier.Axis.X) {
//                        if (axisValueInPercentage != 0 + DEADZONE && axisValueInPercentage != 0 - DEADZONE) System.out.println(axisValueInPercentage);
//                        continue; // Go to next component.
//                    }
//                    // Y axis
//                    if (componentIdentifier == Component.Identifier.Axis.Y) {
//                        if (axisValueInPercentage != 0 + DEADZONE && axisValueInPercentage != 0 - DEADZONE) System.out.println(axisValueInPercentage);
//                    }
                }
            }
        }

        return pressed;
    }

    private static int getAxisValueInPercentage(float axisValue) {
        return (int) (((2 - (1 - axisValue)) * 100) / 2);
    }
}
