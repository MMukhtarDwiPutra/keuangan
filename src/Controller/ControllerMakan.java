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
    private long kolomHarga;
    private int kolomIdPengeluaranMakan;
    
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
        DefaultTableModel model = new DefaultTableModel(new String[]{"ID Pengeluaran Makan","Nama Makanan","Waktu","Harga"},0);
        ArrayList<Makan> makan = db.getMakan();
        for(Makan m : makan){
            model.addRow(new Object[]{m.getIdPengeluaranMakan(),m.getNama(),m.getWaktu(),m.getHarga()});
        }
        view.setModel(model);
    }
    
    public void tambahMakan(String namaMakanan, String waktu, long harga, int idPengeluaran){
        db.tambahMakan(new Makan(namaMakanan, waktu, harga, idPengeluaran));
        view.setVisible(false);
        new ControllerMakan(idPengeluaran,bulanTahun,tanggal,idPengeluaranBulanan);
        db.updateTambahPengeluaran(idPengeluaran,harga);
    }
    
    public void hapusMakan(int idPengeluaranMakan, long harga){
        db.hapusMakan(idPengeluaranMakan);
        db.updateHapusPengeluaran(idPengeluaran, harga);
        view.setVisible(false);
        new ControllerMakan(idPengeluaran,bulanTahun,tanggal,idPengeluaranBulanan);
    }
    
    public void editMakan(int idPengeluaranMakan, String namaMakanan, String waktuMakan, long hargaBaru, long hargaLama){
        db.editMakan(idPengeluaranMakan, namaMakanan, waktuMakan, hargaBaru);
        db.updateEditPengeluaran(idPengeluaran, hargaBaru, hargaLama);
        view.setVisible(false);
        new ControllerMakan(idPengeluaran,bulanTahun,tanggal,idPengeluaranBulanan);
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
        }else if(source.equals(view.getBtnHapus())){
            hapusMakan(kolomIdPengeluaranMakan, kolomHarga);
        }else if(source.equals(view.getBtnEdit())){
            long hargaBaru = Long.parseLong(view.getTxtHarga().getText().toString());
            editMakan(kolomIdPengeluaranMakan,namaMakanan,waktuMakan,hargaBaru,kolomHarga);
        }
    }
    
    @Override
    public void mousePressed(MouseEvent m){
        Object source = m.getSource();
        if(source.equals(view.getTable())){
            int i = view.getTableSelectedRow();
            view.getTxtHarga().setText(view.getTable().getValueAt(i, 3).toString());
            view.getTxtNamaMakanan().setText(view.getTable().getValueAt(i, 1).toString());
            if((view.getTable().getValueAt(i, 2).toString()).equals("Pagi")){
                view.getCbWaktuMakan().setSelectedIndex(0);
            }else if((view.getTable().getValueAt(i, 2).toString()).equals("Malam")){
                view.getCbWaktuMakan().setSelectedIndex(2);
            }else if((view.getTable().getValueAt(i, 2).toString()).equals("Siang")){
                view.getCbWaktuMakan().setSelectedIndex(1);
            }
            kolomIdPengeluaranMakan = Integer.parseInt(view.getTable().getValueAt(i, 0).toString());
            kolomHarga = Long.parseLong(view.getTable().getValueAt(i, 3).toString());
        }
    }
}
