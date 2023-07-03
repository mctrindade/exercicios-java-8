package br.com.infnet.statepattern;

import java.util.ArrayList;
import java.util.List;

public final class DadosProcessamentoRetornoBuilder {
	private OperacaoArquivoDAO dao;
	private List<Retorno> retornos;
	private Integer nsa;
	private String nomeArquivo;

	public DadosProcessamentoRetornoBuilder() {
	}

	public DadosProcessamentoRetornoBuilder dao(OperacaoArquivoDAO dao) {
		this.dao = dao;
		return this;
	}

	public DadosProcessamentoRetornoBuilder addRetorno(Retorno retorno) {
		if(this.retornos == null) {
			this.retornos = new ArrayList<>();
		}
		this.retornos.add(retorno);
		return this;
	}

	public DadosProcessamentoRetornoBuilder withNsa(Integer nsa) {
		this.nsa = nsa;
		return this;
	}

	public DadosProcessamentoRetornoBuilder withNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
		return this;
	}

	public DadosProcessamentoRetorno build() {
		return new DadosProcessamentoRetorno(this.dao, this.retornos, this.nsa, this.nomeArquivo);
	}
}
