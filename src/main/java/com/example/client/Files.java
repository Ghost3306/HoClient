package com.example.client;

import org.json.simple.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Files {
    Sender sender = new Sender();

    public void saveData(String em) throws IOException, ClassNotFoundException {
        FileWriter name = new FileWriter("username.txt");
        FileWriter email = new FileWriter("email.txt");
        JSONObject jsonData = new JSONObject();
        jsonData.put("purpose","getNames");
        jsonData.put("email",em);
        jsonData.put("for","doctors");
        JSONObject jsonReturn = new JSONObject();
        jsonReturn = sender.sendJSON(jsonData);
        name.write(jsonReturn.get("doctor").toString());
        email.write(em);
        name.close();
        email.close();
    }
    public void create() throws IOException {
        File files = new File("patient.txt");
        files.createNewFile();
    }
    public JSONObject readFile() throws FileNotFoundException {
        File email = new File("email.txt");
        Scanner emailRead = new Scanner(email);
        JSONObject jsonData = new JSONObject();
        File user = new File("username.txt");
        Scanner userRead = new Scanner(user);
        try {
            while (emailRead.hasNextLine()){
                String data = emailRead.nextLine();
                jsonData.put("email",data);
                System.out.println(data);
            }
            while (userRead.hasNextLine()){
                String data = userRead.nextLine();
                jsonData.put("username",data);
                System.out.println(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return jsonData;



    }
    public static void main(String args[]) throws IOException, ClassNotFoundException {
            Files files = new Files();
            files.create();
    }
}
