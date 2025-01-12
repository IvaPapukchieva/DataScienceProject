package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.*;

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
    private int index;
    @FXML
    private List<String> studentProperties;
    private double grade;
    @FXML
    private Text prediction;
    @FXML
    private VBox buttonBox;
    private double scaleFactor = 0.5;
//here we will store all the trees;
//    first element is the index and the second saves the tree;
private static Map<String, TreeProperties> treeMap = new HashMap<>();


    public void passProperties(Integer index, List<String> labels, int levels, List<String> studentProperties, double grade) {
        this.labels = labels;
        this.levels = levels;
        this.index = index;
        this.studentProperties = studentProperties;
        this.grade = grade;
        TreeProperties treeProps = new TreeProperties(index, labels, levels, studentProperties, grade);
        treeMap.put(String.valueOf(index), treeProps);
      addTreeButtons(String.valueOf(index));
            rootPane.setOnScroll(this::handleScroll);
            System.out.println(treeMap);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (treeMap == null) {
            treeMap = new HashMap<>();

        }

        if (labels != null && levels > 0) {
            scrollPane.setFitToWidth(true);
            scrollPane.setFitToHeight(true);
            scrollPane.setContent(rootPane);
            scrollPane.setPannable(true);

//
        }
    }
    public class TreeProperties {
        private Integer index;
        private List<String> labels;
        private int levels;
        private List<String> studentProperties;
        private double grade;

        public TreeProperties(Integer index, List<String> labels, int levels, List<String> studentProperties, double grade) {
            this.index = index;
            this.labels = labels;
            this.levels = levels;
            this.studentProperties = studentProperties;
            this.grade = grade;
        }

        // Getters and setters
        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public List<String> getLabels() {
            return labels;
        }

        public void setLabels(List<String> labels) {
            this.labels = labels;
        }

        public int getLevels() {
            return levels;
        }

        public void setLevels(int levels) {
            this.levels = levels;
        }

        public List<String> getStudentProperties() {
            return studentProperties;
        }

        public void setStudentProperties(List<String> studentProperties) {
            this.studentProperties = studentProperties;
        }

        public double getGrade() {
            return grade;
        }

        public void setGrade(double grade) {
            this.grade = grade;
        }
    }

    private void drawTree() {
        rootPane.getChildren().clear();  // Clear any existing tree
        TreeNode tree = new TreeNode();
        tree.generateTree(rootPane, labels.iterator(), 700, 100, 600, 90, levels);

        System.out.println("Drawing tree with index: " + index);
        updateBulletPoints();
        updateGrade(grade);
    }
    public void addTreeButtons(String name) {
        Button treeButton = new Button(name);
        treeButton.setStyle(
                "-fx-background-color: transparent; " +
                        "-fx-background-radius: 10; " +
                        "-fx-text-fill: #ADD8E6; " +
                        "-fx-pref-width: 50px; " +
                        "-fx-pref-height: 30px; " +
                        "-fx-border-radius: 10; " +
                        "-fx-border-width: 2px; " +
                        "-fx-border-color: #ADD8E6;"
        );

        treeButton.setOnMousePressed(event -> {
            treeButton.setStyle(
                    "-fx-background-color: #ADD8E6; " +
                            "-fx-background-radius: 10; " +
                            "-fx-text-fill: white; " +
                            "-fx-pref-width: 50px; " +
                            "-fx-pref-height: 30px; " +
                            "-fx-border-radius: 10; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-color: #ADD8E6;"
            );
        });

        treeButton.setOnMouseReleased(event -> {
            treeButton.setStyle(
                    "-fx-background-color: transparent; " +
                            "-fx-background-radius: 10; " +
                            "-fx-text-fill: #ADD8E6; " +
                            "-fx-pref-width: 50px; " +
                            "-fx-pref-height: 30px; " +
                            "-fx-border-radius: 10; " +
                            "-fx-border-width: 2px; " +
                            "-fx-border-color: #ADD8E6;"
            );
        });


        treeButton.setOnAction(event -> {
            openTree(name);
        });

        buttonBox.getChildren().add(treeButton);
    }

    public void openTree(String treeIndex) {
        System.out.println("Opening tree with index: " + treeIndex);

        if (treeMap.containsKey(treeIndex)) {

            TreeProperties treeProps = treeMap.get(treeIndex);

            labels = treeProps.getLabels();
            levels = treeProps.getLevels();
            studentProperties = treeProps.getStudentProperties();
            grade = treeProps.getGrade();
            drawTree();
        } else {
            System.out.println("Tree not found: " + treeIndex);
        }
    }


    private void handleScroll(ScrollEvent event) {
        double zoomFactor = 1.1;
        if (event.getDeltaY() < 0) {
            zoomFactor = 1 / zoomFactor;
        }

        double oldScale = rootPane.getScaleX();
        double newScale = oldScale * zoomFactor;


        if (newScale < 0.1) newScale = 0.1;
        if (newScale > 2.0) newScale = 2.0;

        double pivotX = event.getSceneX() - rootPane.getBoundsInParent().getMinX();
        double pivotY = event.getSceneY() - rootPane.getBoundsInParent().getMinY();

        // Apply scaling transformation with the pivot point
        rootPane.setScaleX(newScale);
        rootPane.setScaleY(newScale);

        // Adjust the translation to keep the zoom focused around the mouse pointer
        rootPane.setTranslateX(rootPane.getTranslateX() - (pivotX * (newScale - oldScale)));
        rootPane.setTranslateY(rootPane.getTranslateY() - (pivotY * (newScale - oldScale)));

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

            if (currentLabel.contains("leaf")) {
                createInteractiveLeaf(pane, labelIterator, x, y);
                return;
            }

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

        public void createInteractiveLeaf(Pane pane, Iterator<String> labelIterator, double x, double y) {
            // Create a rectangle for the leaf with rounded corners
            Rectangle leaf = new Rectangle(x - 30, y - 15, 60, 30);  // Rectangular shape for the leaf
            leaf.setArcWidth(15);  // Rounded corners
            leaf.setArcHeight(15);  // Rounded corners

            // Set the blue gradient fill
            LinearGradient blueGradient = new LinearGradient(
                    0, 0, 1, 1,
                    true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#2C3E50")),  // Darker blue
                    new Stop(1, Color.web("#34495E"))   // Even darker blue
            );
            leaf.setFill(blueGradient);  // Apply the gradient to the leaf

            // Add text for the leaf
            String text = labelIterator.next();
            Text nodeText = new Text(text);
            nodeText.setFont(Font.font("Arial", FontWeight.BOLD, 14));
            nodeText.setFill(Color.WHITE);
            nodeText.setX(x - (nodeText.getLayoutBounds().getWidth() / 2));  // Centering text
            nodeText.setY(y + 5);  // Position text inside the leaf

            // Tooltip for the leaf
            Tooltip tooltip = new Tooltip("Node: " + text);
            Tooltip.install(leaf, tooltip);

            // Hover effect - Change color and add a subtle shadow
            leaf.setOnMouseEntered(event -> {
                leaf.setFill(Color.DODGERBLUE);  // Change color to a brighter blue on hover
                leaf.setEffect(new DropShadow(5, Color.GRAY));  // Add a shadow effect on hover
            });

            leaf.setOnMouseExited(event -> {
                leaf.setFill(blueGradient);  // Reset the gradient color
                leaf.setEffect(null);  // Remove the shadow when mouse leaves
            });

            // Add the leaf and text to the pane
            pane.getChildren().addAll(leaf, nodeText);
        }




        private void createInteractiveNode(Pane pane, String text, double x, double y) {
            LinearGradient gradient = createGradient();
            Rectangle rect = new Rectangle(x - 35, y - 20, 70, 40);
            rect.setArcWidth(20);
            rect.setArcHeight(20);
            rect.setFill(gradient);
            rect.setStroke(Color.BLACK);

            Text nodeText = new Text(text);
            nodeText.setDisable(true);
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