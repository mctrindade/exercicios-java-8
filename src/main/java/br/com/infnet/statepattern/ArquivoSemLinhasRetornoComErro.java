package br.com.infnet.statepattern;





public class ArquivoSemLinhasRetornoComErro implements EstadoRetorno{

    private EstadoRetorno estadoAnterior;


    public ArquivoSemLinhasRetornoComErro(EstadoRetorno estadoAnterior) {
        this.estadoAnterior = estadoAnterior;
    }

    @Override
    public void executaAcao(DadosProcessamentoRetorno dadosProcessamentoRetorno){

        if(estadoAnterior instanceof ArquivoRetornoProcessadoComErro && !linhasContinuamComErro(dadosProcessamentoRetorno)){
            dadosProcessamentoRetorno.getDao().salvarArquivoSemErro(dadosProcessamentoRetorno);
        }
    }

    private boolean linhasContinuamComErro(DadosProcessamentoRetorno dadosProcessamentoRetorno) {
        return dadosProcessamentoRetorno.getDao().verificarSeExisteLinhaRetornoComErro(dadosProcessamentoRetorno.getRetornos());
    }
}
