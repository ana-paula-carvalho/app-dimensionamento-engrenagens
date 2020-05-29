package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FatorQ {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float fatorQ(int numeroDentes, boolean tipoEngrenagem, Context ctx) throws Exception {
        String caminho;
        if (tipoEngrenagem) {
            caminho = "FatorQint.csv";
        } else {
            caminho = "FatorQex.csv";
        }
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open(caminho), "ISO8859-1");

        try (BufferedReader reader=new BufferedReader(is)) {
            String linha = reader.readLine();
            String[] l = linha.split(";");
            while ((linha = reader.readLine()) != null) {
                l = linha.split(";");
                if (l[0].equals(Integer.toString(numeroDentes))) {
                    reader.close();
                    return Float.parseFloat(l[1]);
                }
            }
        }
        is.close();
        System.err.println("Fator de serviço não encontrado");
        return 0;
    }
}
