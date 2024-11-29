    package com.example.project11.Controllers;

    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.control.Button;
    import javafx.scene.control.Label;
    import javafx.scene.control.MenuItem;
    import javafx.scene.control.Slider;
    import javafx.scene.layout.VBox;

    import javafx.event.ActionEvent ;

    import java.net.URL;
    import java.util.ArrayList;
    import java.util.ResourceBundle;

    public class SeparateQuestions extends Controller implements Initializable {
        // Track selected phases (if needed)
        public ArrayList<String> sequence = new ArrayList<>();
        {
            sequence.add(" ");
            sequence.add(" ");
            sequence.add(" ");
        }

        public double currentsliderValue ;

        @FXML
        private Slider mySlider; // Connect this to your Slider in FXML
        @FXML
        private Button button;

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

                MenuItem selectedMenuItem = (MenuItem) event.getSource();
                String subsection = selectedMenuItem.getText();
                String property = getParentMenuText(selectedMenuItem);
                String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
                System.out.println("Selected Function: " + selectedFunction);
                sequence.set(0, (property));
                sequence.set(1, (selectedFunction));
            }
            else{
                MenuItem selectedMenuItem = (MenuItem) event.getSource();
                String subsection = selectedMenuItem.getText();
                String property = getParentMenuText(selectedMenuItem);
                String selectedFunction = "Property: " + property + ", Subsection: " + subsection;
                System.out.println("Selected Function: " + selectedFunction);
                sequence.add(0, (property));
                sequence.add(1, (selectedFunction));



            }
            System.out.println(sequence);

        }

        @FXML
        private void getSliderValue(ActionEvent event) {

                double sliderValue = mySlider.getValue();
                if (sequence.size() < 3) {
                    sequence.add(String.valueOf(sliderValue));
                } else {
                    sequence.set(2, String.valueOf(sliderValue));
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
