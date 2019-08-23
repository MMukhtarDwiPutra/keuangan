/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.ViewMakan;

/**
 *
 * @author User
 */
public class ControllerMakan {
    private Database db;
    private ViewMakan view;
    
    
    public ControllerMakan(){
        view = new ViewMakan();
        view.setVisible(true);
    }
    
}
