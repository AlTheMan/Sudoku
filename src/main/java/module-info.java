module com.example.labb4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens se.kth.AlgotVREmilW.labb4 to javafx.fxml;
    exports se.kth.AlgotVREmilW.labb4;
    exports se.kth.AlgotVREmilW.labb4.model;
    opens se.kth.AlgotVREmilW.labb4.model to javafx.fxml;
}