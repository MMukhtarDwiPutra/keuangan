package Controller;

import Model.Database;
import View.ViewPengeluaran;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerPengeluaran implements ActionListener{
    private Database db;
    private ViewPengeluaran view;
    
    public ControllerPengeluaran(){
        db = new Database();
        view = new ViewPengeluaran();
        view.addActionListener(this);
        view.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String tanggal = view.getTxtTanggal().getText();
        if(source.equals(view.getBtnTambahMakan())){
            
        }else if(source.equals(view.getBtnTambahBarang())){
            new ControllerBarang(tanggal);
        }else if(source.equals(view.getBtnTambahPengeluaran())){
            
        }
        
    }
}
