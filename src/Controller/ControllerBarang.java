/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Database;
import Model.Barang;
import View.ViewBarang;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class ControllerBarang extends MouseAdapter implements ActionListener {
    private Database db;
    private ViewBarang view;
    private int idPengeluaran;
    private String bulanTahun;
    private String hariTanggal;
    private int idPengeluaranBulanan;
    
    public ControllerBarang(int idPengeluaran, String bulanTahun, String hariTanggal, int idPengeluaranBulanan){
        this.idPengeluaran = idPengeluaran;
        this.bulanTahun = bulanTahun;
        this.hariTanggal = hariTanggal;
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        db = new Database();
        view = new ViewBarang(bulanTahun, hariTanggal);
        view.addActionListener(this);
        view.addMouseAdapter(this);
        loadTable();
        view.setVisible(true);
    }
    
    public void loadTable(){
        db.loadBarang(idPengeluaran);
        ArrayList<Barang> barang = db.getBarang();
        DefaultTableModel model = new DefaultTableModel(new String[]{"Nama Barang","Keperluan","Harga"},0);
        for(Barang b : barang){
            model.addRow(new Object[]{b.getNama(),b.getKeperluan(),b.getHarga()});
        }
        view.setTableModel(model);
    }
    
    public void tambahBarang(Barang b){
        db.tambahBarang(b, idPengeluaran);
        view.setVisible(false);
        new ControllerBarang(idPengeluaran,bulanTahun,hariTanggal,idPengeluaranBulanan);
        db.updateTambahPengeluaran(idPengeluaran, b.getHarga());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String nama = view.getTxtNamaBarang().getText();
        String keperluan = view.getTxtKeperluan().getText();
        long harga;
        if(source.equals(view.getBtnBack())){
            new ControllerPengeluaran(idPengeluaranBulanan,bulanTahun);
            view.setVisible(false);
        }else if(source.equals(view.getBtnTambah())){
            harga = Long.parseLong(view.getTxtHarga().getText());
            tambahBarang(new Barang(nama,keperluan,harga));
        }
    }
    
    @Override
    public void mousePressed(MouseEvent m){
        
    }
}
