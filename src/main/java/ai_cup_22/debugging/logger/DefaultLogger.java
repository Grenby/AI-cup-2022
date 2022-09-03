package ai_cup_22.debugging.logger;

public class DefaultLogger implements Logger{
    public void debug(String tag, String message) {
        this.println("DEBUG", tag, message);
    }

    public void debug(String tag, String message, Throwable exception) {
        this.println("DEBUG", tag, message, exception);
    }

    public void info(String tag, String message) {
        this.println("INFO", tag, message);
    }

    public void info(String tag, String message, Throwable exception) {
        this.println("INFO", tag, message, exception);
    }

    public void error(String tag, String message) {
        this.println("ERROR", tag, message);
    }

    public void error(String tag, String message, Throwable exception) {
        this.println("ERROR", tag, message, exception);
    }

    private void println(String level, String tag, String message) {
        System.out.println(level + " " + tag + ": " + message);
    }

    private void println(String level, String tag, String message, Throwable exception) {
        this.println(level, tag, message);
        exception.printStackTrace();
    }
}
