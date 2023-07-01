package br.com.infnet.statepattern;





public class ArquivoRetornoProcessado implements EstadoRetorno {

    private EstadoRetorno proximoEstado;

    @Override
    public void executaAcao(DadosProcessamentoRetorno dadosProcessamentoRetorno){
        Boolean existeProcessamentoDesteArquivoComErro = dadosProcessamentoRetorno.getDao().verificarSeHouveProcessamentoArquivoRComErro(dadosProcessamentoRetorno.getNsa());

        if(existeProcessamentoDesteArquivoComErro){
            proximoEstado = new ArquivoRetornoProcessadoComErro();
            proximoEstado.executaAcao(dadosProcessamentoRetorno);
        }

    }


}
