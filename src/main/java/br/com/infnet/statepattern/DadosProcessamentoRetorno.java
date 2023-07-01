package br.com.infnet.statepattern;



import java.util.List;

public class DadosProcessamentoRetorno {
    private OperacaoArquivoDAO dao;
    private  List<Retorno> retornos;
    private  Integer nsa;



    public static final DadosProcessamentoRetorno of(OperacaoArquivoDAO dao, List<Retorno> retornos, Integer nsa){
       return new DadosProcessamentoRetorno(dao, retornos, nsa);
    }

   private DadosProcessamentoRetorno(OperacaoArquivoDAO dao, List<Retorno> retornos, Integer nsa) {
        this.dao = dao;
        this.retornos = retornos;
        this.nsa = nsa;
    }

    public OperacaoArquivoDAO getDao() {
        return dao;
    }

    public List<Retorno> getRetornos() {
        return retornos;
    }
    public Integer getNsa() {
        return nsa;
    }

}
