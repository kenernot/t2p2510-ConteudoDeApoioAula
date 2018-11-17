/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.services;

import java.sql.Date;

/**
 *
 * @author lukas
 */
public class Util {

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static boolean isDate(String s) {
        try {
            Date.valueOf(s);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
