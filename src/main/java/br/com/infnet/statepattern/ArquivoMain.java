package br.com.infnet.statepattern;

import br.com.infnet.util.PrintConsole;

import java.util.Arrays;
import java.util.List;
import static br.com.infnet.util.PrintConsole.*;

public class ArquivoMain {


    public static void main(String[] args){
        List<Retorno> retornos =  Arrays.asList(new Retorno("00"), new Retorno("11"));
        DadosProcessamentoRetorno dadosProcessamentoRetorno = DadosProcessamentoRetorno.of(new ArquivoDAO(), retornos, 1);

        print("Testando Estado Arquivo Não Processado com Linhas Com Erro...");

        ArquivoRetornoNaoProcessado arquivoRetornoNaoProcessado = new ArquivoRetornoNaoProcessado();
        arquivoRetornoNaoProcessado.executaAcao(dadosProcessamentoRetorno);

         retornos =  Arrays.asList(new Retorno("00"), new Retorno("00"));
         dadosProcessamentoRetorno = DadosProcessamentoRetorno.of(new ArquivoDAO(), retornos, 1);

        print("Testando Estado Arquivo Não Processado Sem Linhas Com Erro...");

        arquivoRetornoNaoProcessado.executaAcao(dadosProcessamentoRetorno);

        print("Testando Estado Arquivo Já Processado Sem Linhas com Erro...");

        ArquivoRetornoProcessado arquivoRetornoProcessado = new ArquivoRetornoProcessado();
        arquivoRetornoProcessado.executaAcao(dadosProcessamentoRetorno);

        print("Testando Estado Arquivo Já Processado Com Linhas com Erro...");
        retornos =  Arrays.asList(new Retorno("00"), new Retorno("01"));
        dadosProcessamentoRetorno = DadosProcessamentoRetorno.of(new ArquivoDAO(), retornos, 1);
        arquivoRetornoProcessado.executaAcao(dadosProcessamentoRetorno);

    }



}
