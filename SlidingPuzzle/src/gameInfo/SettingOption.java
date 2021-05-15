package gameInfo;

public enum SettingOption implements Option{

    /**
     * this enum has got main setting options and each elements indicate a option in mainMenu.
     * tags will be compare with user input to do something depend on user choice.
     */


    SETTING_GAME_SIZE(" 1.Game Size", '1'),
    SETTING_KEYBOARD_LAYOUT(" 2.Keyboard Layout", '2');


    String text;
    char tag;

    private SettingOption(String text, char tag) {
        this.tag = tag;
        this.text = text;
    }

    @Override
    public char getTag() {
        return this.tag;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
