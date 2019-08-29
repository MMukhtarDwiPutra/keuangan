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
    private int kolomIdPengeluaranBarang;
    private long kolomHarga;
    
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
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pengeluaran Barang","Nama Barang","Keperluan","Harga"},0);
        for(Barang b : barang){
            model.addRow(new Object[]{b.getIdPengeluaranBarang(),b.getNama(),b.getKeperluan(),b.getHarga()});
        }
        view.setTableModel(model);
    }
    
    public void tambahBarang(Barang b){
        db.tambahBarang(b, idPengeluaran);
        view.setVisible(false);
        new ControllerBarang(idPengeluaran,bulanTahun,hariTanggal,idPengeluaranBulanan);
        db.updateTambahPengeluaran(idPengeluaranBulanan, idPengeluaran, b.getHarga());
    }
    
    public void hapusBarang(int idPengeluaranBarang, long harga){
        int yon = view.btnHapusYesOrNo(idPengeluaranBarang);
        if(yon == 0){
            db.hapusBarang(idPengeluaranBarang);
            db.updateHapusPengeluaran(idPengeluaranBulanan,idPengeluaran, harga);
            view.setVisible(false);
            new ControllerBarang(idPengeluaran,bulanTahun,hariTanggal,idPengeluaranBulanan);
        }
    }
    
    public void editBarang(int idPengeluaranBarang, String namaBarang, String keperluan, long hargaBaru, long hargaLama){
        int yon = view.btnEditYesOrNo(idPengeluaranBarang);
        if(yon == 0){
            db.editBarang(idPengeluaranBarang, namaBarang, keperluan, hargaBaru);
            db.updateEditPengeluaran(idPengeluaranBulanan, idPengeluaran, hargaBaru, hargaLama);
            view.setVisible(false);
            new ControllerBarang(idPengeluaran,bulanTahun,hariTanggal,idPengeluaranBulanan);
        }
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
        }else if(source.equals(view.getBtnHapus())){
            hapusBarang(kolomIdPengeluaranBarang, kolomHarga);
        }else if(source.equals(view.getBtnEdit())){
            long hargaBaru = Long.parseLong(view.getTxtHarga().getText());
            editBarang(kolomIdPengeluaranBarang, nama, keperluan, hargaBaru, kolomHarga);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent m){
        Object source = m.getSource();
        if(source.equals(view.getTable())){
            int row = view.getTable().getSelectedRow();
            kolomIdPengeluaranBarang = Integer.parseInt(view.getTable().getValueAt(row, 0).toString());
            kolomHarga = Long.parseLong(view.getTable().getValueAt(row, 3).toString());
            view.getTxtNamaBarang().setText(view.getTable().getValueAt(row, 1).toString());
            view.getTxtKeperluan().setText(view.getTable().getValueAt(row, 2).toString());
            view.getTxtHarga().setText(view.getTable().getValueAt(row, 3).toString());
        }
    }
}
