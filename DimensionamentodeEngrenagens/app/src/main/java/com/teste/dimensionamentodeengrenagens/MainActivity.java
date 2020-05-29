package com.teste.dimensionamentodeengrenagens;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.teste.dimensionamentodeengrenagens.controller.Dimensiona;
import com.teste.dimensionamentodeengrenagens.controller.FatorServico;
import com.teste.dimensionamentodeengrenagens.controller.TensaoMaxima;
import com.teste.dimensionamentodeengrenagens.model.Engrenagem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            addItensCampoTipoServico();
            addItensMaterial();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("WrongViewCast")
    private void addItensCampoTipoServico() throws Exception {
//    Spinner spTP = findViewById(R.id.campoTipoServico);
//    ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
//            android.R.layout.simple_list_item_1,FatorServico.tipoServico(this));
//    //    Log.d("myTag111","Ganhador111"+ FatorServico.tipoServico(this).get(1));
//    spTP.setAdapter(adapter);
//        ArrayList<String> l = new ArrayList<String>();
//        l = FatorServico.tipoServico(this);
//        try {
//            adapter = new ArrayAdapter(this,
//                    android.R.layout.simple_spinner_dropdown_item, l);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        ((Spinner)findViewById(R.id.botaoTipoEngrenagemInt)).setAdapter(adapter);

        Spinner spinner =  findViewById(R.id.campoTipoServico);
        ArrayList<String> listaTP = FatorServico.tipoServico(this);
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
            android.R.layout.simple_list_item_1,listaTP);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void addItensMaterial() throws Exception {
        Spinner spinner =  findViewById(R.id.campoMaterial);
        ArrayList<String> listaM = TensaoMaxima.material(this);
        ArrayAdapter<String> adapter = new ArrayAdapter(MainActivity.this,
                android.R.layout.simple_list_item_1,listaM);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

    public void botaoLimpar(View view) {
        ((EditText)findViewById(R.id.campoNumeroDentesPiao)).setText(null);
        ((EditText)findViewById(R.id.campoNumeroDentesCoroa)).setText(null);
        ((EditText)findViewById(R.id.campoRelacaoDiametroLarguraPiao)).setText("0.25");
        ((EditText)findViewById(R.id.campoPotenciaMotor)).setText(null);
        ((EditText)findViewById(R.id.campoRotacaoMotor)).setText(null);
        ((EditText)findViewById(R.id.campoDureza)).setText(null);
//        (findViewById(R.id.botaoTempoTrabalho10)).setSelected(false);
//        (findViewById(R.id.botaoTempoTrabalho24)).setSelected(false);
        ((EditText)findViewById(R.id.campoTempoTrabalhoTotal)).setText(null);
//        (findViewById(R.id.botaoTipoEngrenagemInt)).setSelected(false);
//        (findViewById(R.id.botaoTipoEngrenagemExt)).setSelected(false);
        (findViewById(R.id.campoTipoServico)).setSelected(false);
        (findViewById(R.id.campoMaterial)).setSelected(false);
        ((EditText)findViewById(R.id.campoAnguloPressao)).setText("20");
    }

    private int converteCamposInt(EditText editText){
        if(editText.getText().length()!=0){
            return Integer.parseInt(((Editable)(editText).getText()).toString());
        }
        return 0;
    }

    private float converteCamposFloat(EditText editText){
        if(editText.getText().length()!=0){
            return Float.parseFloat(((Editable)(editText).getText()).toString());
        }
        return 0;
    }

    @SuppressLint("ResourceType")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void botaoCalcula(View view) throws Exception {
        int numeroDentesPiao = converteCamposInt((EditText) findViewById(R.id.campoNumeroDentesPiao));////Errado
        int numeroDentesCoroa = converteCamposInt((EditText) findViewById(R.id.campoNumeroDentesCoroa));
        float diametroLarguraPiao = converteCamposFloat((EditText) findViewById(R.id.campoRelacaoDiametroLarguraPiao));
        float potenciaMotor = converteCamposFloat((EditText) findViewById(R.id.campoPotenciaMotor));
        int rotacaoMotor = converteCamposInt((EditText) findViewById(R.id.campoRotacaoMotor));
        float dureza = converteCamposFloat((EditText) findViewById(R.id.campoDureza));

        String tempoTrabalho;
        if(((RadioButton)findViewById(R.id.botaoTempoTrabalho10)).isChecked()){
            tempoTrabalho = "10h";
        }else {
            tempoTrabalho = "24h";
        }

        int tempoTrabalhoTotal = converteCamposInt((EditText) findViewById(R.id.campoTempoTrabalhoTotal));

        boolean tipoEngrenagem;
        if(((RadioButton)findViewById(R.id.botaoTipoEngrenagemExt)).isChecked()){
            tipoEngrenagem= Engrenagem.EXTERNA;
        }else {
            tipoEngrenagem= Engrenagem.INTERNA;
        }

        String tipoServico= (String) ((Spinner)findViewById(R.id.campoTipoServico)).getSelectedItem();
        String tipoMaterial= (String) ((Spinner)findViewById(R.id.campoMaterial)).getSelectedItem();
        float anguloPressao = converteCamposFloat((EditText) findViewById(R.id.campoAnguloPressao));

        Log.d("Valores","nC"+numeroDentesCoroa+"\nnP"+numeroDentesPiao+"\ndLP"+diametroLarguraPiao+"\npotM"
                +potenciaMotor+"\nrotM"+rotacaoMotor+"\ndureza"+dureza+"\ntTrab"+tempoTrabalho+"\ntempTrabTot"+tempoTrabalhoTotal+"\ntE"
                +tipoEngrenagem+"\ntS"+tipoServico+"\ntM"+tipoMaterial+"\naP"+anguloPressao);

        if(numeroDentesCoroa==0||numeroDentesPiao==0||diametroLarguraPiao==0||potenciaMotor==0||rotacaoMotor==0||
                dureza==0||tempoTrabalhoTotal==0||anguloPressao==0){
            AlertDialog.Builder a = new AlertDialog.Builder(this);
            a.setTitle("Atenção!");
            a.setMessage("Preencha todos os campos.");
            a.setPositiveButton("OK",
                    new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            //nada
                        }
                    });
            a.show();
        }else {

            final Dimensiona dimensiona = new Dimensiona();
            dimensiona.iniciaValores(numeroDentesPiao,numeroDentesCoroa,diametroLarguraPiao,potenciaMotor,
                    rotacaoMotor,dureza,tempoTrabalho, tempoTrabalhoTotal, tipoEngrenagem, tipoServico,
                    tipoMaterial, anguloPressao, this);

            final ArrayList<ArrayList> possiveisM = dimensiona.getPossiveisModulosLarguras();

            CharSequence[] charSequence = new CharSequence[possiveisM.size()];

            Log.d("",""+possiveisM.size());
            for(int i =0; i<possiveisM.size();i++){
                charSequence[i] = getString(R.string.modulo)+possiveisM.get(i).get(0)+" "
                        +getString(R.string.largura)+possiveisM.get(i).get(1);
            }


            ArrayAdapter adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, charSequence);

            final AlertDialog.Builder a = new AlertDialog.Builder(this);
                a.setTitle("Selecione uma opção:");
                a.setSingleChoiceItems(adapter, 0, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        dimensiona.dimencionaPar((Float) possiveisM.get(which).get(0), (Float) possiveisM.get(which).get(1));

                        Intent it = new Intent(a.getContext(), Resultados.class);///colocar fora ou sla
                        it.putExtra("Informacao", tranformaCoroaPiaoParaString(dimensiona.getCoroa(),dimensiona.getPiao()));
                        startActivity(it);
                    }
                });
            a.show();

        }


    }
    private String tranformaCoroaPiaoParaString(Engrenagem coroa, Engrenagem piao){
        String s = " "+getString(R.string.piao) +""+
                "\n    "+getString(R.string.modulo)+piao.getModulo()+
                "\n    "+getString(R.string.anguloPressao)+piao.getAnguloPressao()+
                "\n    "+getString(R.string.potenciaMotor)+piao.getPotenciaMotor()+
                "\n    "+getString(R.string.rotacaoMotor)+piao.getRotacaoMotor()+
                "\n    "+getString(R.string.material)+piao.getMaterial()+
                "\n    "+getString(R.string.dureza)+piao.getDureza()+
                "\n    "+getString(R.string.tempoTrabalho)+piao.getTempoTrabalho()+
                "\n    "+getString(R.string.tempoTrabalhoTotal)+piao.getTempoTrabalhoTotal()+"h"+
                "\n    "+getString(R.string.tipoServico)+piao.getTipoServico()+
                "\n    "+getString(R.string.passo)+piao.getPasso()+
                "\n    "+getString(R.string.vaoDente)+piao.getVaoDente()+
                "\n    "+getString(R.string.alturaCabeca)+piao.getAlturaCabeca()+
                "\n    "+getString(R.string.alturaPe)+piao.getAlturaPe()+
                "\n    "+getString(R.string.alturaComumDente)+piao.getAlturaComumDente()+
                "\n    "+getString(R.string.alturaTotalDente)+piao.getAlturaTotalDente()+
                "\n    "+getString(R.string.espessuraDente)+piao.getEspessuraDente()+
                "\n    "+getString(R.string.folgaCabeca)+piao.getFolgaCabeca()+
                "\n    "+getString(R.string.numeroDentes)+piao.getNumeroDentes()+
                "\n    "+getString(R.string.diametroPrimitivo)+piao.getDiametroPrimitivo()+
                "\n    "+getString(R.string.diametroBase)+piao.getDiametroBase()+
                "\n    "+getString(R.string.largura)+piao.getLargura()+
                "\n    "+getString(R.string.diametroInterno)+piao.getDiametroInterno()+
                "\n    "+getString(R.string.diametroExterno)+piao.getDiametroExterno()+

                "\n\n "+getString(R.string.coroa)+
                "\n    "+getString(R.string.modulo)+coroa.getModulo()+
                "\n    "+getString(R.string.anguloPressao)+ coroa.getAnguloPressao()+
                "\n    "+getString(R.string.potenciaMotor)+coroa.getPotenciaMotor()+
                "\n    "+getString(R.string.rotacaoMotor)+coroa.getRotacaoMotor()+
                "\n    "+getString(R.string.material)+coroa.getMaterial()+
                "\n    "+getString(R.string.dureza)+coroa.getDureza()+
                "\n    "+getString(R.string.tempoTrabalho)+coroa.getTempoTrabalho()+
                "\n    "+getString(R.string.tempoTrabalhoTotal)+coroa.getTempoTrabalhoTotal()+"h"+
                "\n    "+getString(R.string.tipoServico)+coroa.getTipoServico()+
                "\n    "+getString(R.string.passo)+coroa.getPasso()+
                "\n    "+getString(R.string.vaoDente)+coroa.getVaoDente()+
                "\n    "+getString(R.string.alturaCabeca)+coroa.getAlturaCabeca()+
                "\n    "+getString(R.string.alturaPe)+coroa.getAlturaPe()+
                "\n    "+getString(R.string.alturaComumDente)+coroa.getAlturaComumDente()+
                "\n    "+getString(R.string.alturaTotalDente)+coroa.getAlturaTotalDente()+
                "\n    "+getString(R.string.espessuraDente)+coroa.getEspessuraDente()+
                "\n    "+getString(R.string.folgaCabeca)+coroa.getFolgaCabeca()+
                "\n    "+getString(R.string.numeroDentes)+coroa.getNumeroDentes()+
                "\n    "+getString(R.string.diametroPrimitivo)+coroa.getDiametroPrimitivo()+
                "\n    "+getString(R.string.diametroBase)+coroa.getDiametroBase()+
                "\n    "+getString(R.string.largura)+coroa.getLargura()+
                "\n    "+getString(R.string.diametroInterno)+coroa.getDiametroInterno()+
                "\n    "+getString(R.string.diametroExterno)+coroa.getDiametroExterno();
        return s;
    }

}
