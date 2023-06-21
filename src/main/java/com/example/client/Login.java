package com.example.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.stage.Stage;
import org.json.simple.JSONObject;
public class Login {
    public TextField user;
    public PasswordField password;
    public RadioButton doctor;
    public RadioButton patient;
    public Button login;
    public Button cancel;
    public Label register;
    public Label forgot;
    public Button backtoLogin;
    public TextField email;
    public Button procced;
    public TextField getEmail;
    public ImageView back;
    public ImageView backB;
    public TextField otp;
    public Button verify;
    public PasswordField createpassword;
    public PasswordField confirmpassword;
    public Button submit;
    public ImageView backC;
    public Label getUpEmail;
    public RadioButton resdoctor;
    public RadioButton respatient;
    public Button backD;
    Sender sender = new Sender();

    Autheticate autheticate = new Autheticate();
    Files files = new Files();
    public String getRadio(){
        if(doctor.isSelected()){
            return "doctor";
        }else if(patient.isSelected()){
            return "patient";
        }else {
            return "null";
        }
    }


    public void cancel(ActionEvent event) {
        user.setText("");
        password.setText("");
    }
    public void gotoRegister(MouseEvent mouseEvent) throws IOException {

        Stage stage = (Stage) register.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
        stage.show();


    }
    public void backD() throws IOException {
        Stage stage = (Stage) backD.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ForgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
        stage.show();
    }
    public void forgot(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) login.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ForgotPassword.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Forgot Password");
        stage.setScene(scene);
        stage.show();
    }

    public int login() throws IOException, ClassNotFoundException {
        String username = user.getText();
        String pass = password.getText();
        String cate = getRadio();
        System.out.println("Getting user password and category : ");
        System.out.println(username);
        System.out.println(pass );
        System.out.println(cate);
        System.out.println("Checking Password Validation....");
        Boolean result = autheticate.loginPassword(pass);
        System.out.println("Authentication result is "+result);
        System.out.println(result);
        JSONObject json = new JSONObject();
        JSONObject jsonResult = new JSONObject();
        if(result){
            json.put("purpose","login");
            json.put("user",username);
            json.put("password",pass);
            json.put("category",cate);
            files.saveData(username);



            System.out.println("Sending Result to Sender..");
            try {
                jsonResult = sender.sendJSON(json);
            }catch (Exception e){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Alert");
                alert.setHeaderText("Failed to Connect Server\nPlease turn on internet or check connections");
                alert.show();
            }



            System.out.println("Getting Result From Server...");
            System.out.println(jsonResult);
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Invalid Password\n* password contain 8 character,uppercase, number, special character\n* Choose doctor or patient");
            alert.show();
            return 0;
        }

        if(jsonResult.get("result").equals("trueD")){
            System.out.println("User Autheticate True..");
            System.out.println("Changing Scene.....");
            Stage stage = (Stage) login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("DoctorHome.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("Doctor Homepage");
            stage.setScene(scene);
            stage.show();
        } else if (jsonResult.get("result").equals("trueP")) {
            System.out.println("User Autheticate True..");
            System.out.println("Changing Scene.....");
            Stage stage = (Stage) login.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("PatientHome.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("Patient Homepage");
            stage.setScene(scene);
            stage.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Invalid username or password!!");
            alert.show();
        }
    return 1;
    }


    public void backLogin() throws IOException {
        Stage stage = (Stage) backtoLogin.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void backLoginA() throws IOException {
        Stage stage = (Stage) back.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
    public void backLoginB() throws IOException {
        Stage stage = (Stage) backB.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void backLoginC() throws IOException {
        Stage stage = (Stage) backC.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public void sendVerOtp(ActionEvent actionEvent) throws IOException {
        String email = getEmail.getText();
        sender.sendOTP(email);
        Stage stage = (Stage) procced.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("VerifyOTP.fxml"));

        
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("OTP Verification");
        stage.setScene(scene);
        stage.show();
    }

    public void verifyOTP() throws IOException, ClassNotFoundException {
        String getOTP = otp.getText();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("purpose","verotp");
        jsonObject.put("otp",getOTP);

        JSONObject jsonReturn = new JSONObject();
        jsonReturn = sender.sendJSON(jsonObject);

        if(jsonReturn.get("result").equals("true")){
            Stage stage = (Stage) verify.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("ChangePassword.fxml"));

            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);

            stage.setTitle("Update Password");
            stage.setScene(scene);
            stage.show();
        }else{
            Stage stage = (Stage) verify.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("VerifyOTP.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
            stage.setTitle("OTP Verification");
            stage.setScene(scene);
            stage.show();
        }

    }
    public String resetRadio(){
        if(resdoctor.isSelected()){
            return "doctor";
        } else if (respatient.isSelected()) {
            return "patient";
        }else{
            return "null";
        }
    }
    public void setLabel(String str){
        getUpEmail.setText(str);
    }
    public void changePassword() throws IOException, ClassNotFoundException {
        Boolean checkPass = autheticate.validatePassword(createpassword.getText(),confirmpassword.getText());
        JSONObject jsonReturn  = new JSONObject();
        if(checkPass && (resetRadio().equals("doctor") || resetRadio().equals("patient"))){
            JSONObject jsonData = new JSONObject();
            jsonData.put("purpose","updatePass");
            jsonData.put("password",createpassword.getText());
            jsonData.put("cate",resetRadio());
            jsonReturn = sender.sendJSON(jsonData);
            if(jsonReturn.get("result").equals("true")){
                Stage stage = (Stage) submit.getScene().getWindow();
                FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("Login.fxml"));
                Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
                stage.setTitle("Login");
                stage.setScene(scene);
                stage.show();
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Alert");
            alert.setHeaderText("Invalid Password\n* password contain 8 character,uppercase, number, special character\n* Choose doctor or patient");
            alert.show();
        }
    }
}
