package Controller;

import Model.Database;
import View.Home;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerHome implements ActionListener{
    private Database db;
    private Home view;
    
    public ControllerHome(){
        db = new Database();
        view = new Home();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getBtnPengeluaran())){
            new ControllerPengeluaranBulanan();
            view.setVisible(false);
        }else if(source.equals(view.getBtnRiwayat())){
//            new ControllerPengeluaran();
//            view.setVisible(false);
        }
    }
}
