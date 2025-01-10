package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

public class TreeVisualizationController extends Controller implements Initializable {
    @FXML
    private ScrollPane scrollPane;

// In your initialize or constructor method
@FXML
private Slider zoomSlider;
    @FXML
    private Pane rootPane;
    @FXML
    private VBox bulletBox;
    private List<String> labels;
    private int levels;
    @FXML
    private List<String> studentProperties;
    private double grade;
    @FXML
    private Text prediction;
    private double scaleFactor = 0.5;

    public void passProperties(List<String> labels, int levels, List<String> studentProperties, double grade) {
        this.labels = labels;
        this.levels = levels;
        this.studentProperties = studentProperties;
        this.grade = grade;
        if (rootPane != null) {
            drawTree();
            updateBulletPoints();
            updateGrade(grade);
            rootPane.setOnScroll(this::handleScroll);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (labels != null && levels > 0) {
            drawTree();
            updateBulletPoints();
            updateGrade(grade);
            scrollPane.setContent(rootPane);
        }
    }

    private void drawTree() {
        rootPane.getChildren().clear();
        TreeNode tree = new TreeNode();
        Iterator<String> labelIterator = labels.iterator();
        tree.generateTree(rootPane, labelIterator, 700, 100, 600, 90, levels);
    }



    private void handleScroll(ScrollEvent event) {

        if (event.getDeltaY() > 0) {
            scaleFactor += 0.03;  // Zoom in
        } else if (event.getDeltaY() < 0) {
            scaleFactor -= 0.03;  // Zoom out
        }

        if (scaleFactor < 0.1) {
            scaleFactor = 0.1;
        } else if (scaleFactor > 2.0) {
            scaleFactor = 2.0;
        }

        rootPane.setScaleX(scaleFactor);
        rootPane.setScaleY(scaleFactor);

        event.consume();
    }
    public void updateBulletPoints() {
        bulletBox.getChildren().remove(1, bulletBox.getChildren().size());

        for (String point : studentProperties) {
            Text bullet = new Text("• " + point);
            bullet.setStyle("-fx-font-size: 14px; -fx-font-family: 'Arial'; -fx-fill:#ADD8E6;");
            bulletBox.getChildren().add(bullet);
        }
    }

    public void updateGrade(double grade) {
        prediction.setText(String.valueOf(grade));
    }

    public class TreeNode {
        public void generateTree(Pane pane, Iterator<String> labelIterator, double x, double y, double xSpacing, double ySpacing, int levels) {
            if (levels == 0 || !labelIterator.hasNext()) {
                return;
            }

            String currentLabel = labelIterator.next();
            createInteractiveNode(pane, currentLabel, x, y);

            double leftChildX = x - xSpacing / 2;
            double leftChildY = y + ySpacing;
            double rightChildX = x + xSpacing / 2;
            double rightChildY = y + ySpacing;

            if (levels > 1 && labelIterator.hasNext()) {
                connectNodes(pane, x, y + 20, leftChildX, leftChildY - 20, "YES");
                generateTree(pane, labelIterator, leftChildX, leftChildY, xSpacing / 2, ySpacing, levels - 1);
            }

            if (levels > 1 && labelIterator.hasNext()) {
                connectNodes(pane, x, y + 20, rightChildX, rightChildY - 20, "NO");
                generateTree(pane, labelIterator, rightChildX, rightChildY, xSpacing / 2, ySpacing, levels - 1);
            }
        }


        private void createInteractiveNode(Pane pane, String text, double x, double y) {
            LinearGradient gradient = createGradient();
            Rectangle rect = new Rectangle(x - 35, y - 20, 70, 40);
            rect.setArcWidth(20);
            rect.setArcHeight(20);
            rect.setFill(gradient);
            rect.setStroke(Color.BLACK);

            Text nodeText = new Text(text);
            nodeText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            nodeText.setFill(Color.WHITE);
            nodeText.setX(x - (nodeText.getLayoutBounds().getWidth() / 2));
            nodeText.setY(y + 5);

            Tooltip tooltip = new Tooltip("Node: " + text);
            Tooltip.install(rect, tooltip);

            rect.setOnMouseEntered(event -> rect.setFill(Color.LIGHTCYAN));
            rect.setOnMouseExited(event -> rect.setFill(gradient));  // Reset to gradient

            pane.getChildren().addAll(rect, nodeText);
        }

        private void connectNodes(Pane pane, double startX, double startY, double endX, double endY, String label) {
            Line line = new Line(startX, startY, endX, endY);
            line.setStroke(Color.rgb(48, 74, 98));
            line.setStrokeWidth(1.5);

            Text text = new Text(label);
            text.setFont(Font.font("Arial", FontWeight.BOLD, 12));
            text.setFill(label.equals("YES") ? Color.GREEN : Color.RED);
            text.setX((startX + endX) / 2 - 10);
            text.setY((startY + endY) / 2 - 5);

            pane.getChildren().addAll(line, text);
        }

        private LinearGradient createGradient() {
            return new LinearGradient(
                    0, 0, 1, 1,
                    true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#3F5A7D")),  // Darker version of #6082B6
                    new Stop(1, Color.web("#8BB8D6"))   // Darker version of #ADD8E6
            );
        }
    }
}