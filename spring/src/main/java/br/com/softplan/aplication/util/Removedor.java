/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softplan.aplication.util;

/**
 *
 * @author moquiuti
 */
public class Removedor {

    public static String removeCaracteresEspeciais(String valor) {
        if (valor.contains(".")) {
            valor = valor.replace(".", "");
        }
        if (valor.contains("-")) {
            valor = valor.replace("-", "");
        }
        if (valor.contains("/")) {
            valor = valor.replace("/", "");
        }
        return valor;
    }
}
