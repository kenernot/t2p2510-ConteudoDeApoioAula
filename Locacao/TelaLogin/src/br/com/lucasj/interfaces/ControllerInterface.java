/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.interfaces;

/**
 *
 * @author lukas
 */
public interface ControllerInterface {
    /**
     * Método padrão que retornorá uma mensagem em caso de erro, se houver.
     * Caso não haver erros, deve retornar nulo.
     * @return 
     */
    public String verificarCamposObrigatorios();
}
