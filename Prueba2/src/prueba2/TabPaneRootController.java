/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prueba2;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 *
 * @author Emanuel
 */
public class TabPaneRootController{
    
    @FXML private TabPane tabPane;

    //################################# Inject SubController ################################## 
    //Inject tab controller
    @FXML private TabController xxx_tab1_xxxController; //TabPaneRootView.fxml_include_fx:id="xxx_tab_xxx" + "Controller"
    @FXML private TabController xxx_tab2_xxxController;
    @FXML private TabController tab3;
    //#########################################################################################

    public void myInit() {      
        tabPane.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Tab> observable,
                Tab oldValue, Tab newValue)->{
                    preparationInitTab(newValue);                   
                });
    }

    public void preparationInitTab(Tab selectedTab) {
        String currentTabId_string = selectedTab.getId();
        String[] parts = currentTabId_string.split("_");
        int currentTabId = Integer.parseInt(parts[1]); 

        switch(currentTabId) {
        case 1:
            xxx_tab1_xxxController.initTab(currentTabId);
            break;
        case 2:
            xxx_tab2_xxxController.initTab(currentTabId);
            break;

        default:
            System.out.println("Warning: Select an unassigned tab='" + currentTabId + "'");
        }
    }   
    
}
