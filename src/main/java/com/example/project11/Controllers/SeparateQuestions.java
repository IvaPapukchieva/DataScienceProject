    package com.example.project11.Controllers;

    import javafx.collections.FXCollections;
    import javafx.collections.ObservableList;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.ComboBox;
    import javafx.scene.control.MenuItem;
    import javafx.scene.layout.VBox;

    import javafx.event.ActionEvent ;

    import java.net.URL;
    import java.util.ArrayList;
    import java.util.ResourceBundle;

    public class SeparateQuestions extends Controller implements Initializable {
        // Track selected phases (if needed)
        public ArrayList<String> sequence = new ArrayList<>();

        @FXML
        private VBox childComboBox; // VBox for dynamically added ComboBoxes
        @FXML
        private MenuItem step1MenuItem;
        @FXML
        private MenuItem step2MenuItem;
        @FXML
        private MenuItem step3MenuItem;
        @FXML
        private MenuItem step4MenuItem;


        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {

        }

        @FXML
        private void handleMenuSelection(ActionEvent event) {

            if(!sequence.isEmpty()){
                sequence.clear();
                MenuItem selectedMenuItem = (MenuItem) event.getSource();
                String subsection = selectedMenuItem.getText();
                String property = getParentMenuText(selectedMenuItem);
                String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
                System.out.println("Selected Function: " + selectedFunction);
                sequence.add(property);
                sequence.add(selectedFunction);
            }
            else{
                MenuItem selectedMenuItem = (MenuItem) event.getSource();
                String subsection = selectedMenuItem.getText();
                String property = getParentMenuText(selectedMenuItem);
                String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
                System.out.println("Selected Function: " + selectedFunction);
                sequence.add(property);
                sequence.add(selectedFunction);
            }
            System.out.println(sequence);

        }


        private String getParentMenuText(MenuItem menuItem) {
            if (menuItem.getParentMenu() != null) {
                return menuItem.getParentMenu().getText(); // Direct parent is the Menu (Property)
            }
            return "Unknown";
        }


    }
