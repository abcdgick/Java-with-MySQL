/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_InputSiswaController implements Initializable {

    @FXML
    private TextField txtNpm;
    @FXML
    private TextField txtNama;
    @FXML
    private TextField txtAlamat;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnBatal;
    @FXML
    private Button btnKeluar;

    boolean editData=false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanKlik(ActionEvent event) {
        SiswaModel s = new SiswaModel();
        s.setNpm(txtNpm.getText());
        s.setNama(txtNama.getText());
        s.setAlamat(txtAlamat.getText());
        
        FXMLDocumentController.dtsiswa.setSiswaModel(s);
        if(editData){
            if(FXMLDocumentController.dtsiswa.update()){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah",ButtonType.OK);
                a.showAndWait();
                txtNpm.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah",ButtonType.OK);
            }
        } else if(FXMLDocumentController.dtsiswa.validasi(s.getNpm())<=0){
            if(FXMLDocumentController.dtsiswa.insert()){
                Alert a = new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
                a.showAndWait();
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
                a.showAndWait();
            }
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtNpm.requestFocus();
        }
    }

    @FXML
    private void batalKlik(ActionEvent event) {
        txtNpm.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtNpm.requestFocus();
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnKeluar.getScene().getWindow().hide();
    }
    
    public void execute(SiswaModel d){
        if(!d.getNpm().isEmpty()){
            editData=true;
            txtNpm.setText(d.getNpm());
            txtNama.setText(d.getNama());
            txtAlamat.setText(d.getAlamat());
            txtNpm.setEditable(false);
            txtNama.requestFocus();
        }
    }
}
