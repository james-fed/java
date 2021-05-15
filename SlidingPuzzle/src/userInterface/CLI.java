package userInterface;

import gameComponents.GameTiles;
import gameComponents.Tile;
import gameInfo.*;
import main.Setting;

import java.io.IOException;

public class CLI {

    public void updateGameBoard(GameTiles gameTiles, long move) {
        /*
         * for updating user Command Line interface you can use two different manner
         * 1. normalPrinting
         * 2. tileWork
         * more information above of their methods
         */
        tileWork(gameTiles, move);
    }

    /*
     * prints numbers normally in two decimal(because the maximum number is 99) to avoiding disorder of numbers
     * note that this method print 0 for null space
     */
    private void normalPrinting(GameTiles gameTiles, int move) {

        System.out.println(" Moves:" + move);
        for (Tile[] tiles : gameTiles.getTiles()) {
            for (Tile tile : tiles)
                System.out.printf("%2d%s", tile.getValue(), ((tile.getColumn() != tiles.length - 1) ? " " : ""));
            System.out.println();
        }
    }


    /*
     * print numbers in the frames(tiles)
     * this method have got massive output in big gameSize if you would like to simple output is better to use noramlPrinting method
     */
    private void tileWork(GameTiles gameTiles, long move) {

        clearConsole();
        /*
         * depending on game size the outer frame and margin between each innerFrame(tiles) will be change.
         * if size of game is between 2-5 the max frame size is 36  and for higher than 5 the frame size is 71
         * the margin for each gameSize is different, to print tiles in correct position.
         */
        final int gameSize = gameTiles.getTiles().length;
        final int smallFrameWidth = 36;
        final int largeFrameWidth = 71;
        final int tileWidth = 6;

        int frameWidth;
        if (2 <= gameSize && gameSize <= 5)
            frameWidth = smallFrameWidth;
        else
            frameWidth = largeFrameWidth;
        // calculate margin depend on game size
        final int marginTilesWidth = (frameWidth - (gameSize * tileWidth)) / (gameSize + 1);
        final int remindMargin = (frameWidth - (gameSize * tileWidth)) % (gameSize + 1);


        // print top of the outer frame contain movement value
        System.out.print("   ___");
        for (int s = 0; s < frameWidth; s++)
            System.out.print(" ");
        System.out.println("___  ");

        System.out.print(" __| |");
        for (int j = 0; j < frameWidth; j++)
            System.out.print("_");
        System.out.println("| |__");

        System.out.print("|  | |");
        System.out.print(" Moves: " + move);
        for (int s = 0; s < frameWidth - (" Moves: ".length() + String.valueOf(move).length()); s++)
            System.out.print(" ");
        System.out.println("| |  |");

        System.out.print("|__|_|");
        for (int j = 0; j < frameWidth; j++)
            System.out.print("_");
        System.out.println("|_|__|");


        // print main frame and tiles in the outer frame
        for (int i = 0; i < gameSize; i++) {
            System.out.print("   | |");
            for (int s = 0; s < marginTilesWidth + (remindMargin / 2); s++)
                System.out.print(" ");

            for (int j = 0; j < gameSize; j++) {
                if (gameTiles.getTile(i, j).getValue() != TilesVariable.NULL_SPACE_VALUE.getValue())
                    System.out.print(".----.");
                else
                    System.out.print("      ");
                for (int s = 0; s < marginTilesWidth; s++)
                    System.out.print(" ");
            }
            for (int s = 0; s < (remindMargin / 2) + (remindMargin % 2); s++)
                System.out.print(" ");
            System.out.println("| |");

            System.out.print("   | |");
            for (int s = 0; s < marginTilesWidth + (remindMargin / 2); s++)
                System.out.print(" ");
            for (int j = 0; j < gameSize; j++) {
                if (gameTiles.getTile(i, j).getValue() == TilesVariable.NULL_SPACE_VALUE.getValue())
                    System.out.print("      ");
                else
                    System.out.printf("| %2d |", gameTiles.getTile(i, j).getValue());
                for (int s = 0; s < marginTilesWidth; s++)
                    System.out.print(" ");
            }
            for (int s = 0; s < (remindMargin / 2) + (remindMargin % 2); s++)
                System.out.print(" ");
            System.out.println("| |");

            System.out.print("   | |");
            for (int s = 0; s < marginTilesWidth + (remindMargin / 2); s++)
                System.out.print(" ");
            for (int j = 0; j < gameSize; j++) {
                if (gameTiles.getTile(i, j).getValue() != TilesVariable.NULL_SPACE_VALUE.getValue())
                    System.out.print("'----'");
                else
                    System.out.print("      ");
                for (int s = 0; s < marginTilesWidth; s++)
                    System.out.print(" ");
            }
            for (int s = 0; s < (remindMargin / 2) + (remindMargin % 2); s++)
                System.out.print(" ");
            System.out.println("| |");
        }


        // print footer of frame
        System.out.print(" __| |");
        for (int j = 0; j < frameWidth; j++)
            System.out.print("_");
        System.out.println("| |__");

        //print the footer massage
        String moveInfo = "Move:";
        String exitMessage = "Back:B";
        if (Setting.getKeyboardLayout() == KeyboardLayout.WSAD)
            moveInfo += "W.S.D.A";
        else if (Setting.getKeyboardLayout() == KeyboardLayout.DEFAULT)
            moveInfo += "U.D.R.L";

        System.out.print("|     ");
        System.out.print(moveInfo);
        for (int s = 0; s < frameWidth - (exitMessage.length() + moveInfo.length()); s++)
            System.out.print(" ");
        System.out.print(exitMessage);
        System.out.println("     |");

        System.out.print("|_____");
        for (int j = 0; j < frameWidth; j++)
            System.out.print("_");
        System.out.println("_____|");
    }


