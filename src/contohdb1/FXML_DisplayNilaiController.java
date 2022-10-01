/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_DisplayNilaiController implements Initializable {

    @FXML
    private TableView<NilaiModel> tbvnilai;
    @FXML
    private TextField txtTotal;
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
        tbvnilai.getSelectionModel().selectFirst();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sebelumKlik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectAboveCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void sesudahKlik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectBelowCell();
        tbvnilai.requestFocus();
    }

    @FXML
    private void akhirKlik(ActionEvent event) {
        tbvnilai.getSelectionModel().selectLast();
        tbvnilai.requestFocus();
    }

    @FXML
    private void tambahKlik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
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
        NilaiModel s = new NilaiModel();
        s=tbvnilai.getSelectionModel().getSelectedItem();
        Alert a = new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?", ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
            if(FXMLDocumentController.dtnilai.delete(s.getNpm(), s.getKodeMk())){
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
        NilaiModel s = new NilaiModel();
        s=tbvnilai.getSelectionModel().getSelectedItem();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_InputNilai.fxml"));
            Parent root = (Parent)loader.load();
            FXML_InputNilaiController isidt = (FXML_InputNilaiController)loader.getController();
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
        ObservableList<NilaiModel> data = FXMLDocumentController.dtnilai.Load();
        if(data!=null){
            tbvnilai.getColumns().clear();
            tbvnilai.getItems().clear();
            TableColumn col = new TableColumn("NPM");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("Npm"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Nama Siswa");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("NamaSiswa"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Kode MK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("KodeMk"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Nama MK");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("NamaMK"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Tanggal");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Date>("Tanggal"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Sks");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("Sks"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Praktek");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("Praktek"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Nilai");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Float>("Nilai"));
            tbvnilai.getColumns().addAll(col);
            col = new TableColumn("Hadir");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, Integer>("Hadir"));
            tbvnilai.getColumns().addAll(col);
            
            col = new TableColumn("NA");
            col.setCellValueFactory(new PropertyValueFactory<NilaiModel, String>("na"));
            tbvnilai.getColumns().addAll(col);
            
            tbvnilai.setItems(data);
            
            int total = 0;
            for(int i = 0; i<tbvnilai.getItems().size();i++){
                NilaiModel n = tbvnilai.getItems().get(i);
                total+=n.getHadir();
            }
            txtTotal.setText(String.valueOf(total));
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbvnilai.getScene().getWindow().hide();
        }
    }
}
