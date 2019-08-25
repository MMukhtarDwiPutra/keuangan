package Controller;

import Model.Database;
import Model.Makan;
import View.ViewMakan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ControllerMakan extends MouseAdapter implements ActionListener{
    private Database db;
    private ViewMakan view;
    private int idPengeluaran;
    private int idPengeluaranBulanan;
    private String bulanTahun;
    private String tanggal;
    
    public ControllerMakan(int idPengeluaran, String bulanTahun, String tanggal, int idPengeluaranBulanan){
        this.bulanTahun = bulanTahun;
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.idPengeluaran = idPengeluaran;
        this.tanggal = tanggal;
        db = new Database();
        view = new ViewMakan(bulanTahun, tanggal);
        view.addMouseAdapter(this);
        view.addActionListener(this);
        view.setVisible(true);
        loadTable();
    }
    
    public void loadTable(){
        db.loadMakan(idPengeluaran);
        DefaultTableModel model = new DefaultTableModel(new String[]{"Nama Makanan","Waktu","Harga"},0);
        ArrayList<Makan> makan = db.getMakan();
        for(Makan m : makan){
            model.addRow(new Object[]{m.getNama(),m.getWaktu(),m.getHarga()});
        }
        view.setModel(model);
    }
    
    public void tambahMakan(String namaMakanan, String waktu, long harga, int idPengeluaran){
        db.tambahMakan(new Makan(namaMakanan, waktu, harga, idPengeluaran));
        view.setVisible(false);
        new ControllerMakan(idPengeluaran,bulanTahun,tanggal,idPengeluaranBulanan);
        db.updateTambahPengeluaran(idPengeluaran,harga);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String namaMakanan = view.getTxtNamaMakanan().getText();
        String waktuMakan = view.getCbWaktuMakan().getSelectedItem().toString();
        long harga;
        if(source.equals(view.getBtnBack())){
            new ControllerPengeluaran(idPengeluaranBulanan, bulanTahun);
            view.setVisible(false);
        }else if(source.equals(view.getBtnTambah())){
            harga = Long.parseLong(view.getTxtHarga().getText().toString());
            tambahMakan(namaMakanan,waktuMakan,harga,idPengeluaran);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent m){
        Object source = m.getSource();
        if(source.equals(view.getTable())){
            int i = view.getTableSelectedRow();
            
        }
    }
}
