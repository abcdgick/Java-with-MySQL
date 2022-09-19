/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_displaysiswaController implements Initializable {

    @FXML
    private TableView<SiswaModel> tbvsiswa;
    @FXML
    private Button btnAwal;
    @FXML
    private Button btnSebelum;
    @FXML
    private Button btnSesudah;
    @FXML
    private Button btnAkhir;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnUbah;
    @FXML
    private Button btnKeluar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       showData();
    }    

    @FXML
    private void awalKlik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectFirst();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sebelumKlik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectAboveCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void sesudahKlik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectBelowCell();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void akhirKlik(ActionEvent event) {
        tbvsiswa.getSelectionModel().selectLast();
        tbvsiswa.requestFocus();
    }

    @FXML
    private void tambahKlik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        awalKlik(event);
    }

    @FXML
    private void hapusKlik(ActionEvent event) {
        SiswaModel s = new SiswaModel();
        s=tbvsiswa.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dtsiswa.delete(s.getNpm())){
                Alert b = new Alert(Alert.AlertType.INFORMATION, "Data berhasil dihapus", ButtonType.OK);
                b.showAndWait();
            } else {
                Alert b = new Alert(Alert.AlertType.ERROR, "Data gagal dihapus", ButtonType.OK);
                b.showAndWait();
            }
            showData();
            awalKlik(event);
        }
    }

    @FXML
    private void ubahKlik(ActionEvent event) {
        SiswaModel s = new SiswaModel();
        s=tbvsiswa.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputSiswa.fxml"));
            Parent root = (Parent)loader.load();
            FXML_InputSiswaController isidt = (FXML_InputSiswaController)loader.getController();
            isidt.execute(s);
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        showData();
        awalKlik(event);
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnKeluar.getScene().getWindow().hide();
    }
    
    private void showData(){
         ObservableList<SiswaModel> data = FXMLDocumentController.dtsiswa.Load();
        if(data!=null){
            tbvsiswa.getColumns().clear();
            tbvsiswa.getItems().clear();
            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel,String>("Npm"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Nama");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel,String>("Nama"));
            tbvsiswa.getColumns().addAll(col);
            col = new TableColumn("Alamat");
            col.setCellValueFactory(new PropertyValueFactory<SiswaModel,String>("Alamat"));
            tbvsiswa.getColumns().addAll(col);
            
            tbvsiswa.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbvsiswa.getScene().getWindow().hide();
        }
    }
    
}
