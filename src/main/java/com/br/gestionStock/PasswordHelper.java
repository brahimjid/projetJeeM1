package com.br.gestionStock;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordHelper {
    public static String encrypt(String password){
       // System.out.println(BCrypt.withDefaults().hashToString(12, password.toCharArray()));
        return BCrypt.withDefaults().hashToString(6, password.toCharArray());
    }

    public static boolean check(String plain,String encrypted){
        return BCrypt.verifyer().verify(plain.toCharArray(), encrypted).verified;
    }

}
