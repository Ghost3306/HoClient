package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {
    public Button backtoLogB;
    public TextField pfname;
    public TextField plname;
    public DatePicker pdob;
    public TextField pemail;
    public TextArea paddress;
    public TextField pphone;
    public Button registerbtnPat;
    public TextField pheight;
    public TextField pweight;
    public TextField poccupation;
    public TextArea phealth;
    public TextArea pphysical;
    public PasswordField pcreatepass;
    public PasswordField pconfirmpass;
    public TextField dmname;
    public TextField pmname;
    public ImageView backA;
    Sender sender = new Sender();
    public Button back;
    public Button doctorbtn;
    public Button patientbtn;
    public Button backtoLog;
    public TextField dfname;
    public TextField dlname;
    public DatePicker ddob;
    public TextField demail;
    public TextField dphone;
    public TextArea daddress;
    public Button registerbtn;
    public PasswordField dcreatepass;
    public PasswordField dconfirmpass;

    Autheticate autheticate = new Autheticate();
    public void backLogin() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }

    public void backA() throws IOException {
        Stage stage = (Stage) backA.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }

    public void getDoc() throws IOException {
        Stage stage = (Stage) doctorbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("RegisterDoc.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }

    public void getPat() throws IOException {
        Stage stage = (Stage) patientbtn.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("RegisterPat.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }
    public void backLoginA() throws IOException {
        Stage stage = (Stage) backtoLog.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }


    public void backLoginB() throws IOException {
        Stage stage = (Stage) backtoLogB.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }
    public void RegisterDoc() throws IOException, ClassNotFoundException {
        LocalDate dob = ddob.getValue();
        String dateofbirth = dob.toString();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob,currentDate);
        String age = String.valueOf(period.getYears());
        JSONObject jsonReturn = new JSONObject();

        if(autheticate.validator(dcreatepass.getText(),dconfirmpass.getText(),ddob.getValue())){
            JSONObject jsonData = new JSONObject();
            jsonData.put("purpose","registerDoc");
            jsonData.put("fname",dfname.getText());
            jsonData.put("mname",dmname.getText());
            jsonData.put("lname",dfname.getText());
            jsonData.put("dob",dateofbirth);
            jsonData.put("age",age);
            jsonData.put("email",demail.getText());
            jsonData.put("phone",dphone.getText());
            jsonData.put("address",daddress.getText());
            jsonData.put("password",dcreatepass.getText());
            jsonReturn=sender.sendJSON(jsonData);
            if(jsonReturn.get("result").equals("true")){
                Stage stage = (Stage) registerbtn.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            }else if (jsonReturn.get("result").equals("false")){

            }
        }

    }

    public void registerPat() throws IOException, ClassNotFoundException {
        LocalDate dob = pdob.getValue();
        String dateofbirth = dob.toString();
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob,currentDate);
        String age = String.valueOf(period.getYears());
        JSONObject jsonReturn = new JSONObject();

        if(autheticate.validator(pcreatepass.getText(),pconfirmpass.getText(),pdob.getValue())){
            JSONObject jsonData = new JSONObject();
            jsonData.put("purpose","registerPat");
            jsonData.put("fname",pfname.getText());
            jsonData.put("mname",pmname.getText());
            jsonData.put("lname",pfname.getText());
            jsonData.put("dob",dateofbirth);
            jsonData.put("age",age);
            jsonData.put("email",pemail.getText());
            jsonData.put("phone",pphone.getText());
            jsonData.put("address",paddress.getText());
            jsonData.put("height",pheight.getText());
            jsonData.put("weight",pweight.getText());
            jsonData.put("health",phealth.getText());
            jsonData.put("physical",pphysical.getText());
            jsonData.put("work",poccupation.getText());
            jsonData.put("password",pcreatepass.getText());
            jsonReturn=sender.sendJSON(jsonData);
            if(jsonReturn.get("result").equals("true")){
                Stage stage = (Stage) registerbtnPat.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            }else if (jsonReturn.get("result").equals("false")){

            }
        }
    }

}
