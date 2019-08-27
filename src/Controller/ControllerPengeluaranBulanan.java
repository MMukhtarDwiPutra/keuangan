package Controller;

import Model.Database;
import Model.PengeluaranBulanan;
import View.ViewPengeluaranBulanan;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ControllerPengeluaranBulanan extends MouseAdapter implements ActionListener{
    private Database db;
    private ViewPengeluaranBulanan view;
    private String kolomBulanTahun;
    private int kolomIdPengeluaranBulanan;
    
    public ControllerPengeluaranBulanan(){
        db = new Database();
        db.loadPengeluaranBulanan();
        view = new ViewPengeluaranBulanan();
        loadTable();
        view.addMouseAdapter(this);
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    public void loadTable(){
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID PengeluaranBulanan","Bulan dan Tahun"},0);
        ArrayList<PengeluaranBulanan> pengeluaranBulanan = db.getPengeluaranBulanan();
        for(PengeluaranBulanan p : pengeluaranBulanan){
            model.addRow(new Object[]{p.getIdPengeluaranBulanan(),p.getBulanTahun()});
        }
        view.setTableModel(model);
    }
    
    public void hapusPengeluaranBulanan(){
        int yon = view.btnHapusYesOrNo(kolomIdPengeluaranBulanan);
        if(yon == 0){
            db.hapusPengeluaranBulanan(kolomIdPengeluaranBulanan);
            view.setVisible(false);
            new ControllerPengeluaranBulanan();
        }
    }
    
    public void tambahPengeluaranBulanan(String bulanTahun){
        db.tambahPengeluaranBulanan(bulanTahun);
        view.setVisible(false);
        new ControllerPengeluaranBulanan();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getBtnTambahPengeluaran())){
            String bulanTahun = view.getCbBulan().getSelectedItem().toString();
            bulanTahun += " "+view.getCbTahun().getSelectedItem().toString();
            tambahPengeluaranBulanan(bulanTahun);
        }else if(source.equals(view.getBtnLihatPengeluaran())){
            new ControllerPengeluaran(kolomIdPengeluaranBulanan, kolomBulanTahun);
            view.setVisible(false);
        }else if(source.equals(view.getBtnHome())){
            new ControllerHome();
            view.setVisible(false);
        }else if(source.equals(view.getBtnHapus())){
            hapusPengeluaranBulanan();
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Object source = e.getSource();
        if(source.equals(view.getTable())){
            int i = view.getTableSelectedRow();
            this.kolomBulanTahun = view.getTable().getModel().getValueAt(i, 1).toString();
            this.kolomIdPengeluaranBulanan = Integer.parseInt(view.getTable().getModel().getValueAt(i, 0).toString());
        }
    }
}
