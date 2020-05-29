package com.teste.dimensionamentodeengrenagens.controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TensaoMaxima {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static float tensaoMaximaMaterial(String material,Context ctx) throws Exception {
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("TensaoMaxima.csv"), "ISO8859-1");

        try (BufferedReader reader=new BufferedReader(is)) {
            String linha;
            String[] l;
            while ((linha = reader.readLine()) != null) {
                l = linha.split(";");
                if (material.equals(l[0])) {
                    reader.close();
                    return Float.parseFloat(l[1]);
                }
            }
        }
        is.close();
        System.out.println("Material não encontrado");
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public static ArrayList<String> material(Context ctx) throws Exception {
        ArrayList<String> lista = new ArrayList();
        AssetManager assetManager= ctx.getAssets();
        InputStreamReader is=new InputStreamReader(assetManager.open("TensaoMaxima.csv"), "ISO8859-1");

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
        System.err.println("Material não encontrado");
        return lista;
    }
}
