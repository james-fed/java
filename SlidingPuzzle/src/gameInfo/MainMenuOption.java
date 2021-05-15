package gameInfo;

public enum MainMenuOption implements Option{

    /**
     * this enum has got main menu options and each elements indicate a option in mainMenu.
     * tags will be compare with user input to do something depend on user choice.
     */

    START_GAME("1.Start Game", '1'),
    SETTING("2.Setting", '2'),
    EXIT("3.Exit", '3');


    char tag;
    String text;

    private MainMenuOption(String text, char tag) {
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
