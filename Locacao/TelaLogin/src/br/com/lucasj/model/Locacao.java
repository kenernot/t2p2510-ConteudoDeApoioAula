/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lucasj.model;

import java.util.Date;

/**
 *
 * @author lukas
 */
public class Locacao {

    private int idLocacao;
    private Cliente clinte;
    private Automovel automovel;
    private String horaInicio;
    private String horaFim;
    private Double kmInicio;
    private Double kmFim;
    private Double kmRodado;
    private Double tempoHoras;
    private Double vlHora;
    private Double vlKm;
    private Double valorTotal;
    private String situacao;

    public int getIdLocacao() {
        return idLocacao;
    }

    public void setIdLocacao(int idLocacao) {
        this.idLocacao = idLocacao;
    }

    public Cliente getCliente() {
        return clinte;
    }

    public void setClinte(Cliente clinte) {
        this.clinte = clinte;
    }

    public Automovel getAutomovel() {
        return automovel;
    }

    public void setAutomovel(Automovel automovel) {
        this.automovel = automovel;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }

    public Double getKmInicio() {
        return kmInicio;
    }

    public void setKmInicio(Double kmInicio) {
        this.kmInicio = kmInicio;
    }

    public Double getKmFim() {
        return kmFim;
    }

    public void setKmFim(Double kmFim) {
        this.kmFim = kmFim;
    }

    public Double getKmRodado() {
        return kmRodado;
    }

    public void setKmRodado(Double kmRodado) {
        this.kmRodado = kmRodado;
    }

    public Double getTempoHoras() {
        return tempoHoras;
    }

    public void setTempoHoras(Double tempoHoras) {
        this.tempoHoras = tempoHoras;
    }

    public Double getVlHora() {
        return vlHora;
    }

    public void setVlHora(Double vlHora) {
        this.vlHora = vlHora;
    }

    public Double getVlKm() {
        return vlKm;
    }

    public void setVlKm(Double vlKm) {
        this.vlKm = vlKm;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
