package com.example.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;

public class EditPatientData {
    public Label lname;
    public Label fname;
    public Label dob;
    public Label age;
    public TextField email;
    public Label phone;
    public TextArea address;
    public TextField height;
    public TextField weight;
    public TextField work;
    public TextArea physical;
    public TextArea health;
    public Button back;
    public Button submitData;
    public Label mname;

    Sender sender = new Sender();
    public JSONObject fillJSON(){
        JSONObject jsonData = new JSONObject();
        jsonData.put("purpose","updatePatient");
        jsonData.put("fname",fname.getText());
        jsonData.put("mname",mname.getText());
        jsonData.put("lname",lname.getText());
        jsonData.put("age",age.getText());
        jsonData.put("dob",dob.getText());
        jsonData.put("email",email.getText());
        jsonData.put("phone",phone.getText());
        jsonData.put("address",address.getText());
        jsonData.put("height",height.getText());
        jsonData.put("weight",weight.getText());
        jsonData.put("health",health.getText());
        jsonData.put("physical",physical.getText());
        jsonData.put("work",work.getText());
        return jsonData;
    }
    public void back() throws IOException {
        JSONObject jsonData = new JSONObject();
        jsonData = fillJSON();
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(EditPatientData.class.getResource("PatientData.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        System.out.println(jsonData);
        PatientData patientData = fxmlLoader.getController();
        patientData.setLabelValue(
                jsonData.get("fname").toString(),jsonData.get("mname").toString(),jsonData.get("lname").toString(),jsonData.get("dob").toString(),
                jsonData.get("age").toString(),jsonData.get("email").toString(),jsonData.get("phone").toString(),
                jsonData.get("address").toString(),jsonData.get("height").toString(),
                jsonData.get("weight").toString(),jsonData.get("work").toString(),jsonData.get("health").toString(),
                jsonData.get("physical").toString()
        );
        stage.setTitle("Patient Data");
        stage.setScene(scene);
        stage.show();
    }
    public void setValue(String pfname,String pmname,String plname,String pdob,String page,String pemail,String pphone,String paddress,String pheight,String pweight,String pwork,
                         String phealth,String pphysical){
        fname.setText(pfname);
        mname.setText(pmname);
        lname.setText(plname);
        dob.setText(pdob);
        age.setText(page);
        email.setText(pemail);
        phone.setText(pphone);
        address.setText(paddress);
        height.setText(pheight);
        weight.setText(pweight);
        work.setText(pwork);
        health.setText(phealth);
        physical.setText(pphysical);
    }

    public void submit() throws ClassNotFoundException, IOException {
        JSONObject jsonData = new JSONObject();
        jsonData = fillJSON();
        jsonData.put("purpose","updatePatient");
        JSONObject jsonReturn = new JSONObject();
        jsonReturn = sender.sendJSON(jsonData);
        if(jsonReturn.get("result").equals("true")){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Data Updated Successfully");
            alert.show();
            Stage stage = (Stage) submitData.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(EditPatientData.class.getResource("PatientData.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            System.out.println(jsonData);
            PatientData patientData = fxmlLoader.getController();
            patientData.setLabelValue(
                    jsonData.get("fname").toString(),jsonData.get("mname").toString(),jsonData.get("lname").toString(),jsonData.get("dob").toString(),
                    jsonData.get("age").toString(),jsonData.get("email").toString(),jsonData.get("phone").toString(),
                    jsonData.get("address").toString(),jsonData.get("height").toString(),
                    jsonData.get("weight").toString(),jsonData.get("work").toString(),jsonData.get("health").toString(),
                    jsonData.get("physical").toString()
            );
            stage.setTitle("Patient Data");
            stage.setScene(scene);
            stage.show();






        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText("Data Update Failed");
            alert.show();
        }


    }
}
