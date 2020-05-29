package com.teste.dimensionamentodeengrenagens.model;
import com.teste.dimensionamentodeengrenagens.controller.Calcula;

public class Engrenagem {
    public static boolean INTERNA = true;
    public static boolean EXTERNA = false;

    private final float modulo;
    private final float anguloPressao;
    private final float potenciaMotor;
    private final int rotacaoMotor;
    private final String material;
    private final float dureza;
    private final String tempoTrabalho;
    private final int tempoTrabalhoTotal;
    private final boolean tipoEngrenagem;
    private final String tipoServico;
    private float passo;
    private float vaoDente;
    private float alturaCabeca;
    private float alturaPe;
    private float alturaComumDente;
    private float alturaTotalDente;
    private float espessuraDente;
    private float folgaCabeca;
    private int numeroDentes;
    private float diametroPrimitivo;
    private float diametroBase;
    private float largura;
    private float diametroInterno;
    private float diametroExterno;


    public Engrenagem(int numeroDentes, float potenciaMotor, 
            int rotacaoMotor, float dureza, String tempoTrabalho, 
            int tempoTrabalhoTotal, boolean tipoEngrenagem, String tipoServico, 
            String material, float modulo, float largura, float anguloPressao) {
        this.numeroDentes = numeroDentes;
        this.modulo = modulo;
        this.potenciaMotor = potenciaMotor;
        this.rotacaoMotor = rotacaoMotor;
        this.dureza = dureza;
        this.tempoTrabalho = tempoTrabalho;
        this.tempoTrabalhoTotal = tempoTrabalhoTotal;
        this.tipoEngrenagem = tipoEngrenagem;
        this.tipoServico = tipoServico;
        this.material = material;
        this.largura = largura;
        this.anguloPressao = anguloPressao;
        this.passo = Calcula.passoM(modulo);
        this.vaoDente = Calcula.vaoDente(passo);
        this.alturaCabeca = this.modulo;
        this.alturaPe = Calcula.alturaPe(modulo);
        this.alturaComumDente = Calcula.alturaComumDoDente(modulo);
        this.alturaTotalDente = Calcula.alturaTotalDente(modulo);
        this.espessuraDente = Calcula.espessuraDoDente(passo);
        this.folgaCabeca = Calcula.folgaCabeca(modulo);
        this.diametroPrimitivo = Calcula.diametroPrimitivoPiao(modulo, numeroDentes);
        this.diametroBase = Calcula.diametroBase(this.diametroPrimitivo, anguloPressao);
        this.diametroInterno = Calcula.diametroInterno(this.diametroPrimitivo, modulo);
        this.diametroExterno = Calcula.diametroExterno(this.diametroPrimitivo, modulo);
        
    }

    public float getModulo() {
        return modulo;
    }

    public float getAnguloPressao() {
        return anguloPressao;
    }

    public float getPotenciaMotor() {
        return potenciaMotor;
    }

    public int getRotacaoMotor() {
        return rotacaoMotor;
    }

    public String getMaterial() {
        return material;
    }

    public float getDureza() {
        return dureza;
    }

    public String getTempoTrabalho() {
        return tempoTrabalho;
    }

    public int getTempoTrabalhoTotal() {
        return tempoTrabalhoTotal;
    }

    public boolean isTipoEngrenagem() {
        return tipoEngrenagem;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public float getPasso() {
        return passo;
    }

    public float getVaoDente() {
        return vaoDente;
    }

    public float getAlturaCabeca() {
        return alturaCabeca;
    }

    public float getAlturaPe() {
        return alturaPe;
    }

    public float getAlturaComumDente() {
        return alturaComumDente;
    }

    public float getAlturaTotalDente() {
        return alturaTotalDente;
    }

    public float getEspessuraDente() {
        return espessuraDente;
    }

    public float getFolgaCabeca() {
        return folgaCabeca;
    }

    public int getNumeroDentes() {
        return numeroDentes;
    }

    public float getDiametroPrimitivo() {
        return diametroPrimitivo;
    }

    public float getDiametroBase() {
        return diametroBase;
    }

    public float getLargura() {
        return largura;
    }

    public float getDiametroInterno() {
        return diametroInterno;
    }

    public float getDiametroExterno() {
        return diametroExterno;
    }


}
