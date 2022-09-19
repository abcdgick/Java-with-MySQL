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
public class FXML_InputMatkulController implements Initializable {

    @FXML
    private TextField txtKodeMK;
    @FXML
    private TextField txtNamaMK;
    @FXML
    private TextField txtSKS;
    @FXML
    private TextField txtPraktek;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnBatal;
    @FXML
    private Button btnKeluar;

    boolean editData = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void simpanKlik(ActionEvent event) {
        MatkulModel s = new MatkulModel();
        s.setKodeMK(txtKodeMK.getText());
        s.setNamaMK(txtNamaMK.getText());
        s.setPraktek(Integer.parseInt(txtPraktek.getText()));
        s.setSks(Integer.parseInt(txtSKS.getText()));
        
        FXMLDocumentController.dtmatkul.setMatkulModel(s);
        if(editData){
            if(FXMLDocumentController.dtmatkul.update()){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah",ButtonType.OK);
                a.showAndWait();
                txtKodeMK.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah",ButtonType.OK);
            }
        } else if(FXMLDocumentController.dtmatkul.validasi(s.getKodeMK())<=0){
            if(FXMLDocumentController.dtmatkul.insert()){
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
            txtKodeMK.requestFocus();
        }
    }

    @FXML
    private void batalKlik(ActionEvent event) {
        txtKodeMK.setText("");
        txtNamaMK.setText("");
        txtSKS.setText("");
        txtPraktek.setText("");
        
        txtKodeMK.requestFocus();
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnKeluar.getScene().getWindow().hide();
    }
    
    public void execute(MatkulModel d){
        if(!d.getKodeMK().isEmpty()){
            editData=true;
            txtKodeMK.setText(d.getKodeMK());
            txtNamaMK.setText(d.getNamaMK());
            txtSKS.setText(String.valueOf(d.getSks()));
            txtPraktek.setText(String.valueOf(d.getPraktek()));
            txtKodeMK.setEditable(false);
            txtNamaMK.requestFocus();
        }
    }
}
