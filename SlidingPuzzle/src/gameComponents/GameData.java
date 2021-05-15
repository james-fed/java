package gameComponents;

public class GameData {


    private static long time;
    private static long moveCount;
    private static long score;


    public static long getTime() {
        return time;
    }

    public static void setTime(long time) {
        GameData.time = time;
    }

    public static long getScore() {
        return score;
    }

    public static void setScore(long score) {
        GameData.score = score;
    }

    public static long getMoveCount() {
        return moveCount;
    }

    public static void setMoveCount(int moveCount) {
        GameData.moveCount = moveCount;
    }
}
