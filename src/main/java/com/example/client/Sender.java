package com.example.client;

import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Sender {
    String username="";
    String email = "";

    public JSONObject saveNames(String str) throws IOException, ClassNotFoundException {
        email = str;
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("purpose","getNames");
        jsonObject.put("email",str);
        username = sendJSON(jsonObject).get("doctor").toString();
        jsonObject.put("email",email);
        jsonObject.put("username",username);
        return jsonObject;

    }

    public JSONObject getNames(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("email",email);
        jsonObject.put("username",username);
        return jsonObject;
    }

    public JSONObject sendJSON(JSONObject json) throws IOException, ClassNotFoundException {
        try {
            final Socket clientSocket;
            System.out.println("Connecting to Server.....");
            clientSocket = new Socket("127.0.0.1", 5000);
            System.out.println("Server Connected....");
            JSONObject jsonReturn;

            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            System.out.println("Data : "+json);
            System.out.println("Sending Data");
            objectOutputStream.writeObject(json);
            System.out.println("Waiting for Server to Responds");
            jsonReturn =(JSONObject) objectInputStream.readObject();
            System.out.println("Return Data From Server : "+jsonReturn);
            return jsonReturn;
        }catch (Exception e){
            final Socket clientSocket;
            System.out.println("Connecting to Server.....");
            clientSocket = new Socket("127.0.0.1", 5001);
            System.out.println("Server Connected....");
            JSONObject jsonReturn;

            OutputStream outputStream = clientSocket.getOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            InputStream inputStream = clientSocket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            System.out.println("Data : "+json);
            System.out.println("Sending Data");
            objectOutputStream.writeObject(json);
            System.out.println("Waiting for Server to Responds");
            jsonReturn =(JSONObject) objectInputStream.readObject();
            System.out.println("Return Data From Server : "+jsonReturn);
            return jsonReturn;

        }

    }

    public int sendOTP(String email) throws IOException {
        JSONObject jOb  = new JSONObject();
        jOb.put("purpose","sendotp");
        jOb.put("to",email);

        final Socket clientSocket;
        System.out.println("Connecting to Server.....");
        clientSocket = new Socket("127.0.0.1", 5000);

        OutputStream outputStream = clientSocket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

        objectOutputStream.writeObject(jOb);


        return 0;
    }


    public static void main(String[] args) throws IOException {
        Sender sender = new Sender();
    }
}
