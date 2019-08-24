package Controller;

import Model.Database;
import Model.Pengeluaran;
import View.ViewPengeluaran;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ControllerPengeluaran implements ActionListener{
    private Database db;
    private ViewPengeluaran view;
    private ArrayList<Pengeluaran> pengeluaran = new ArrayList<>();
    
    public ControllerPengeluaran(int idPengeluaranBulanan, String bulanTahun){
        db = new Database();
        db.loadPengeluaran();
        view = new ViewPengeluaran(bulanTahun);
        loadTable(idPengeluaranBulanan);
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    public void loadTable(int idPengeluaranBulanan){
        pengeluaran = db.getPengeluaran();
        ArrayList<Pengeluaran> pengeluaranTemp = new ArrayList<>(); //Untuk sesuaikan dengan bulannya
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pengeluaran","Tanggal", "Jumlah Pengeluaran", "ID Pengeluaran Bulanan"},0);
        for(Pengeluaran p : pengeluaran){
            if(idPengeluaranBulanan == p.getIdPengeluaranBulanan()){
                pengeluaranTemp.add(p);
            }
        }
        for(Pengeluaran pt : pengeluaranTemp){
            model.addRow(new Object[]{pt.getIdPengeluaran(),pt.getTanggal(),pt.getJumlahPengeluaran()});
        }
        view.setTableModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String tanggal = view.getTxtTanggal().getText();
        if(source.equals(view.getBtnTambahMakan())){
            
        }else if(source.equals(view.getBtnTambahBarang())){
            new ControllerBarang(tanggal);
        }else if(source.equals(view.getBtnTambahPengeluaran())){
            
        }else if(source.equals(view.getBtnBack())){
            new ControllerPengeluaranBulanan();
            view.setVisible(false);
        }
    }
}
