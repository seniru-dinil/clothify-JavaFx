package edu.icet.clothify.util;

public class Validation {
    private static  Validation instance;
    private Validation(){}
    public static Validation getInstance(){
        return instance==null?instance=new Validation():instance;
    }

    public boolean isMatch(String newPassword,String confirmpassword){
        return newPassword.equals(confirmpassword);
    }
}
