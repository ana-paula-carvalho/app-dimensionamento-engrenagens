package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public final class Dureza {


    private static int procuraPosicaoUnidade(String unidadeDeMedida, String[] l) {
        for (int i = 0; i < l.length; i++) {
            if (unidadeDeMedida.equals(l[i])) {
                return i;
            }
        }
        System.err.println("Unidade de medida não encontrada");
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float converteParaHB(float dureza, String unidadeDeMedida, Context ctx) throws Exception {
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("Dureza.csv"), "ISO8859-1");

        try (BufferedReader reader=new BufferedReader(is)) {
            String linha = reader.readLine();
            String[] l = linha.split(";");
            int i = Dureza.procuraPosicaoUnidade(unidadeDeMedida, l);
            while ((linha = reader.readLine()) != null) {
                l = linha.split(";");
                System.out.println(l[i]);
                if (!l[i].equals("-")) {
                    if (dureza == Float.parseFloat(l[i])) {
                        reader.close();
                        return Float.parseFloat(l[1]);
                    }
                }
            }
            is.close();
            System.out.println("Não foi possivel converter");
            return 0;
        }
    }
}
