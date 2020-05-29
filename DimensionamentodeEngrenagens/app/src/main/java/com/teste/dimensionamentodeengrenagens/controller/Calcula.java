package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;


import java.util.ArrayList;

/**
 *
 * @author anapa
 */
public final class Calcula {
//passar a largura tbm
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<ArrayList> possiveisModulosLarguras(int numeroDentesPiao, int numeroDentesCoroa,
                                                                float relacaoDiametroLarguraPiao, float potenciaMotor, int rotacaoMotor,
                                                                float dureza, String tempoTrabalho, int tempoTrabalhoTotal, boolean tipoEngrenagem,
                                                                String tipoServico, String material, float anguloPressao, Context ctx) throws Exception {
        
        ArrayList<ArrayList> possiveisModulosLarguras = new ArrayList();
        ArrayList<Float> mat = new ArrayList<Float>();
        
        float momentoTensor = Calcula.momentoTensor(potenciaMotor, rotacaoMotor);
        float relacaoTrasmissao = Calcula.relacaoTrasmissao(numeroDentesCoroa, numeroDentesPiao);
        float fatorDuarabilidade = Calcula.fatorDuarabilidade(rotacaoMotor, tempoTrabalhoTotal);
        float pressaoAdmissivel = Calcula.pressaoAdmissivel(fatorDuarabilidade, dureza);
        float fatorServico = FatorServico.fatorServico(tipoServico, tempoTrabalho,ctx);
        float volumeMin = Calcula.volumeMinimo(momentoTensor, relacaoTrasmissao,
                pressaoAdmissivel, fatorServico, tipoEngrenagem);
        float fatorQ = FatorQ.fatorQ(numeroDentesPiao, tipoEngrenagem, ctx);

        System.out.println("Vmin: "+volumeMin+" RelaçãoDL: "+relacaoDiametroLarguraPiao+" nDentesP: "+numeroDentesPiao);

        float modulo = Calcula.modulo(Calcula.diametroPrimitivoPiao2(volumeMin, relacaoDiametroLarguraPiao), numeroDentesPiao);
        float larguraPiao = Calcula.larguraPiao(volumeMin, 
                Calcula.diametroPrimitivoPiao2(volumeMin, relacaoDiametroLarguraPiao));
        float tensaoMaterial = TensaoMaxima.tensaoMaximaMaterial(material, ctx);
        System.out.println(larguraPiao);
        modulo = Modulo.normaliza(modulo, ctx);
        mat.add(modulo);
        mat.add(Calcula.larguraPiao(tensaoMaterial, 
                Calcula.forcaTangencial(momentoTensor, Calcula.diametroPrimitivoPiao(modulo, numeroDentesPiao)), 
                fatorQ, modulo, fatorServico));
        possiveisModulosLarguras.add(mat);
        
        float tensaoMax = Calcula.tensaoMax(Calcula.forcaTangencial(momentoTensor, 
                    Calcula.diametroPrimitivoPiao(modulo, numeroDentesPiao)), 
                    fatorServico, fatorQ, larguraPiao, modulo);
        
        while (tensaoMax > tensaoMaterial){
            System.out.println(modulo);
            mat = new ArrayList<>();
            modulo = Modulo.proximoModulo(modulo, ctx);
            
            mat.add(modulo);
            mat.add(Calcula.larguraPiao(tensaoMaterial, 
                Calcula.forcaTangencial(momentoTensor, Calcula.diametroPrimitivoPiao(modulo, numeroDentesPiao)), 
                fatorQ, modulo, fatorServico));
            
            possiveisModulosLarguras.add(mat);
            
            tensaoMax = Calcula.tensaoMax(Calcula.forcaTangencial(momentoTensor, 
                    Calcula.diametroPrimitivoPiao(modulo, numeroDentesPiao)), 
                    fatorServico, fatorQ, larguraPiao, modulo);
        } 
        System.out.println("mat na Calcula"+mat);
        return possiveisModulosLarguras;
    }

    public static float modulo(float diametroPrimitivo, int numeroDentes) {
        return diametroPrimitivo / numeroDentes;
    }

    public static float modulo(float passo) {
        return (float) (passo / Math.PI);
    }

    public static float modulo(float forcaTangencial, float fatorQ, float fatorServico, float larguraPiao, float tensaoMax) {
        return (float) (forcaTangencial * fatorQ * fatorServico) / (larguraPiao * tensaoMax);
    }

    public static float passoM(float modulo) {
        return (float) (modulo * Math.PI);
    }

    public static float passoED(float espessuraDente) {
        return espessuraDente * 2;
    }

    public static float passoVD(float vaoDente) {
        return vaoDente * 2;
    }

