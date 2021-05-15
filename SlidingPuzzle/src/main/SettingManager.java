package main;

import gameInfo.KeyboardLayout;
import io.Reader;
import io.Writer;

public class SettingManager {

    private static final String SETTING_FILE_NAME = "setting.csv";
    private static final int GAME_SIZE_SETTING_INDEX = 0;
    private static final int KEYBOARD_LAYOUT_SETTING_INDEX = 1;


    private static final int DEFAULT_GAME_SIZE = 4;
    private static final KeyboardLayout DEFAULT_KEYBOARD_LAYOUT = KeyboardLayout.DEFAULT;

    public static void implementSettingInGame() {

        Reader reader = new Reader();
        if (!reader.isExist(SETTING_FILE_NAME))
            setSetting(DEFAULT_GAME_SIZE, DEFAULT_KEYBOARD_LAYOUT);

        String[] settings = reader.read(SETTING_FILE_NAME).split(",");

        Setting.setGameSize(Integer.parseInt(settings[GAME_SIZE_SETTING_INDEX]));
        Setting.setKeyboardLayout(settings[KEYBOARD_LAYOUT_SETTING_INDEX].equals("0") ? KeyboardLayout.DEFAULT : KeyboardLayout.WSAD);

    }


    public static void setSetting(int gameSize, KeyboardLayout keyboardLayout) {

        String gameInfo = "";
        gameInfo = gameSize + "," + ((keyboardLayout == KeyboardLayout.DEFAULT) ? "0" : "1");
        Writer writer = new Writer();
        writer.write(SETTING_FILE_NAME, gameInfo);
        implementSettingInGame();
    }


}
