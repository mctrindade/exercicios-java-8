package br.com.infnet.statepattern;



public class ArquivoRetornoNaoProcessado implements EstadoRetorno{


    private EstadoRetorno proximoEstado;



    @Override
    public void executaAcao(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        Boolean existeLinhaRetornoComErro = dadosProcessamentoRetorno.getDao().verificarSeExisteLinhaRetornoComErro(dadosProcessamentoRetorno.getRetornos());

        if(!existeLinhaRetornoComErro){
            proximoEstado = new ArquivoSemLinhasRetornoComErro( this);
            proximoEstado.executaAcao(dadosProcessamentoRetorno);
            dadosProcessamentoRetorno.getDao().salvarArquivoSemErro(dadosProcessamentoRetorno);
        }  else{
           dadosProcessamentoRetorno.getDao().marcarArquivoComoRecusado(dadosProcessamentoRetorno);
        }
    }


}
