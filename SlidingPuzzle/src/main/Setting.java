package main;

import gameInfo.KeyboardLayout;

public class Setting {


    private static int gameSize;
    private static KeyboardLayout keyboardLayout;

    public static int getGameSize() {
        return gameSize;
    }

    public static KeyboardLayout getKeyboardLayout() {
        return keyboardLayout;
    }

    public static void setGameSize(int game_Size) {
        gameSize = game_Size;
    }

    public static void setKeyboardLayout(KeyboardLayout keyboard_Layout) {
        keyboardLayout = keyboard_Layout;
    }

}
