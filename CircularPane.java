
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

public class CircularPane extends Pane {
    @Override
    protected void layoutChildren() {
        final int radius = 200;
        final double increment = 360 / (getChildren().size()-1);
        double degrees = 0;
        for (Node node : getChildren()) {
            double x = radius * Math.cos(Math.toRadians(degrees)) + getWidth() / 2;
            double y = radius * Math.sin(Math.toRadians(degrees)) + getHeight() / 2;
            layoutInArea(node, x - node.getBoundsInLocal().getWidth() / 2, y - node.getBoundsInLocal().getHeight() / 2, getWidth(), getHeight(), 0.0, HPos.LEFT, VPos.TOP);
            degrees += increment;
        }
    }
}