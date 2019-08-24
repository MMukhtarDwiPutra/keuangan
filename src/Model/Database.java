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
    private ArrayList<Pengeluaran> pengeluaran = new ArrayList<>();
    
    String url = "jdbc:mysql://localhost/keuangan";
    String user = "root";
    String pw = "";
    
    public Database(){
    }
    
    public void loadPengeluaran(){
        connect();
        try {
            String query = "SELECT * FROM pengeluaran";
            rs = stmt.executeQuery(query);
            while(rs.next()){
                pengeluaran.add(new Pengeluaran(rs.getInt("id_pengeluaran"),rs.getString("tanggal"),rs.getLong("jumlah_pengeluaran"),rs.getInt("id_pengeluaran_bulanan")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public ArrayList<Pengeluaran> getPengeluaran() {
        return pengeluaran;
    }
    
    public ArrayList<PengeluaranBulanan> getPengeluaranBulanan(){
        return pengeluaranBulanan;
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
}
