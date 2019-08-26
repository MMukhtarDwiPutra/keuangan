package Model;

public class Makan {
    private int idPengeluaranMakan;
    private String nama;
    private String waktu;
    private long harga;
    private int idPengeluaran;

    public int getIdPengeluaran() {
        return idPengeluaran;
    }

    public void setIdPengeluaran(int idPengeluaran) {
        this.idPengeluaran = idPengeluaran;
    }

    public String getNama(){
        return nama;
    }

    public Makan(int idPengeluaranMakan, String nama, String waktu, long harga){
        this.idPengeluaranMakan = idPengeluaranMakan;
        this.nama = nama;
        this.waktu = waktu;
        this.harga = harga;
        this.idPengeluaran = idPengeluaran;
    }

    public int getIdPengeluaranMakan() {
        return idPengeluaranMakan;
    }

    public void setIdPengeluaranMakan(int idPengeluaranMakan) {
        this.idPengeluaranMakan = idPengeluaranMakan;
    }
    
    public Makan(String nama, String waktu, long harga, int idPengeluaran){
        this.nama = nama;
        this.waktu = waktu;
        this.harga = harga;
        this.idPengeluaran = idPengeluaran;
    }

    public Makan(String nama, String waktu, long harga){
        this.nama = nama;
        this.waktu = waktu;
        this.harga = harga;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }
    
    
}
