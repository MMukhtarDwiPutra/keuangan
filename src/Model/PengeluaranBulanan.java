package Model;

import java.util.ArrayList;

public class PengeluaranBulanan {
    private int idPengeluaranBulanan;
    private String bulanTahun;
    private ArrayList<Pengeluaran> pengeluaranHarian = new ArrayList<>();
    private long jumlahPengeluaranBulanan;

    public PengeluaranBulanan(int idPengeluaranBulanan, String bulanTahun) {
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.bulanTahun = bulanTahun;
        this.jumlahPengeluaranBulanan = 0;
    }

    public PengeluaranBulanan(int idPengeluaranBulanan, String bulanTahun, long jumlahPengeluaranBulanan) {
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.bulanTahun = bulanTahun;
        this.jumlahPengeluaranBulanan = jumlahPengeluaranBulanan;
    }

    public long getJumlahPengeluaranBulanan() {
        return jumlahPengeluaranBulanan;
    }

    public void setJumlahPengeluaranBulanan(long jumlahPengeluaranBulanan) {
        this.jumlahPengeluaranBulanan = jumlahPengeluaranBulanan;
    }

    public int getIdPengeluaranBulanan() {
        return idPengeluaranBulanan;
    }

    public void setIdPengeluaranBulanan(int idPengeluaranBulanan) {
        this.idPengeluaranBulanan = idPengeluaranBulanan;
    }

    public String getBulanTahun() {
        return bulanTahun;
    }

    public void setBulanTahun(String bulanTahun) {
        this.bulanTahun = bulanTahun;
    }

    public ArrayList<Pengeluaran> getPengeluaranHarian() {
        return pengeluaranHarian;
    }

    public void setPengeluaranHarian(ArrayList<Pengeluaran> pengeluaranHarian) {
        this.pengeluaranHarian = pengeluaranHarian;
    }
}
