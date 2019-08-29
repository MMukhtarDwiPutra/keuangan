/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import Model.PengeluaranBulanan;
import View.ViewRiwayat;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ControllerRiwayat implements ActionListener{
    private Database db;
    private ViewRiwayat view;

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getBtnHome())){
            new ControllerHome();
            view.setVisible(false);
        }
    }
    
    public ControllerRiwayat(){
        db = new Database();
        view = new ViewRiwayat();
        loadTable();
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    public void loadTable(){
        db.loadRiwayatPengeluaran();
        ArrayList<PengeluaranBulanan> pengeluaranBulanan = db.getRiwayatPengeluaran();
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pengeluaran Bulanan","Bulan dan Tahun","Jumlah Pengeluaran"},0);
        for(PengeluaranBulanan p : pengeluaranBulanan){
            model.addRow(new Object[]{p.getIdPengeluaranBulanan(),p.getBulanTahun(),p.getJumlahPengeluaranBulanan()});
        }
        view.setTable(model);
    }
}
