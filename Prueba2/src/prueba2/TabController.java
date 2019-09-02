/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

/**
 *
 * @author Emanuel
 */
public class TabController{
    
    private int tabId = -9; //is the ID of the currently selected tab

    @FXML private TextField tab_textField_currentTabName;
    @FXML private TextField tab_textField01_costPosition1;

    public void initTab(int currentTabId) {
        System.out.println(">TabController::initTab() with currentTabId=" + currentTabId);
        this.tabId = currentTabId;      
        tab_textField_currentTabName.setText(String.valueOf(tabId));
    }

    //----------------- FXML-Methoden --------------------------    
    @FXML
    private void handleTabTextFieldCostPosition1() {
        System.out.println("costPosition1[" + tabId + "]=" + tab_textField01_costPosition1.getText());
    }

    @FXML
    private void handleExit() {     
        Platform.exit();
    }
    
}
