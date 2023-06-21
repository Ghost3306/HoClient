package com.example.client;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.Scanner;

public class PatientData {
    public Label fname;
    public Label lname;
    public Label dob;
    public Label age;
    public Label email;
    public Label phone;
    public Label address;
    public Label height;
    public Label weight;
    public Label work;
    public Label health;
    public Label physical;
    public ImageView back;
    public TextField medicine;
    public TextField times;
    public TextField beafter;
    public TextField breakfast;
    public Button Add;
    public Pane dataPane;
    public Button edit;
    public Label mname;

    Sender sender = new Sender();
    JSONObject jo = new JSONObject();
    public void setLabelValue(String pfname,String pmname,String plname,String pdob,String page,String pemail,String pphone,String paddress,String pheight,String pweight,String pwork,String phealth,String pphysical)  {


           fname.setText(pfname);
           mname.setText(pmname);
           lname.setText(plname);
           dob.setText(pdob);
           age.setText(page);
           email.setText(pemail);
           phone.setText(pphone);
           address.setText(paddress);
           work.setText(pwork);
           height.setText(pheight);
           weight.setText(pweight);
            health.setText(phealth);
            physical.setText(pphysical);

    }
    public void back() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("DoctorHome.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Doctor Homepage");
        stage.setScene(scene);
        stage.show();
    }



    public void setMedicineTable() throws IOException, ClassNotFoundException {
        String medicinenm = medicine.getText();
        String time = times.getText();
        String before = beafter.getText();
        String lunch = breakfast.getText();

        JSONObject jsonData = new JSONObject();
        jsonData.put("purpose","addmedicine");
        jsonData.put("phone",phone.getText());
        jsonData.put("name",fname.getText()+lname.getText());
        jsonData.put("medicine",medicinenm);
        jsonData.put("time",time);
        jsonData.put("before",before);
        jsonData.put("lunch",lunch);
        JSONObject jsonReturn = new JSONObject();
        jsonReturn = sender.sendJSON(jsonData);
        if(jsonReturn.get("result").toString().equals("true")){
            medicine.setText("");
            times.setText("");
            beafter.setText("");
            breakfast.setText("");
        }

    }

    public void editData() throws IOException {
        
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(EditPatientData.class.getResource("EditPatientData.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        EditPatientData editPatientData = fxmlLoader.getController();
        editPatientData.setValue(fname.getText(),mname.getText(), lname.getText(),dob.getText(),age.getText(), email.getText()
                , phone.getText(), address.getText(), height.getText(), weight.getText(), work.getText(),
                health.getText(), physical.getText());
        stage.setTitle("Edit Data");
        stage.setScene(scene);
        stage.show();

    }


}
