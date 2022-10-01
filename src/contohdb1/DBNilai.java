/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package contohdb1;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author acer
 */
public class DBNilai {
    private NilaiModel dt = new NilaiModel();
    
    public NilaiModel getNilaiModel(){
        return dt;
    }
    
    public void setNilaiModel(NilaiModel n){
        dt = n;
    }
    
    public ObservableList<NilaiModel> Load(){
        try{
            ObservableList<NilaiModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select n.Npm, Nama as NamaSiswa, n.KodeMk, NamaMK, Sks, Praktek, Tanggal, Nilai, Hadir "
                    + " from nilai n inner join siswa s on n.Npm = s.npm "
                    + " inner join matakuliah m on n.KodeMk = m.KodeMK");
            int i = 1;
            while(rs.next()){
                NilaiModel d = new NilaiModel();
                d.setNpm(rs.getString("Npm"));
                d.setKodeMk(rs.getString("KodeMk"));
                d.setTanggal(rs.getDate("Tanggal"));
                d.setNilai(rs.getInt("Nilai"));
                d.setHadir(rs.getInt("Hadir"));
                
                d.setNamaSiswa(rs.getString("NamaSiswa"));
                d.setNamaMK(rs.getString("NamaMK"));
                d.setSks(rs.getInt("Sks"));
                d.setPraktek(rs.getInt("Praktek"));
                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch(SQLException e){
            return null;
        }
    }
    
    public int validasi(String nomor, String matkul){
        int val = 0;
        try {
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from nilai "
                    + "where Npm = '"+nomor+"' and KodeMk = '"+matkul+"'");
            while(rs.next()) val = rs.getInt("jml");
            con.tutupKoneksi();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return val;
    }
    
    public boolean insert(){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement(
                    "insert into nilai(Npm, KodeMk, Tanggal, Nilai, Hadir) values (?,?,?,?,?)"
            );
            con.preparedStatement.setString(1, getNilaiModel().getNpm());
            con.preparedStatement.setString(2, getNilaiModel().getKodeMk());
            con.preparedStatement.setDate(3, getNilaiModel().getTanggal());
            con.preparedStatement.setFloat(4, getNilaiModel().getNilai());
            con.preparedStatement.setInt(5, getNilaiModel().getHadir());
            
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = false;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean delete(String nomor, String kode){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from nilai where Npm = ? and KodeMk = ?");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.setString(2, kode);
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e){
            e.printStackTrace();
        } finally{
            con.tutupKoneksi();
            return berhasil;
        }  
    }
    
    public boolean update(){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try {
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update nilai set tanggal=?, nilai=?, hadir=? where Npm = ? and KodeMk=?");
            con.preparedStatement.setDate(1, getNilaiModel().getTanggal());
            con.preparedStatement.setDouble(2, getNilaiModel().getNilai());
            con.preparedStatement.setInt(3, getNilaiModel().getHadir());
            con.preparedStatement.setString(4, getNilaiModel().getNpm());
            con.preparedStatement.setString(5, getNilaiModel().getKodeMk());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {
            e.printStackTrace();
            berhasil = true;
        } finally {
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
}
