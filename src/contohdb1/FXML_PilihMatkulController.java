/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_PilihMatkulController implements Initializable {

    @FXML
    private ComboBox<String> cmbField;
    @FXML
    private TextField txtIsi;
    @FXML
    private Button btnCari;
    @FXML
    private Button btnPilih;
    @FXML
    private Button btnBatal;
    @FXML
    private TableView<MatkulModel> tbvmatkul;
    @FXML
    private Button btnAwal;
    @FXML
    private Button btnSebelum;
    @FXML
    private Button btnSesudah;
    @FXML
    private Button btnAkhir;
    
    private int hasil = 0;
    private String kodeHasil="";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbField.setItems(FXCollections.observableArrayList("KodeMK", "NamaMK", "Praktek", "Sks"));
        cmbField.getSelectionModel().select(0);
        showData("KodeMK", "");
    }    

    @FXML
    private void cariKlik(ActionEvent event) {
        showData(cmbField.getSelectionModel().getSelectedItem(), txtIsi.getText());
    }

    @FXML
    private void pilihKlik(ActionEvent event) {
        hasil=1;
        int pil = tbvmatkul.getSelectionModel().getSelectedCells().get(0).getRow();
        kodeHasil=tbvmatkul.getItems().get(pil).getKodeMK();
        btnPilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalKlik(ActionEvent event) {
        hasil=0;
        btnBatal.getScene().getWindow().hide();
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
    
    public void showData(String f, String i){
        ObservableList<MatkulModel> data = FXMLDocumentController.dtmatkul.LookUp(f, i);
        if(data.isEmpty()){
            data = FXMLDocumentController.dtmatkul.Load();
            txtIsi.setText("");
        }
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
        } else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbvmatkul.getScene().getWindow().hide();
        }
        awalKlik(null);
        txtIsi.requestFocus();
    }
    
    public int getHasil(){
        return hasil;
    }
    
    public String getKode(){
        return kodeHasil;
    }
    
}
