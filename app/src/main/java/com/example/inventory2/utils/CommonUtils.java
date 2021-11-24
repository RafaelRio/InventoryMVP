package com.example.inventory2.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Esta clase no podra tener clases hijas. No se puede heredar de ella
 * debido al modificador final
 */
public final class CommonUtils {
    static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
    //Todos sus metodos van a ser estaticos
    /**
     * Metodo que comprueba que la contraseña cumple los siguientes requisitos:
     *  - Debe contener al menos un digito
     *  - Debe contener un carácter en mayúscula
     *  - Debe contener un carácter en minúscula
     *  - Debe contener un carácter especial
     *  - Su longitud está entre 8 y 20
     */
    public static boolean isPasswordValid(String password){
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher m = pattern.matcher(password);
        return m.matches();
    }
}
