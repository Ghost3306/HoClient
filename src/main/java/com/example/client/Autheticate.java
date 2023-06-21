package com.example.client;

import javax.security.auth.login.CredentialException;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Autheticate {
    
    public Boolean validatePassword(String create,String confirm){
        String password = create;
        String confirmPass = confirm;
       if(password.equals(confirmPass)){
           if(password.length()>=8){
               Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
               Matcher matcher = pattern.matcher(password);
               if(matcher.find()){
                   for (int i = 0; i < password.length(); i++) {
                       char ch = password.charAt(i);
                       if(Character.isUpperCase(ch)){
                           for (int j = 0; j < password.length(); j++) {
                               char c = password.charAt(j);
                               if(Character.isDigit(c)){
                                   return true;
                               }else {
                                   if(j==password.length()){
                                       System.out.println("Number not found");
                                       return false;
                                   }
                               }
                           }
                       }else{
                           System.out.println("Uppercase not found");
                           return false;
                       }
                   }
               }else {
                   System.out.println("special character not found");
                   return false;
               }
           }else{
               System.out.println("length is less then 8 character");
               return false;
           }
       }else {
           System.out.println("Create and Confirm Passwords doesnt match");
           return false;
       }
       return null;
    }

    public Boolean loginPassword(String pass){
        String password = pass;
        if(password.length()>=8){
            Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
            Matcher matcher = pattern.matcher(password);
            if(matcher.find()){
                for (int i = 0; i < password.length(); i++) {
                    char ch = password.charAt(i);
                    if(Character.isUpperCase(ch)){
                        for (int j = 0; j < password.length(); j++) {
                            char c = password.charAt(j);
                            if(Character.isDigit(c)){
                                return true;
                            }else {
                                if(j==password.length()){
                                    System.out.println("Number not found");
                                    return false;
                                }
                            }
                        }
                    }else{
                        System.out.println("Uppercase not found");
                        return false;
                    }
                }
            }else {
                System.out.println("special character not found");
                return false;
            }
        }else{
            System.out.println("length is less then 8 character");
            return false;
        }
        return null;
    }
    
    public Boolean validateAge(LocalDate date){
        LocalDate dob = date;
        LocalDate currentDate = LocalDate.now();
        if(dob.equals(currentDate)){
            return false;
        } else if (dob.compareTo(currentDate)>0) {
            return false;
        }else {
            return true;
        }
    }

    public int ageCalulate(LocalDate dob){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob,currentDate);
        if(dob.equals(currentDate)){
            System.out.println("Invalid date");
            return 999;
        }
        int age =period.getYears();
        return age;
    }

    public Boolean validator(String create,String confirm,LocalDate dob){
        return validatePassword(create,confirm) && validateAge(dob);
    }

    public static void main(String[] args) {
        Autheticate autheticate = new Autheticate();
    }
}
