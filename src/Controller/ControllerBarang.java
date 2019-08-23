/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import View.ViewBarang;

/**
 *
 * @author User
 */
public class ControllerBarang {
    private Database db;
    private ViewBarang view;
    
    public ControllerBarang() {
        view = new ViewBarang();
        view.setVisible(true);
    }
    
    public ControllerBarang(String tanggal) {
        view = new ViewBarang();
        view.setVisible(true);
    }
}
