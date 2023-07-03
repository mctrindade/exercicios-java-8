package br.com.infnet.statepattern;

import static br.com.infnet.util.PrintConsole.print;

public class ArquivoMain {

	public static void main(String[] args) {
		OperacaoArquivoDAOImpl arquivoDao = new OperacaoArquivoDAOImpl();
		DadosProcessamentoRetorno dadosProcessamentoRetorno = new DadosProcessamentoRetornoBuilder().dao(arquivoDao)
				.addRetorno(new Retorno("00")).addRetorno(new Retorno("11")).withNomeArquivo("arquivo1").withNsa(1)
				.build();

		print("Testando Estado Arquivo Não Processado com Linhas Com Erro...");

		ArquivoRetornoNaoProcessado arquivoRetornoNaoProcessado = new ArquivoRetornoNaoProcessado();
		arquivoRetornoNaoProcessado.executaAcao(dadosProcessamentoRetorno);

		dadosProcessamentoRetorno = new DadosProcessamentoRetornoBuilder().dao(arquivoDao)
				.addRetorno(new Retorno("00")).addRetorno(new Retorno("00")).withNomeArquivo("arquivo2").withNsa(1)
				.build();

		print("Testando Estado Arquivo Não Processado Sem Linhas Com Erro...");

		arquivoRetornoNaoProcessado.executaAcao(dadosProcessamentoRetorno);

		print("Testando Estado Arquivo Já Processado Sem Linhas com Erro...");

		ArquivoRetornoProcessado arquivoRetornoProcessado = new ArquivoRetornoProcessado();
		arquivoRetornoProcessado.executaAcao(dadosProcessamentoRetorno);

		print("Testando Estado Arquivo Já Processado Com Linhas com Erro...");

		dadosProcessamentoRetorno = new DadosProcessamentoRetornoBuilder().dao(arquivoDao)
				.addRetorno(new Retorno("00")).addRetorno(new Retorno("01")).withNomeArquivo("arquivo3").withNsa(3)
				.build();
		arquivoRetornoProcessado.executaAcao(dadosProcessamentoRetorno);

	}

}
