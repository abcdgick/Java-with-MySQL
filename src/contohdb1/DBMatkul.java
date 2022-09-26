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
public class DBMatkul {
    private MatkulModel dt = new MatkulModel();
    
    public MatkulModel getMatkulModel(){
        return(dt);
    }
    
    public void setMatkulModel(MatkulModel m){
        dt = m;
    }
    
    public ObservableList<MatkulModel> Load(){
        try{
            ObservableList<MatkulModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select KodeMK, NamaMK, Sks, Praktek from matakuliah");
            int i = 1;
            while(rs.next()){
                MatkulModel d = new MatkulModel();
                d.setKodeMK(rs.getString("KodeMK"));
                d.setNamaMK(rs.getString("NamaMK"));
                d.setSks(rs.getInt("Sks"));
                d.setPraktek(rs.getInt("Praktek"));

                tableData.add(d);
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    
    public int validasi(String nomor){
        int val = 0;
        try{
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("select count(*) as jml from matakuliah where KodeMK = '" + nomor + "'");
            while(rs.next()) val=rs.getInt("jml");
            con.tutupKoneksi();
        } catch(SQLException e){
            e.printStackTrace();
        }
        return val;
    }
    
    public boolean insert(){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into matakuliah(KodeMK, NamaMK, Sks, Praktek) values (?,?,?,?)");
            con.preparedStatement.setString(1, getMatkulModel().getKodeMK());
            con.preparedStatement.setString(2, getMatkulModel().getNamaMK());
            con.preparedStatement.setInt(3, getMatkulModel().getSks());
            con.preparedStatement.setInt(4, getMatkulModel().getPraktek());
            con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch(Exception e){
            e.printStackTrace();
            berhasil = false;
        } finally{
            con.tutupKoneksi();
            return berhasil;
        }
    }
    
    public boolean delete(String nomor){
        boolean berhasil = false;
        Koneksi con = new Koneksi();
        try{
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from matakuliah where KodeMK = ?");
            con.preparedStatement.setString(1, nomor);
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update matakuliah set NamaMK=?, Sks=?, Praktek=? where KodeMK=?;");
            con.preparedStatement.setString(1, getMatkulModel().getNamaMK());
            con.preparedStatement.setInt(2, getMatkulModel().getSks());
            con.preparedStatement.setInt(3, getMatkulModel().getPraktek());
            con.preparedStatement.setString(4, getMatkulModel().getKodeMK());
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
    
    public ObservableList<MatkulModel> LookUp(String fld, String dt){
        try {
            ObservableList<MatkulModel> tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi();
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select KodeMK, NamaMK, Sks, Praktek from matakuliah where "+fld+" like '%"+dt+"%'");
            int i = 1;
            while(rs.next()){
                MatkulModel d = new MatkulModel();
                d.setKodeMK(rs.getString("KodeMK"));
                d.setNamaMK(rs.getString("NamaMK"));
                d.setPraktek(rs.getInt("Praktek"));
                d.setSks(rs.getInt("Sks"));
                i++;
            }
            con.tutupKoneksi();
            return tableData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
