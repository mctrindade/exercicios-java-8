package br.com.infnet.statepattern;

import java.util.List;

public class DadosProcessamentoRetorno {
	private OperacaoArquivoDAO dao;
	private List<Retorno> retornos;
	private Integer nsa;
	private String nomeArquivo;

	public DadosProcessamentoRetorno(OperacaoArquivoDAO dao, List<Retorno> retornos, Integer nsa, String nomeArquivo) {
		this.dao = dao;
		this.retornos = retornos;
		this.nsa = nsa;
		this.nomeArquivo = nomeArquivo;
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

	public String getNomeArquivo() {
		return nomeArquivo;
	}
}