    public static float espessuraDoDente(float passo) {
        return (passo / 2);
    }

    public static float alturaComumDoDente(float modulo) {
        return 2 * modulo;
    }

    public static float alturaTotalDente(float modulo) {
        return (float) (2.2 * modulo);
    }

    public static float alturaPe(float modulo) {
        return (float) (1.2 * modulo);
    }

    public static float vaoDente(float passo) {
        return (float) passo / 2;
    }

    public static float folgaCabeca(float modulo) {
        return (float) (0.2 * modulo);
    }

    public static float larguraDente(float relacaoDiametroLarguraPiao, float diametroPrimitivoPiao) {
        return relacaoDiametroLarguraPiao * diametroPrimitivoPiao;
    }
    
    
    public static float larguraPiao(float volumeMin, float diametroPrimitivoPiao) {
        return (float) (volumeMin / Math.pow(diametroPrimitivoPiao, 2));
    }

    public static float larguraPiao(float tensaoMaterial, float focaTang, float fatorQ, float modulo, float fatorServico) {
        return (float) ((focaTang * fatorQ * fatorServico) / (tensaoMaterial * modulo));
    }

    public static float relacaoTrasmissao(int numeroDentesCoroa, int numeroDentesPiao) {
        return (float) numeroDentesCoroa / (float) numeroDentesPiao;
    }

    public static float relacaoTrasmissao(float diametroPimitivoCoroa, float diametroPimitivoPiao) {
        return diametroPimitivoCoroa / diametroPimitivoPiao;
    }

    public static float diametroPrimitivoCoroa(float relacaoTransmissao, float diametroPrimitivoPiao) {
        return relacaoTransmissao * diametroPrimitivoPiao;
    }

    public static float diametroPrimitivoCoroa(float modulo, int numerDentes) {
        return modulo * numerDentes;
    }

    public static float diametroPrimitivoPiao(float relacaoTransmissao, float diametroPrimitivoCoroa) {
        return relacaoTransmissao * diametroPrimitivoCoroa;
    }

    public static float diametroPrimitivoPiao2(float volumeMinimo, float relacaoDiametroLargura) {
        return (float) Math.pow((volumeMinimo / relacaoDiametroLargura), (1.0 / 3));
    }

    public static float diametroPrimitivoPiao(float modulo, int numerDentes) {
        return modulo * (float) numerDentes;
    }

    public static float diametroBase(float diametroPrimitivo, float anguloPressao) {
        return (float) (diametroPrimitivo * Math.cos(Math.toRadians(anguloPressao)));
    }

    public static float diametroInterno(float diametroPrimitivo, float modulo) {
        return (float) (diametroPrimitivo - (2.4 * modulo));
    }

    public static float diametroExterno(float diametroPrimitivo, float modulo) {
        return (float) (diametroPrimitivo + (2 * modulo));
    }

    public static float distanciaEntreCentros(float diametroPrimitivoPiao, float diametroPrimitivoCora) {
        return (diametroPrimitivoCora + diametroPrimitivoPiao) / 2;
    }

    public static float momentoTensor(float potenciaMotor, int rotacaoMotor) {
        return (float) (1000 * (30 * potenciaMotor) / (Math.PI * rotacaoMotor));
    }

    public static float fatorDuarabilidade(int rotacaoMotor, int tempoTrabalhoTotal) {
        return (float) ((60 * rotacaoMotor * tempoTrabalhoTotal) / (Math.pow(10, 6)));
    }

    public static float pressaoAdmissivel(float fatorDurabilidade, float dureza) {
        return (float) (((float) (0.487 * dureza) / (Math.pow(fatorDurabilidade, (1.0 / 6)))));
    }

    public static float volumeMinimo(float momentoTensor, float relacaoTransmissao, float pressaoAdmissivel, float fatorServico, boolean tipoEngrenagem) {
        if (tipoEngrenagem) {
            return (float) ((5.72E+5 * momentoTensor * (relacaoTransmissao - 1) * fatorServico) / (Math.pow(pressaoAdmissivel, 2.0) * (relacaoTransmissao - 0.14)));
        } else {
            return (float) ((5.72E+5 * momentoTensor * (relacaoTransmissao + 1) * fatorServico) / (Math.pow(pressaoAdmissivel, 2.0) * (relacaoTransmissao + 0.14)));
        }
    }

    public static float forcaTangencial(float momentoTensor, float diametroPrimitivoPiao) {
        return (2 * momentoTensor) / diametroPrimitivoPiao;
    }

    public static float tensaoMax(float forcaTangencial, float fatorServico, float fatorForma, float larguraPiao, float moduloNorm) {
        return (forcaTangencial * fatorForma * fatorServico) / (larguraPiao * moduloNorm);
    }
}
