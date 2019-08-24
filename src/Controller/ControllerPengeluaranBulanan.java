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
    private String bulanTahun;
    private int idPengeluaranBulanan;
    
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
        if(source.equals(view.getBtnTambahPengeluaran())){
            
        }else if(source.equals(view.getBtnLihatPengeluaran())){
            new ControllerPengeluaran(idPengeluaranBulanan, bulanTahun);
            view.setVisible(false);
        }else if(source.equals(view.getBtnHome())){
            new ControllerHome();
            view.setVisible(false);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e){
        Object source = e.getSource();
        if(source.equals(view.getTable())){
            int i = view.getTableSelectedRow();
            this.bulanTahun = view.getTable().getModel().getValueAt(i, 1).toString();
            this.idPengeluaranBulanan = Integer.parseInt(view.getTable().getModel().getValueAt(i, 0).toString());
        }
    }
}
