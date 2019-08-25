package Model;

import java.util.ArrayList;

public class Pengeluaran {
    private int idPengeluaran;
    private String tanggal;
    private String hari;
    private long jumlahPengeluaran;
    private ArrayList<Makan> makan;
    private ArrayList<Barang> barang;
    private int idPengeluaranBulanan;

    public Pengeluaran(String tanggal, String hari, int idPengeluaranBulanan){
        this.hari = hari;
//        this.idPengeluaran = idPengeluaran;
        this.tanggal = tanggal;
        this.jumlahPengeluaran = 0;
        this.idPengeluaranBulanan = idPengeluaranBulanan;
    }
    
    public Pengeluaran(int idPengeluaran, String tanggal, String hari, long jumlahPengeluaran, int idPengeluaranBulanan){
        this.hari = hari;
        this.idPengeluaran = idPengeluaran;
        this.tanggal = tanggal;
        this.jumlahPengeluaran = jumlahPengeluaran;
        this.idPengeluaranBulanan = idPengeluaranBulanan;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setJumlahPengeluaran(long jumlahPengeluaran, long harga){
        this.jumlahPengeluaran = this.jumlahPengeluaran + harga;
    }
    
    public void setJumlahPengeluaran(long jumlahPengeluaran){
        this.jumlahPengeluaran = jumlahPengeluaran;
    }

    public long getJumlahPengeluaran() {
        return jumlahPengeluaran;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public void setBarang(ArrayList<Barang> barang) {
        this.barang = barang;
    }

    public int getIdPengeluaranBulanan() {
        return idPengeluaranBulanan;
    }

    public void setIdPengeluaranBulanan(int idPengeluaranBulanan) {
        this.idPengeluaranBulanan = idPengeluaranBulanan;
    }
}
