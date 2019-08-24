package Controller;

import Model.Database;
import Model.Pengeluaran;
import View.ViewPengeluaran;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

public class ControllerPengeluaran extends MouseAdapter implements ActionListener{
    private Database db;
    private ViewPengeluaran view;
    private ArrayList<Pengeluaran> pengeluaran = new ArrayList<>();
    private String bulanTahun;
    private String hariTanggal;
    private int idPengeluaran;
    private int idPengeluaranBulanan;
    
    public ControllerPengeluaran(int idPengeluaranBulanan, String bulanTahun){
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.bulanTahun = bulanTahun;
        db = new Database();
        db.loadPengeluaran();
        view = new ViewPengeluaran(bulanTahun);
        loadTable(idPengeluaranBulanan);
        view.addMouseAdapter(this);
        view.addActionListener(this);
        view.setVisible(true);
    }
    
    public void loadTable(int idPengeluaranBulanan){
        pengeluaran = db.getPengeluaran();
        ArrayList<Pengeluaran> pengeluaranTemp = new ArrayList<>(); //Untuk sesuaikan dengan bulannya
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pengeluaran","Tanggal", "Hari", "Jumlah Pengeluaran"},0);
        for(Pengeluaran p : pengeluaran){
            if(idPengeluaranBulanan == p.getIdPengeluaranBulanan()){
                pengeluaranTemp.add(p);
            }
        }
        for(Pengeluaran pt : pengeluaranTemp){
            model.addRow(new Object[]{pt.getIdPengeluaran(),pt.getTanggal(),pt.getHari(), pt.getJumlahPengeluaran()});
        }
        view.setTableModel(model);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String tanggal = view.getTxtTanggal().getText();
        if(source.equals(view.getBtnTambahMakan())){
            new ControllerMakan(idPengeluaran, bulanTahun, hariTanggal, idPengeluaranBulanan);
            view.setVisible(false);
        }else if(source.equals(view.getBtnTambahBarang())){
            new ControllerBarang(tanggal);
        }else if(source.equals(view.getBtnTambahPengeluaran())){
            
        }else if(source.equals(view.getBtnBack())){
            new ControllerPengeluaranBulanan();
            view.setVisible(false);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        int i = view.getTableSelectedRow();
        if(source.equals(view.getTable())){
            this.hariTanggal = view.getTable().getValueAt(i, 2).toString();
            this.hariTanggal += ", "+view.getTable().getValueAt(i, 1).toString();
            this.idPengeluaran = Integer.parseInt(view.getTable().getValueAt(i, 0).toString());
        }
    }
}
