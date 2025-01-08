package com.example.project11.Controllers.TreeVisualization;

import com.example.project11.Controllers.Controller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class TreeVisualizationController extends Controller implements Initializable {

    @FXML
    AnchorPane rootPane;
    @FXML
    VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TreeNode root = new TreeNode(20, "1 tau");
        root.leftChild = new TreeNode(20, "full");
        root.rightChild = new TreeNode(20, "1.0 Hz");
        root.leftChild.leftChild = new TreeNode(20, "A");
        root.leftChild.rightChild = new TreeNode(20, "D");
        root.rightChild.leftChild = new TreeNode(20, "35");
        root.rightChild.rightChild = new TreeNode(20, "147");
        root.drawTree(0);  // Start drawing from level 0 (root level)
    }

    private class TreeNode {
        private double radius;
        private String property;
        private TreeNode leftChild;
        private TreeNode rightChild;

        public TreeNode(double radius, String property) {
            this.radius = radius;
            this.property = property;
        }

        public void drawTree(int level) {
            // Ensure we have enough HBoxes in container for the current level
            while (container.getChildren().size() <= level) {
                container.getChildren().add(new HBox(5*(level) + 20)); // Create new HBox for the next level
            }

            // Get the HBox for the current level
            HBox currentLevelHBox = (HBox) container.getChildren().get(level);
            currentLevelHBox.setAlignment(Pos.CENTER);  // Center-align the nodes in the HBox

            // Create the node for this TreeNode
            StackPane nodePane = createTreeNode(property, radius);

            // Add this node to the current level's HBox
            currentLevelHBox.getChildren().add(nodePane);

            // Recursively draw the left and right children (if they exist)
            if (leftChild != null) {
                leftChild.drawTree(level + 1);  // Draw at the next level for the left child
            }
            if (rightChild != null) {
                rightChild.drawTree(level + 1);  // Draw at the next level for the right child
            }
        }

        private StackPane createTreeNode(String labelText, double radius) {
            Circle circle = new Circle(radius, createGradient());
            circle.setStroke(Color.BLACK);

            Text label = new Text(labelText);
            label.setFont(Font.font(radius / 2.5)); // Adjust font size based on circle radius
            label.setFill(Color.WHITE);

            StackPane stackPane = new StackPane();
            stackPane.getChildren().addAll(circle, label);
            return stackPane;
        }

        private LinearGradient createGradient() {
            return new LinearGradient(
                    0, 0, 1, 1, // Start (0, 0) and End (1, 1) coordinates
                    true, CycleMethod.NO_CYCLE,
                    new Stop(0, Color.LIGHTBLUE),  // Start color
                    new Stop(1, Color.DARKBLUE)   // End color
            );
        }
    }
}
