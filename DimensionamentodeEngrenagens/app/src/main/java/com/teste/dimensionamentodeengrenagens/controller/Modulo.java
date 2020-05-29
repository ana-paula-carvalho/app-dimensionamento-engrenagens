package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;

public final class Modulo {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float normaliza(float modulo, Context ctx) throws Exception {
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("Modulo.csv"), "ISO8859-1");

        float l = 0;
        try (BufferedReader reader=new BufferedReader(is)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println("antesNormalizaModulo: "+modulo);
                l = Float.parseFloat(linha);
                if (l >= modulo) {
                    System.out.println("normalizaModulo: "+l);
                    return l;
                }
            }
        }
        System.err.println("normaliza:Modulo não encontrado " + modulo);
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float proximoModulo(float modulo, Context ctx) throws Exception {
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("Modulo.csv"), "ISO8859-1");

        float l;
        try (BufferedReader reader=new BufferedReader(is)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                l = Float.parseFloat(linha);
                if (l > (modulo + 1.5E-10)) {
                    System.out.println("proximoModulo: "+l);
                    return l;
                }
            }
        }
        System.err.println("proximoModulo:Modulo não encontrado " + modulo);
        return 1;
    }
}
