package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    
    
    private ArrayList<PengeluaranBulanan> pengeluaranBulanan = new ArrayList<>();
    private ArrayList<PengeluaranBulanan> riwayatPengeluaran = new ArrayList<>();
    private ArrayList<Pengeluaran> pengeluaran = new ArrayList<>();
    private ArrayList<Makan> makan = new ArrayList<>();
    private ArrayList<Barang> barang = new ArrayList<>();

    public ArrayList<Makan> getMakan() {
        return makan;
    }
    
    String url = "jdbc:mysql://localhost/keuangan";
    String user = "root";
    String pw = "";
    
    public Database(){
    }
    
    public void tambahMakan(Makan m){
        connect();
        String query = "INSERT INTO `makan` (`nama`,`waktu`,`harga`,`id_pengeluaran`) VALUES ";
        query += "('"+m.getNama()+"',";
        query += "'"+m.getWaktu()+"',";
        query += m.getHarga()+",";
        query += m.getIdPengeluaran()+")";
        if(manipulate(query)){
            loadMakan(m.getIdPengeluaran());
        }
        disconnect();
    }
    
    public void hapusMakan(int idPengeluaranMakan){
        connect();
        String query = "DELETE FROM makan WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
        manipulate(query);
        disconnect();
    }
    
    public void editMakan(int idPengeluaranMakan, String namaMakan, String waktuMakan, long harga){
        connect();
        String query = "UPDATE `makan` SET nama = '"+namaMakan+"', waktu = '"+waktuMakan+"', harga = "+harga+" WHERE id_pengeluaran_makan = "+idPengeluaranMakan;
        manipulate(query);
        disconnect();
    }
    
    public void tambahBarang(Barang b, int idPengeluaran){
        connect();
        String query = "INSERT INTO `barang` (`nama`, `keperluan`, `harga`, `id_pengeluaran`) VALUES (";
        query += "'"+b.getNama()+"',";
        query += "'"+b.getKeperluan()+"',";
        query += b.getHarga()+",";
        query += "'"+idPengeluaran+"')";
        manipulate(query);
        disconnect();
    }
    
    public void hapusBarang(int idPengeluaranBarang){
        connect();
        String query = "DELETE FROM barang WHERE id_pengeluaran_barang = "+idPengeluaranBarang;
        manipulate(query);
        disconnect();
    }
    
    public void editBarang(int idPengeluaranBarang, String namaBarang, String keperluan, long hargaBaru){
        connect();
        String query = "UPDATE `barang` SET nama = '"+namaBarang+"', keperluan = '"+keperluan+"', harga = "+hargaBaru+" WHERE id_pengeluaran_barang = "+idPengeluaranBarang;
        manipulate(query);
        disconnect();
    }
    
    public void tambahPengeluaran(Pengeluaran p){
        connect();
        String query = "INSERT INTO `pengeluaran`(`tanggal`, `hari`, `jumlah_pengeluaran`, `id_pengeluaran_bulanan`) VALUES ";
        query += "('"+p.getTanggal()+"',";
        query += "'"+p.getHari()+"',";
        query += p.getJumlahPengeluaran()+",";
        query += p.getIdPengeluaranBulanan()+")";
        if(manipulate(query)){
            loadPengeluaran();
        }
        disconnect();
    }
    
    public void hapusPengeluaran(int idPengeluaran){
        connect();
        String query = "DELETE FROM pengeluaran WHERE id_pengeluaran = "+idPengeluaran;
        manipulate(query);
        disconnect();
    }
    
    public void tambahPengeluaranBulanan(String bulanTahun){
        connect();
        String query = "INSERT INTO pengeluaranbulanan (`bulan_tahun`,`jumlah_pengeluaran_bulanan`) VALUES ('"+bulanTahun+"',0)";
        manipulate(query);
        disconnect();
    }
    
    public void hapusPengeluaranBulanan(int idPengeluaranBulanan){
        connect();
        String query = "DELETE FROM pengeluaranbulanan WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;
        manipulate(query);
        disconnect();
    }
    
    public void loadMakan(int idPengeluaran){
        connect();
        try {
            String query = "SELECT * FROM makan WHERE id_pengeluaran = "+idPengeluaran;
            rs = stmt.executeQuery(query);
            while(rs.next()){
                makan.add(new Makan(rs.getInt("id_pengeluaran_makan"),rs.getString("nama"),rs.getString("waktu"),rs.getLong("harga")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public void loadBarang(int idPengeluaran){
        connect();
        try {
            String query = "SELECT * FROM barang WHERE id_pengeluaran = "+idPengeluaran;
            rs = stmt.executeQuery(query);
            while(rs.next()){
                barang.add(new Barang(rs.getInt("id_pengeluaran_barang"),rs.getString("nama"),rs.getString("keperluan"),rs.getLong("harga")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public void loadPengeluaran(){
        connect();
        try {
            String query = "SELECT * FROM pengeluaran";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                pengeluaran.add(new Pengeluaran(rs.getInt("id_pengeluaran"),rs.getString("tanggal"), rs.getString("hari"), rs.getLong("jumlah_pengeluaran"),rs.getInt("id_pengeluaran_bulanan")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public void loadPengeluaranBulanan(){
        connect();
        try {
            String query = "SELECT * FROM pengeluaranbulanan";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                pengeluaranBulanan.add(new PengeluaranBulanan(rs.getInt("id_pengeluaran_bulanan"),rs.getString("bulan_tahun")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }

    public ArrayList<PengeluaranBulanan> getRiwayatPengeluaran() {
        return riwayatPengeluaran;
    }
    
    public void loadRiwayatPengeluaran(){
        connect();
        try {
            String query = "SELECT * FROM pengeluaranbulanan";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                riwayatPengeluaran.add(new PengeluaranBulanan(rs.getInt("id_pengeluaran_bulanan"),rs.getString("bulan_tahun"),rs.getLong("jumlah_pengeluaran_bulanan")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        disconnect();
    }
    
    public ArrayList<Pengeluaran> getPengeluaran() {
        return pengeluaran;
    }
    
    public ArrayList<PengeluaranBulanan> getPengeluaranBulanan(){
        return pengeluaranBulanan;
    }
    
    public ArrayList<Barang> getBarang() {
        return barang;
    }
    
    public void updateTambahPengeluaran(int idPengeluaranBulanan, int idPengeluaran, long harga) {
        connect();
        String query = "UPDATE pengeluaran SET jumlah_pengeluaran = jumlah_pengeluaran + "+harga+" WHERE id_pengeluaran = "+idPengeluaran;
        manipulate(query);
        query = "UPDATE pengeluaranbulanan SET jumlah_pengeluaran_bulanan = jumlah_pengeluaran_bulanan + "+harga+" WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;
        manipulate(query);
        disconnect();
    }
    
    public void updateHapusPengeluaran(int idPengeluaranBulanan, int idPengeluaran, long harga){
        connect();
        String query = "UPDATE pengeluaran SET jumlah_pengeluaran = jumlah_pengeluaran - "+harga+" WHERE id_pengeluaran = "+idPengeluaran;
        manipulate(query);
        query = "UPDATE pengeluaranbulanan SET jumlah_pengeluaran_bulanan = jumlah_pengeluaran_bulanan - "+harga+" WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;
        manipulate(query);
        disconnect();
    }
    
    public void updateHapusPengeluaran(int idPengeluaranBulanan, long pengeluaran){
        connect();
        String query = "UPDATE pengeluaranbulanan set jumlah_pengeluaran_bulanan = jumlah_pengeluaran_bulanan - "+pengeluaran+" WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;
        manipulate(query);
        disconnect();
    }
    
    public void updateEditPengeluaran(int idPengeluaranBulanan, int idPengeluaran, long hargaBaru, long hargaLama){
        connect();
        String query = "UPDATE pengeluaran SET jumlah_pengeluaran = jumlah_pengeluaran - "+hargaLama+" WHERE id_pengeluaran = "+idPengeluaran;        
        manipulate(query);
        query = "UPDATE pengeluaran SET jumlah_pengeluaran = jumlah_pengeluaran + "+hargaBaru+" WHERE id_pengeluaran = "+idPengeluaran;
        manipulate(query);
        
        query = "UPDATE pengeluaranbulanan SET jumlah_pengeluaran_bulanan = jumlah_pengeluaran_bulanan - "+hargaLama+" WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;        
        manipulate(query);
        query = "UPDATE pengeluaranbulanan SET jumlah_pengeluaran_bulanan = jumlah_pengeluaran_bulanan + "+hargaBaru+" WHERE id_pengeluaran_bulanan = "+idPengeluaranBulanan;
        manipulate(query);
        disconnect();
    }
    
    public void connect(){
        try {
            conn = DriverManager.getConnection(url, user,pw);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void disconnect(){
        try {
            conn.close();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean manipulate(String query){
        boolean cek = false;
        try {
            int row = stmt.executeUpdate(query);
            if(row > 0) cek = true;
        } catch (SQLException ex){
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return cek;
    }
}
