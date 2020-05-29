package com.teste.dimensionamentodeengrenagens;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.teste.dimensionamentodeengrenagens.model.Engrenagem;

public class Resultados extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);
        //Pega a intent de outra activity
        Intent it = getIntent();

        //Recuperei a string da outra activity
        String informacao = it.getStringExtra("Informacao");

        //Log.i("Informacao: ", informacao);
        TextView textView = findViewById(R.id.resultado);
        textView.setText(informacao);

    }

//    void resultados(Engrenagem piao, Engrenagem coroa){
//        TextView textView = findViewById(R.id.resultado);
//
//        textView.setText(getString(R.string.piao));
//        textView.append("\n"+getString(R.string.modulo)+piao.getModulo());
//        textView.append("\n"+getString(R.string.anguloPressao)+piao.getAnguloPressao());
//        textView.append("\n"+getString(R.string.potenciaMotor)+piao.getPotenciaMotor());
//        textView.append("\n"+getString(R.string.rotacaoMotor)+piao.getRotacaoMotor());
//        textView.append("\n"+getString(R.string.material)+piao.getMaterial());
//        textView.append("\n"+getString(R.string.dureza)+piao.getDureza());
//        textView.append("\n"+getString(R.string.tempoTrabalho)+piao.getTempoTrabalho());
//        textView.append("\n"+getString(R.string.tempoTrabalhoTotal)+piao.getTempoTrabalhoTotal());
//        textView.append("\n"+getString(R.string.tipoServico)+piao.getTipoServico());
//        textView.append("\n"+getString(R.string.passo)+piao.getPasso());
//        textView.append("\n"+getString(R.string.vaoDente)+piao.getVaoDente());
//        textView.append("\n"+getString(R.string.alturaCabeca)+piao.getAlturaCabeca());
//        textView.append("\n"+getString(R.string.alturaPe)+piao.getAlturaPe());
//        textView.append("\n"+getString(R.string.alturaComumDente)+piao.getAlturaComumDente());
//        textView.append("\n"+getString(R.string.alturaTotalDente)+piao.getAlturaTotalDente());
//        textView.append("\n"+getString(R.string.espessuraDente)+piao.getEspessuraDente());
//        textView.append("\n"+getString(R.string.folgaCabeca)+piao.getFolgaCabeca());
//        textView.append("\n"+getString(R.string.numeroDentes)+piao.getNumeroDentes());
//        textView.append("\n"+getString(R.string.diametroPrimitivo)+piao.getDiametroPrimitivo());
//        textView.append("\n"+getString(R.string.diametroBase)+piao.getDiametroBase());
//        textView.append("\n"+getString(R.string.largura)+piao.getLargura());
//        textView.append("\n"+getString(R.string.diametroInterno)+piao.getDiametroInterno());
//        textView.append("\n"+getString(R.string.diametroExterno)+piao.getDiametroExterno());
//
//        textView.append("\n\n"+getString(R.string.coroa));
//        textView.append("\n"+getString(R.string.modulo)+coroa.getModulo());
//        textView.append("\n"+getString(R.string.anguloPressao)+coroa.getAnguloPressao());
//        textView.append("\n"+getString(R.string.potenciaMotor)+coroa.getPotenciaMotor());
//        textView.append("\n"+getString(R.string.rotacaoMotor)+coroa.getRotacaoMotor());
//        textView.append("\n"+getString(R.string.material)+coroa.getMaterial());
//        textView.append("\n"+getString(R.string.dureza)+coroa.getDureza());
//        textView.append("\n"+getString(R.string.tempoTrabalho)+coroa.getTempoTrabalho());
//        textView.append("\n"+getString(R.string.tempoTrabalhoTotal)+coroa.getTempoTrabalhoTotal());
//        textView.append("\n"+getString(R.string.tipoServico)+coroa.getTipoServico());
//        textView.append("\n"+getString(R.string.passo)+coroa.getPasso());
//        textView.append("\n"+getString(R.string.vaoDente)+coroa.getVaoDente());
//        textView.append("\n"+getString(R.string.alturaCabeca)+coroa.getAlturaCabeca());
//        textView.append("\n"+getString(R.string.alturaPe)+coroa.getAlturaPe());
//        textView.append("\n"+getString(R.string.alturaComumDente)+coroa.getAlturaComumDente());
//        textView.append("\n"+getString(R.string.alturaTotalDente)+coroa.getAlturaTotalDente());
//        textView.append("\n"+getString(R.string.espessuraDente)+coroa.getEspessuraDente());
//        textView.append("\n"+getString(R.string.folgaCabeca)+coroa.getFolgaCabeca());
//        textView.append("\n"+getString(R.string.numeroDentes)+coroa.getNumeroDentes());
//        textView.append("\n"+getString(R.string.diametroPrimitivo)+coroa.getDiametroPrimitivo());
//        textView.append("\n"+getString(R.string.diametroBase)+coroa.getDiametroBase());
//        textView.append("\n"+getString(R.string.largura)+coroa.getLargura());
//        textView.append("\n"+getString(R.string.diametroInterno)+coroa.getDiametroInterno());
//        textView.append("\n"+getString(R.string.diametroExterno)+coroa.getDiametroExterno());
//    }
}