    public void clearConsole() {
//        for (int i = 0; i < 20; i++)
//            System.out.println("\n");

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }


    /**
     * for implementing a menu, for Example main menu and setting menu
     * the parameter EnumSet get an enumeration(constant options) to implement them in menu
     * for example gameinfo.Settingoption and gameinfo.MenuOption
     */
    public void implementMenu(Option[] options, boolean containBack) {

        clearConsole();
        int frameWidth = 36;
        System.out.println("   ___                                    ___");
        System.out.println("   | |     _|o _|o _  _    _    _ _ | _   | |");
        System.out.println("   | |    _)||(_||| )(_)  |_)|_|/_/_|(-`  | |");
        System.out.println("   | |               _/   |               | |");
        System.out.println(" __| |____________________________________| |__");
        System.out.println("(__| |____________________________________| |__)");
        System.out.println("   | |                                    | |");

        for (Object o : options) {
            Option option = (Option) o;
            System.out.print("   | |");
            String optionText = option.getText();
            if (optionText.length() > frameWidth)
                optionText = optionText.substring(0, frameWidth - 3) + "...";
            System.out.print(optionText);
            for (int i = 0; i < frameWidth - option.getText().length(); i++)
                System.out.print(" ");
            System.out.println("| |");
            System.out.println("   | |                                    | |");
        }

        System.out.println("   | |                                    | |");
        System.out.println(" __| |____________________________________| |__");
        if (containBack)
            System.out.println("(     Back:B                                   )");
        System.out.println("(______________________________________________)");
    }

    public void winMessage(long score) {

        clearConsole();
        System.out.println(" ______________________________________");
        System.out.println("/.\\                                    \\");
        System.out.println("\\_|____________________________________|");
        System.out.println("  |                                    |");
        System.out.println("  |                                    |");
        System.out.println("  |  _  _                      _       |");
        System.out.println("  | | || | ___  _  _  _ __ __ (_) _ _  |");
        System.out.println("  |  \\_. |/ _ \\| || | \\ V  V /| || ' \\ |");
        System.out.println("  |  |__/ \\___/ \\_._|  \\_/\\_/ |_||_||_||");
        System.out.println("  |                                    |");
        System.out.println("  |                                    |");
        System.out.println("  |                                    |");
//        System.out.println("  |Score:                              |");
        System.out.print("  | Score:" + score);
        final int frameWidth = 36;
        for (int s = 0; s < frameWidth - (" Score:".length() + String.valueOf(score).length()); s++)
            System.out.print(" ");
        System.out.println("|");
        System.out.println("  |                                    |");
        System.out.println("  | _                 Press any key... |");
        System.out.println("  \\/.\\_________________________________|_");
        System.out.println("   \\_/__________________________________/");

    }



    public void setting(SettingOption tag) {

        clearConsole();

        System.out.println("   ___                                 ___");
        System.out.println("   | |   _|o _|o _  _    _    _ _ | _  | |");
        System.out.println("   | |  _)||(_||| )(_)  |_)|_|/_/_|(-` | |");
        System.out.println("   | |             _/   |              | |");
        System.out.println(" __| |_________________________________| |__");
        System.out.println("(__| |_________________________________| |__)");


        if (tag == SettingOption.SETTING_GAME_SIZE) {

            System.out.println("   | | Enter game size between 2 - 10: | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |                                 | |");

        } else if (tag == SettingOption.SETTING_KEYBOARD_LAYOUT) {


            System.out.println("   | | Keyboard layouts:               | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |  1.(W.S.A.D) layout             | |");
            System.out.println("   | |  W:up , S:down, A:left, D:right | |");
            System.out.println("   | |                                 | |");
            System.out.println("   | |  2.(U.D.L.R) layout             | |");
            System.out.println("   | |  U:up, D:down, L:left, R:right  | |");
            System.out.println("   | |                                 | |");
        }


        System.out.println(" __| |_________________________________| |__");
        System.out.println("(___________________________________________)");

    }

}
