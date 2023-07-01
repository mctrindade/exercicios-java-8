package br.com.infnet.statepattern;

import java.util.List;

public interface OperacaoArquivoDAO {

   void salvarArquivoSemErro(DadosProcessamentoRetorno dadosProcessamentoRetorn);
   void marcarArquivoComoRecusado(DadosProcessamentoRetorno dadosProcessamentoRetorn);
   void salvarLinhasComErro(DadosProcessamentoRetorno dadosProcessamentoRetorno);
   Boolean verificarSeHouveProcessamentoArquivoRComErro(Integer nsa);
   boolean verificarSeExisteLinhaRetornoComErro( List<Retorno> retornos);
}
