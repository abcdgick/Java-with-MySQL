/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_PilihSiswaController implements Initializable {

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
    private Button btnAwal;
    @FXML
    private Button btnSebelum;
    @FXML
    private Button btnSesudah;
    @FXML
    private Button btnAkhir;
    
    private int hasil = 0;
    private String npmHasil = "";
    @FXML
    private TableView<SiswaModel> tbvsiswa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cmbField.setItems(FXCollections.observableArrayList("Npm", "Nama", "Alamat"));
        cmbField.getSelectionModel().select(0);
        showData("Npm", "");
    }    

    @FXML
    private void cariKlik(ActionEvent event) {
        showData(cmbField.getSelectionModel().getSelectedItem(), txtIsi.getText());
    }

    @FXML
    private void pilihKlik(ActionEvent event) {
        hasil = 1;
        int pil = tbvsiswa.getSelectionModel().getSelectedCells().get(0).getRow();
        npmHasil = tbvsiswa.getItems().get(pil).getNpm();
        btnPilih.getScene().getWindow().hide();
    }

    @FXML
    private void batalKlik(ActionEvent event) {
        hasil = 0;
        btnBatal.getScene().getWindow().hide();
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
    
    public void showData(String f, String i){
        ObservableList<SiswaModel> data = FXMLDocumentController.dtsiswa.LookUp(f,i);
        if(data.isEmpty()){
            data = FXMLDocumentController.dtsiswa.Load();
            txtIsi.setText("");
        }
        if(data!=null) {
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
        } else{
            Alert a = new Alert(Alert.AlertType.ERROR, "Data Kosong", ButtonType.OK);
            a.showAndWait();
            tbvsiswa.getScene().getWindow().hide();
        }
        awalKlik(null);
        txtIsi.requestFocus();
    }
    
    public int getHasil(){
        return hasil;
    }
    
    public String getNpmHasil(){
        return npmHasil;
    }
}
