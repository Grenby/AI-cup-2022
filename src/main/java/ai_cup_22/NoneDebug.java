package ai_cup_22;

import ai_cup_22.debugging.*;
import ai_cup_22.model.Vec2;

import java.io.InputStream;
import java.io.OutputStream;

public class NoneDebug extends DebugInterface{
    public NoneDebug(InputStream inputStream, OutputStream outputStream) {
        super(inputStream, outputStream);
    }

    @Override
    public void addPlacedText(Vec2 position, String text, Vec2 alignment, double size, Color color) {
    }

    @Override
    public void addCircle(Vec2 position, double radius, Color color) {
    }

    @Override
    public void addGradientCircle(Vec2 position, double radius, Color innerColor, Color outerColor) {
    }

    @Override
    public void addRing(Vec2 position, double radius, double width, Color color) {
    }

    @Override
    public void addPie(Vec2 position, double radius, double startAngle, double endAngle, Color color) {
    }

    @Override
    public void addArc(Vec2 position, double radius, double width, double startAngle, double endAngle, Color color) {
    }

    @Override
    public void addRect(Vec2 bottomLeft, Vec2 size, Color color) {
    }

    @Override
    public void addPolygon(Vec2[] vertices, Color color) {
    }

    @Override
    public void addGradientPolygon(ColoredVertex[] vertices) {
    }

    @Override
    public void addSegment(Vec2 firstEnd, Vec2 secondEnd, double width, Color color) {
    }

    @Override
    public void addGradientSegment(Vec2 firstEnd, Color firstColor, Vec2 secondEnd, Color secondColor, double width) {
    }

    @Override
    public void addPolyLine(Vec2[] vertices, double width, Color color) {
    }

    @Override
    public void addGradientPolyLine(ColoredVertex[] vertices, double width) {
    }

    @Override
    public void add(DebugData debugData) {
    }

    @Override
    public void clear() {
    }

    @Override
    public void setAutoFlush(boolean enable) {
    }

    @Override
    public void flush() {
    }

    @Override
    public void send(DebugCommand command) {
    }

    @Override
    public DebugState getState() {
        return null;
    }
}
