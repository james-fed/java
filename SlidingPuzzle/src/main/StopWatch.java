package main;

public class StopWatch {

    /**
     * StopWatch class
     */

    static private long startTime = 0, totalTime = 0;

    public static void reset() {
        totalTime = 0L;
    }

    public static void start() {
        startTime = System.currentTimeMillis();
    }

    public static void stop() {
        totalTime += System.currentTimeMillis() - startTime;
    }

    public static long getTimeMillis() {
        return totalTime;
    }

    public static long getTime() {
        return totalTime / 1000;
    }

}
