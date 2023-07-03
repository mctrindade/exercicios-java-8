package br.com.infnet.statepattern;

import java.util.List;

public class OperacaoArquivoDAOImpl implements OperacaoArquivoDAO {

    @Override
    public void salvarArquivoSemErro(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        System.out.println("Salvando dados do arquivo "+dadosProcessamentoRetorno.getNomeArquivo()+" sem Erro!");
    }

    @Override
    public void marcarArquivoComoRecusado(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        System.out.println("Marcando arquivo "+dadosProcessamentoRetorno.getNomeArquivo()+" como recusado!");
    }

    @Override
    public void salvarLinhasComErro(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        System.out.println("Salvando linhas do arquivo "+dadosProcessamentoRetorno.getNomeArquivo()+" com erro!");
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
