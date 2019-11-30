# keuangan
Yang liat repository ini untuk referensi tubes PBO, di program ini masih ada yang kurang. Karena ini program kebanyakan ambil data dari database, jadinya kalau ada update atau delete cuman update di database (misal makan). Array untuk makan di model "database" itu belum ditambahin kodingan untuk delete atau update langsung ke modelnya.

Misalnya nih : 
public void hapusMakan(int idPengeluaranMakan){
        connect();
        String query = "DELETE FROM makan WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
        manipulate(query);
        disconnect();
    }
    
Itu harusnya :
public void hapusMakan(int idPengeluaranMakan){
    connect();
    String query = "DELETE FROM makan WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
    if (manipulate(query)) {
        for (Makan m : makan) {
            if (m.getIdPengeluaranMakan().equals(idPengeluaranMakan)) {
                makan.remove(m);
                break;
            }
        }
    }
    disconnect();
}

Contoh lagi :

Yang tadinya :
public void editMakan(int idPengeluaranMakan, String namaMakan, String waktuMakan, long harga){
      connect();
      String query = "UPDATE `makan` SET nama = '"+namaMakan+"', waktu = '"+waktuMakan+"', harga = "+harga+" WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
      manipulate(query);
      disconnect();
}
Harusnya :
public void editMakan(int idPengeluaranMakan, String namaMakan, String waktuMakan, long harga){
    connect();
    String query = "UPDATE `makan` SET nama = '"+namaMakan+"', waktu = '"+waktuMakan+"', harga = "+harga+" WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
    if (manipulate(query)) {
        for (Makan m : makan) {
            if (m.getIdPengeluaranMakan().equals(idPengeluaranMakan) {
                m.setNamaMakan(namaMakan);
                m.setHarga(harga);
                break;
            }
        }
    }
    disconnect();
}
    
    
Soalnya waktu tubes PBO dulu, kan nanya ke asdos tuh. Katanya harus diupdate langsung juga yang di programnya.
