package Model;

import java.util.ArrayList;

public class PengeluaranBulanan {
    private int idPengeluaranBulanan;
    private String bulanTahun;
    private ArrayList<Pengeluaran> pengeluaranHarian = new ArrayList<>();

    public PengeluaranBulanan(int idPengeluaranBulanan, String bulanTahun) {
        this.idPengeluaranBulanan = idPengeluaranBulanan;
        this.bulanTahun = bulanTahun;
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
