/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package contohdb1;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author acer
 */
public class FXML_InputNilaiController implements Initializable {

    @FXML
    private TextField txtNPM;
    @FXML
    private TextField txtKodeMK;
    @FXML
    private TextField txtNilai;
    @FXML
    private Button btnBatal;
    @FXML
    private Button btnSimpan;
    @FXML
    private Button btnKeluar;
    @FXML
    private DatePicker dateTanggal;
    @FXML
    private Slider sldHadir;
    @FXML
    private Label lblHadir;
    
    private boolean editData = false;
    @FXML
    private Button btnPilih;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        sldHadir.valueProperty().addListener(new ChangeListener<Number>(){
            public void changed(ObservableValue<? extends Number> changed, Number oldVal, Number newVal){
                lblHadir.setText(String.valueOf(newVal.intValue()));
            }
        });
    }    

    @FXML
    private void batalKlik(ActionEvent event) {
        txtNPM.setText("");
        txtKodeMK.setText("");
        txtNilai.setText("");
        dateTanggal.getEditor().clear();
        sldHadir.setValue(0);
        txtNPM.requestFocus();
    }

    @FXML
    private void simpanKlik(ActionEvent event) {
        NilaiModel n = new NilaiModel();
        n.setNpm(txtNPM.getText());
        n.setKodeMk(txtKodeMK.getText());
        n.setTanggal(Date.valueOf(dateTanggal.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        n.setHadir((int)sldHadir.getValue());
        n.setNilai(Float.parseFloat(txtNilai.getText()));
        
        FXMLDocumentController.dtnilai.setNilaiModel(n);
        if(editData){
            if(FXMLDocumentController.dtnilai.update()){
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Data berhasil diubah",ButtonType.OK);
                a.showAndWait();
                txtNPM.setEditable(true);
                txtKodeMK.setEditable(true);
                batalKlik(event);
            } else {
                Alert a = new Alert(Alert.AlertType.ERROR, "Data gagal diubah",ButtonType.OK);
            }
        } else if(FXMLDocumentController.dtnilai.validasi(n.getNpm(),n.getKodeMk())<=0){
            if(FXMLDocumentController.dtnilai.insert()){
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
            txtNPM.requestFocus();
        }
    }

    @FXML
    private void keluarKlik(ActionEvent event) {
        btnKeluar.getScene().getWindow().hide();
    }
    
    public void execute(NilaiModel d){
        if(!d.getNpm().isEmpty() && !d.getKodeMk().isEmpty()){
            editData=true;
            txtNPM.setText(d.getNpm());
            txtKodeMK.setText(d.getKodeMk());
            dateTanggal.setValue(d.getTanggal().toLocalDate());
            sldHadir.setValue(d.getHadir());
            txtNilai.setText(String.valueOf(d.getNilai()));
            txtNPM.setEditable(false);
            txtKodeMK.setEditable(false);
            dateTanggal.requestFocus();
        }
    }

    @FXML
    private void pilihKlik(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML_PilihSiswa.fxml"));
            Parent root = (Parent)loader.load();
            FXML_PilihSiswaController isidt = (FXML_PilihSiswaController)loader.getController();
            Scene scene = new Scene(root);
            Stage stg = new Stage();
            stg.initModality(Modality.APPLICATION_MODAL);
            stg.setResizable(false);
            stg.setIconified(false);
            stg.setScene(scene);
            stg.showAndWait();
            if(isidt.getHasil()==1) txtNPM.setText(isidt.getNpmHasil());
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }
    
}
