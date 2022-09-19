/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package contohdb1;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author acer
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem msiswa;
    @FXML
    private MenuItem mmatkul;
    @FXML
    private MenuItem tpenilaian;
    @FXML
    private MenuItem dsiswa;
    @FXML
    private MenuItem dmatkul;
    @FXML
    private MenuItem dpenilaian;
    @FXML
    private MenuItem keluar;
    
    public static DBSiswa dtsiswa = new DBSiswa();
    public static DBMatkul dtmatkul = new DBMatkul();
    public static DBNilai dtnilai = new DBNilai();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void msiswaclick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e){
            
        }
        
    }

    @FXML
    private void mmatkulclick(ActionEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e){
            
        }
    }

    @FXML
    private void tpenilaianclick(ActionEvent event) {
         try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.show();
        } catch (IOException e){
            
        }
    }

    @FXML
    private void dsiswaclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_displaysiswa.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();
    }

    @FXML
    private void dmatkulclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayMatkul.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();
    }

    @FXML
    private void dpenilaianclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_DisplayNilai.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stg = new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.show();
    }

    @FXML
    private void keluarclick(ActionEvent event) {
        System.exit(0);
    }
    
}
