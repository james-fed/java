package main;

import gameComponents.GameData;
import gameComponents.GameTiles;
import gameComponents.Tile;
import gameInfo.*;
import userInterface.CLI;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	/**
	 * main class just get input from user and pass it to another class
	 */

	private static Scanner scanner = new Scanner(System.in);
	private static CLI cli = new CLI();

	public static void main(String[] args) {



		SettingManager.implementSettingInGame();
		// get char for the menu
		char menuOptionsChar;
		do {
			String input = "";
			menuOptionsChar = '\0';

			cli.implementMenu(MainMenuOption.values(), false);
			do {
				input = scanner.nextLine().trim();
				if (input.length() == 1)
					menuOptionsChar = input.charAt(0);
			} while (!(MainMenuOption.EXIT.getTag() == menuOptionsChar
					|| MainMenuOption.START_GAME.getTag() == menuOptionsChar
					|| MainMenuOption.SETTING.getTag() == menuOptionsChar));

			if (menuOptionsChar == MainMenuOption.START_GAME.getTag())
				startGame(Setting.getGameSize());
			else if (menuOptionsChar == MainMenuOption.SETTING.getTag())
				gameSetting();
			else if (menuOptionsChar == MainMenuOption.EXIT.getTag())
				cli.clearConsole();

		} while (menuOptionsChar != MainMenuOption.EXIT.getTag());

	}



	private static void startGame(int gameSize) {

		StopWatch.start();

		GameHandler gameHandler = new GameHandler(gameSize);

		// this update is for first show
		cli.updateGameBoard(gameHandler.updateGameData(KeyDirectionTag.NO_DIRECTION), 0);

		String input = "";
		boolean winState = false;

		char directionChar;
		do {
			// get char until game in running
			directionChar = '\0';
			do {
				input = scanner.nextLine().trim();
				if (input.length() == 1)
					directionChar = input.charAt(0);
			} while (!(directionChar == KeyDirectionTag.UP.getTag(Setting.getKeyboardLayout())
					|| directionChar == KeyDirectionTag.DOWN.getTag(Setting.getKeyboardLayout())
					|| directionChar == KeyDirectionTag.LEFT.getTag(Setting.getKeyboardLayout())
					|| directionChar == KeyDirectionTag.RIGHT.getTag(Setting.getKeyboardLayout())
					|| directionChar == KeyDirectionTag.BACK.getTag(Setting.getKeyboardLayout())));

			if (directionChar == KeyDirectionTag.UP.getTag(Setting.getKeyboardLayout()))
				cli.updateGameBoard(gameHandler.updateGameData(KeyDirectionTag.UP), GameData.getMoveCount());
			else if (directionChar == KeyDirectionTag.DOWN.getTag(Setting.getKeyboardLayout()))
				cli.updateGameBoard(gameHandler.updateGameData(KeyDirectionTag.DOWN), GameData.getMoveCount());
			else if (directionChar == KeyDirectionTag.LEFT.getTag(Setting.getKeyboardLayout()))
				cli.updateGameBoard(gameHandler.updateGameData(KeyDirectionTag.LEFT), GameData.getMoveCount());
			else if (directionChar == KeyDirectionTag.RIGHT.getTag(Setting.getKeyboardLayout()))
				cli.updateGameBoard(gameHandler.updateGameData(KeyDirectionTag.RIGHT), GameData.getMoveCount());

		} while (!(winState = gameHandler.isWin())
				&& directionChar != KeyDirectionTag.BACK.getTag(Setting.getKeyboardLayout()));

		StopWatch.stop();
		int score = (int) ((1000 * Math.pow(gameSize, 2)) / Math.log(GameData.getMoveCount())
				* Math.cbrt(StopWatch.getTime()));
		GameData.setScore(score);

		if (winState) {
			cli.winMessage(score);
			scanner.nextLine();
		}
	}

	private static void gameSetting() {

		char optionChar = '\0';
		do {
			cli.implementMenu(SettingOption.values(), true);

			do {
				String value;
				value = scanner.nextLine().trim();
				if (value.length() == 1)
					optionChar = value.charAt(0);

			} while (!(optionChar == SettingOption.SETTING_GAME_SIZE.getTag()
					|| optionChar == SettingOption.SETTING_KEYBOARD_LAYOUT.getTag()
					|| optionChar == KeyDirectionTag.BACK.getTag(Setting.getKeyboardLayout())));

			if (optionChar == SettingOption.SETTING_GAME_SIZE.getTag()) {
				cli.setting(SettingOption.SETTING_GAME_SIZE);
				boolean validNumber = false;
				int gameSize = 0;
				do {
					try {
						gameSize = scanner.nextInt();
						scanner.nextLine();// flushing the buffer
						if (2 <= gameSize && gameSize <= 10)
							validNumber = true;
					} catch (InputMismatchException e) {
						scanner.next();
					}
				} while (!validNumber);

				SettingManager.setSetting(gameSize, Setting.getKeyboardLayout());

				System.out.println(gameSize);

			} else if (optionChar == SettingOption.SETTING_KEYBOARD_LAYOUT.getTag()) {
				cli.setting(SettingOption.SETTING_KEYBOARD_LAYOUT);

				int optionValue = 0;
				boolean isValidInput = false;
				do {
					try {
						optionValue = scanner.nextInt();
						scanner.nextLine();
						if (optionValue == 1 || optionValue == 2)
							isValidInput = true;
					} catch (InputMismatchException e) {
						scanner.next();
					}
				} while (!isValidInput);

				SettingManager.setSetting(Setting.getGameSize(),
						(optionValue == 2) ? KeyboardLayout.DEFAULT : KeyboardLayout.WSAD);
			}
		} while (optionChar != KeyDirectionTag.BACK.getTag(Setting.getKeyboardLayout()));
	}

}