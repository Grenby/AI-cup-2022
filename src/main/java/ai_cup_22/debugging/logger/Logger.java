package ai_cup_22.debugging.logger;

public interface Logger {

    void debug(String var1, String var2);

    void debug(String var1, String var2, Throwable var3);

    void info(String var1, String var2);

    void info(String var1, String var2, Throwable var3);

    void error(String var1, String var2);

    void error(String var1, String var2, Throwable var3);

}
