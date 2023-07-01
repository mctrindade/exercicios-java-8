package br.com.infnet.statepattern;

import java.util.List;

public class ArquivoDAO implements OperacaoArquivoDAO {

    @Override
    public void salvarArquivoSemErro(DadosProcessamentoRetorno dadosProcessamentoRetorn) {
        System.out.println("Salvando dados do arquivo sem Erro!");
    }

    @Override
    public void marcarArquivoComoRecusado(DadosProcessamentoRetorno dadosProcessamentoRetorn) {
        System.out.println("Marcando arquivo como recusado!");
    }

    @Override
    public void salvarLinhasComErro(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        System.out.println("Salvando linhas do arquivo com erro!");
    }

    @Override
    public Boolean verificarSeHouveProcessamentoArquivoRComErro(Integer nsa) {
        if(nsa > 0){
            return true;
        }
        return false;
    }

     public boolean verificarSeExisteLinhaRetornoComErro( List<Retorno> retornos) {
        return retornos.stream().filter(retorno -> !retorno.getCampoErro().equals("00")).findAny().isPresent();
    }

}
