package Handlers;

import java.awt.event.KeyEvent;

// this class contains a boolean array of current and previous key states
// a key k is down when keyState[k] is true.
public class Keys {

    public static final int NUM_KEYS = 12;

    public static boolean keyState[] = new boolean[NUM_KEYS];
    public static boolean prevKeyState[] = new boolean[NUM_KEYS];

    public static int UP = 0;
    public static int LEFT = 1;
    public static int DOWN = 2;
    public static int RIGHT = 3;
    public static int SPACE = 4;
    public static int BUTTON1 = 5;
    public static int BUTTON2 = 6;
    public static int BUTTON3 = 7;
    public static int BUTTON4 = 8;
    public static int ENTER = 9;
    public static int ESCAPE = 10;
    public static int POSITION = 11; //for printing position

    public static void keySet(int i, boolean b) {
        switch (i) {
            case KeyEvent.VK_UP:
                keyState[UP] = b;
                break;
            case KeyEvent.VK_LEFT:
                keyState[LEFT] = b;
                break;
            case KeyEvent.VK_DOWN:
                keyState[DOWN] = b;
                break;
            case KeyEvent.VK_RIGHT:
                keyState[RIGHT] = b;
                break;
            case KeyEvent.VK_SPACE:
                keyState[SPACE] = b;
                break;
            case KeyEvent.VK_W:
                keyState[BUTTON1] = b;
                break;
            case KeyEvent.VK_E:
                keyState[BUTTON2] = b;
                break;
            case KeyEvent.VK_R:
                keyState[BUTTON3] = b;
                break;
            case KeyEvent.VK_F:
                keyState[BUTTON4] = b;
                break;
            case KeyEvent.VK_ENTER:
                keyState[ENTER] = b;
                break;
            case KeyEvent.VK_ESCAPE:
                keyState[ESCAPE] = b;
                break;
            case KeyEvent.VK_P:
                keyState[POSITION] = b;
                break;
            default:
                break;
        }
    }


    public static void update() {
        for (int i = 0; i < NUM_KEYS; i++) {
            prevKeyState[i] = keyState[i];
        }
    }

    public static boolean isPressed(int i) {
        return keyState[i] && !prevKeyState[i];
    }

    public static boolean anyKeyPress() {
        for (int i = 0; i < NUM_KEYS; i++) {
            if (keyState[i]) {
                return true;
            }
        }
        return false;
    }

}