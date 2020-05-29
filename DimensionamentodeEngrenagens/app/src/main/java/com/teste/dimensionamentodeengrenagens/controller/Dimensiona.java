package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.teste.dimensionamentodeengrenagens.model.Engrenagem;

import java.util.ArrayList;

public class Dimensiona {
    private Engrenagem coroa;
    private Engrenagem piao;
    private ArrayList possiveisModulosLarguras;
    private int numeroDentesPiao;
    private int numeroDentesCoroa;
    private float potenciaMotor; 
    private int rotacaoMotor; 
    private float dureza; 
    private String tempoTrabalho; 
    private int tempoTrabalhoTotal; 
    private boolean tipoEngrenagem; 
    private String tipoServico; 
    private String material; 
    private float anguloPressao;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void iniciaValores(int numeroDentesPiao, int numeroDentesCoroa, float relacaoDiametroLarguraPiao,
                              float potenciaMotor, int rotacaoMotor, float dureza, String tempoTrabalho,
                              int tempoTrabalhoTotal, boolean tipoEngrenagem, String tipoServico,
                              String material, float anguloPressao, Context ctx) throws Exception {
        this.numeroDentesPiao = numeroDentesPiao;
        this.numeroDentesCoroa = numeroDentesCoroa;
        this.potenciaMotor = potenciaMotor;
        this.rotacaoMotor = rotacaoMotor;
        this.dureza = dureza;
        this.tempoTrabalho = tempoTrabalho;
        this.tempoTrabalhoTotal = tempoTrabalhoTotal;
        this.tipoEngrenagem = tipoEngrenagem;
        this.tipoServico = tipoServico;
        this.material = material;
        this.anguloPressao = anguloPressao;
        
        possiveisModulosLarguras = Calcula.possiveisModulosLarguras(numeroDentesPiao, numeroDentesCoroa, 
                relacaoDiametroLarguraPiao, potenciaMotor, rotacaoMotor, dureza, tempoTrabalho, 
                tempoTrabalhoTotal, tipoEngrenagem, tipoServico, material, anguloPressao, ctx);

    }
    
    public ArrayList getPossiveisModulosLarguras() {
        return possiveisModulosLarguras;
    }
    
    public void dimencionaPar(float modulo, float largura){                
        piao = new Engrenagem(numeroDentesPiao, potenciaMotor, rotacaoMotor, dureza, 
                tempoTrabalho, tempoTrabalhoTotal, tipoEngrenagem, tipoServico, 
                material, modulo, largura, anguloPressao);
        
        coroa = new Engrenagem(numeroDentesCoroa, potenciaMotor, rotacaoMotor, dureza, 
                tempoTrabalho, tempoTrabalhoTotal, tipoEngrenagem, tipoServico, material, 
                modulo, largura, anguloPressao);
    }
    
    public Engrenagem getCoroa() {
        return coroa;
    }

    public Engrenagem getPiao() {
        return piao;
    }
  
}

