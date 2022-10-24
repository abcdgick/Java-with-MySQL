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
public class FXML_DisplayMatkulController implements Initializable {

    @FXML
    private TableView<MatkulModel> tbvmatkul;
    @FXML
    private Button btnAwal;
    @FXML
    private Button btnTambah;
    @FXML
    private Button btnSebelum;
    @FXML
    private Button btnHapus;
    @FXML
    private Button btnSesudah;
    @FXML
    private Button btnUbah;
    @FXML
    private Button btnAkhir;
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
        tbvmatkul.getSelectionModel().selectFirst();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void sebelumKlik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectAboveCell();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void sesudahKlik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectBelowCell();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void akhirKlik(ActionEvent event) {
        tbvmatkul.getSelectionModel().selectLast();
        tbvmatkul.requestFocus();
    }

    @FXML
    private void tambahKlik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
            Parent root = (Parent)loader.load();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
        } catch (IOException e) {
        }
        showData();
        awalKlik(event);
    }

    @FXML
    private void hapusKlik(ActionEvent event) {
        MatkulModel s = new MatkulModel();
        s=tbvmatkul.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dtmatkul.delete(s.getKodeMK())){
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
        MatkulModel s = new MatkulModel();
        s=tbvmatkul.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputMatkul.fxml"));
            Parent root = (Parent)loader.load();
            FXML_InputMatkulController isidt = (FXML_InputMatkulController)loader.getController();
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
        ObservableList<MatkulModel> data = FXMLDocumentController.dtmatkul.Load();
        if(data!=null){
            tbvmatkul.getColumns().clear();
            tbvmatkul.getItems().clear();
            TableColumn col = new TableColumn("Kode MK");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("KodeMK"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("Nama MK");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("NamaMK"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("SKS");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("Sks"));
            tbvmatkul.getColumns().addAll(col);
            col = new TableColumn("Praktikum");
            col.setCellValueFactory(new PropertyValueFactory<MatkulModel, String>("Praktek"));
            tbvmatkul.getColumns().addAll(col);
            
            tbvmatkul.setItems(data);
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbvmatkul.getScene().getWindow().hide();
        }
    }
    
}
