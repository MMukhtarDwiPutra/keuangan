package Model;

public class Makan {
    private String nama;
    private String waktu;
    private long harga;
    private int idPengeluaran;

    public String getNama(){
        return nama;
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
