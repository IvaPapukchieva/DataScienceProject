package com.example.project11;

import javafx.scene.*;

@FunctionalInterface
public interface DataCallback {
    void onDataReceived(Node... nodes);


}