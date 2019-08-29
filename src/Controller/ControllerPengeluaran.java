package Controller;

import Model.Database;
import Model.Pengeluaran;
import Model.PengeluaranBulanan;
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
    private String hari;
    private String tanggal;
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
    
    public void tambahPengeluaran(Pengeluaran p){
        db.tambahPengeluaran(p);
        view.setVisible(false);
        new ControllerPengeluaran(idPengeluaranBulanan, bulanTahun);
    }
    
    public void hapusPengeluaran(int idPengeluaran){
        int yon = view.btnHapusYesOrNo(idPengeluaran);
        if(yon == 0){
            Pengeluaran temp = null;
            ArrayList<Pengeluaran> pengeluaran = db.getPengeluaran();
            for(Pengeluaran p : pengeluaran){
                if(idPengeluaran == p.getIdPengeluaran()){
                    temp = p;
                    break;
                }
            }
            db.updateHapusPengeluaran(idPengeluaranBulanan,temp.getJumlahPengeluaran());
            db.hapusPengeluaran(idPengeluaran);
            view.setVisible(false);
            new ControllerPengeluaran(idPengeluaranBulanan, bulanTahun);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String tambahTanggal = view.getTxtTanggal().getText();
        String tambahHari = view.getCbHari().getSelectedItem().toString();
        if(source.equals(view.getBtnMakan())){
            String hariTanggal = hari+", "+tanggal;
            new ControllerMakan(idPengeluaran, bulanTahun, hariTanggal, idPengeluaranBulanan);
            view.setVisible(false);
        }else if(source.equals(view.getBtnBarang())){
            String hariTanggal = hari+", "+tanggal;
            new ControllerBarang(idPengeluaran,bulanTahun,hariTanggal,idPengeluaranBulanan);
            view.setVisible(false);
        }else if(source.equals(view.getBtnTambahPengeluaran())){
            tambahPengeluaran(new Pengeluaran(tambahTanggal,tambahHari,idPengeluaranBulanan));
        }else if(source.equals(view.getBtnBack())){
            new ControllerPengeluaranBulanan();
            view.setVisible(false);
        }else if(source.equals(view.getBtnHapus())){
            hapusPengeluaran(idPengeluaran);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        int i = view.getTableSelectedRow();
        if(source.equals(view.getTable())){
            this.hari = view.getTable().getValueAt(i, 2).toString();
            this.tanggal = view.getTable().getValueAt(i, 1).toString();
            this.idPengeluaran = Integer.parseInt(view.getTable().getValueAt(i, 0).toString());
        }
    }
}
