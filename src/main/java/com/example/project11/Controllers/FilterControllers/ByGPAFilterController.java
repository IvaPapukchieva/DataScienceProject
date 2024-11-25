package com.example.project11.Controllers.FilterControllers;

import com.example.project11.Controllers.Controller;
import com.example.project11.DataCallback;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class ByGPAFilterController extends AbstractFilterController{

    public void handleAddFilter() {
        super.handleAddFilter();
    }

    @Override
    protected void sendDataToFirstWindow() {
        DataCallback dataCallback = super.getDataCallback();
        if (dataCallback == null) return;
        dataCallback.onDataReceived(sliderRange1,sliderRange2);
    }
}
