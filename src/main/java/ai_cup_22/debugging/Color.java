package ai_cup_22.debugging;

import ai_cup_22.util.StreamUtil;

/**
 * RGBA Color
 */
public class Color extends com.badlogic.gdx.graphics.Color {

    public Color(double r, double g, double b, double a) {
        this.r = (float) r;
        this.g = (float) g;
        this.b = (float) b;
        this.a = (float) a;
    }

    public Color(int rgba8888) {
        rgba8888ToColor(this, rgba8888);
    }

    public static void rgba8888ToColor(Color color, int value) {
        color.r = ((value & 0xff000000) >>> 24) / 255f;
        color.g = ((value & 0x00ff0000) >>> 16) / 255f;
        color.b = ((value & 0x0000ff00) >>> 8) / 255f;
        color.a = ((value & 0x000000ff)) / 255f;
    }

    public static Color readFrom(java.io.InputStream stream) throws java.io.IOException {
        double r;
        r = StreamUtil.readDouble(stream);
        double g;
        g = StreamUtil.readDouble(stream);
        double b;
        b = StreamUtil.readDouble(stream);
        double a;
        a = StreamUtil.readDouble(stream);
        return new Color(r, g, b, a);
    }

    public double getR() {
        return r;
    }

    public void setR(double value) {
        this.r = (float) value;
    }

    public double getG() {
        return g;
    }

    public void setG(double value) {
        this.g = (float) value;
    }

    public double getB() {
        return b;
    }

    public void setB(double value) {
        this.b = (float) value;
    }

    public double getA() {
        return a;
    }

    public void setA(double value) {
        this.a = (float) value;
    }

    /**
     * Write Color to output stream
     */
    public void writeTo(java.io.OutputStream stream) throws java.io.IOException {
        StreamUtil.writeDouble(stream, r);
        StreamUtil.writeDouble(stream, g);
        StreamUtil.writeDouble(stream, b);
        StreamUtil.writeDouble(stream, a);
    }

    /**
     * Get string representation of Color
     */
    @Override
    public String toString() {
        return "Color { " + "r: " +
                r +
                ", " +
                "g: " +
                g +
                ", " +
                "b: " +
                b +
                ", " +
                "a: " +
                a +
                " }";
    }

//    public static final Color CLEAR = new Color(0.0F, 0.0F, 0.0F, 0.0F);
//    public static final Color BLACK = new Color(0.0F, 0.0F, 0.0F, 1.0F);
//    public static final Color WHITE = new Color(-1);
//    public static final Color LIGHT_GRAY = new Color(-1077952513);
//    public static final Color GRAY = new Color(2139062271);
//    public static final Color DARK_GRAY = new Color(1061109759);
//    public static final Color BLUE = new Color(0.0F, 0.0F, 1.0F, 1.0F);
//    public static final Color NAVY = new Color(0.0F, 0.0F, 0.5F, 1.0F);
//    public static final Color ROYAL = new Color(1097458175);
//    public static final Color SLATE = new Color(1887473919);
//    public static final Color SKY = new Color(-2016482305);
//    public static final Color CYAN = new Color(0.0F, 1.0F, 1.0F, 1.0F);
//    public static final Color TEAL = new Color(0.0F, 0.5F, 0.5F, 1.0F);
//    public static final Color GREEN = new Color(16711935);
//    public static final Color CHARTREUSE = new Color(2147418367);
//    public static final Color LIME = new Color(852308735);
//    public static final Color FOREST = new Color(579543807);
//    public static final Color OLIVE = new Color(1804477439);
//    public static final Color YELLOW = new Color(-65281);
//    public static final Color GOLD = new Color(-2686721);
//    public static final Color GOLDENROD = new Color(-626712321);
//    public static final Color ORANGE = new Color(-5963521);
//    public static final Color BROWN = new Color(-1958407169);
//    public static final Color TAN = new Color(-759919361);
//    public static final Color FIREBRICK = new Color(-1306385665);
//    public static final Color RED = new Color(-16776961);
//    public static final Color SCARLET = new Color(-13361921);
//    public static final Color CORAL = new Color(-8433409);
//    public static final Color SALMON = new Color(-92245249);
//    public static final Color PINK = new Color(-9849601);
//    public static final Color MAGENTA = new Color(1.0F, 0.0F, 1.0F, 1.0F);
//    public static final Color PURPLE = new Color(-1608453889);
//    public static final Color VIOLET = new Color(-293409025);
//    public static final Color MAROON = new Color(-1339006721);

}