package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FatorServico {

    private static int tempoTrabalho(String tempoTrabalho, String[] l) {
        for (int i = 0; i < l.length; i++) {
            if (tempoTrabalho.equals(l[i])) {
                return i;
            }
        }
        System.err.println("Tempo trabalho não encontrado");
        return 1;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float fatorServico(String tipoServico, String tempoTrabalho, Context ctx) throws Exception {
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("FatorServico.csv"), "ISO8859-1");

        try (BufferedReader reader=new BufferedReader(is)) {
            String linha = reader.readLine();
            String[] l = linha.split(";");
            int i = tempoTrabalho(tempoTrabalho, l);

            while ((linha = reader.readLine()) != null) {
                l = linha.split(";");

                if (tipoServico.equals(l[0])) {
                    reader.close();
                    return Float.parseFloat(l[i]);
                }
            }
        }
        is.close();
        System.err.println("Fator de serviço não encontrado");
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<String> tipoServico(Context ctx) throws Exception {
        ArrayList<String> lista = new ArrayList();
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("FatorServico.csv"), "ISO8859-1");

        try (BufferedReader reader=new BufferedReader(is)) {
            String linha;
            String[] l;
            while ((linha = reader.readLine()) != null) {
                l = linha.split(";");
                lista.add(l[0]);
            }
        }
        is.close();
        lista.remove(0);
        System.err.println("Fator de serviço não encontrado");
        return lista;
    }
}
