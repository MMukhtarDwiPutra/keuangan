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
    
    public ControllerMakan(int idPengeluaran, String bulanTahun, String tanggal, int idPengeluaranBulanan){
        this.bulanTahun = bulanTahun;
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.idPengeluaran = idPengeluaran;
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getBtnBack())){
            new ControllerPengeluaran(idPengeluaranBulanan, bulanTahun);
            view.setVisible(false);
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
