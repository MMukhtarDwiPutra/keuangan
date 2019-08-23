package Model;

public class Barang {
    private String nama;
    private String keperluan;
    private long harga;

    public Barang(String nama, String keperluan, long harga) {
        this.nama = nama;
        this.keperluan = keperluan;
        this.harga = harga;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }

    public long getHarga() {
        return harga;
    }

    public void setHarga(long harga) {
        this.harga = harga;
    }
    
    
}
