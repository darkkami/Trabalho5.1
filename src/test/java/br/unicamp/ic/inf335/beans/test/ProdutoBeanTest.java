package br.unicamp.ic.inf335.beans.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.unicamp.ic.inf335.beans.ProdutoBean;

class ProdutoBeanTest {

	@Test
	void testGetAndSetCodigo() {
		ProdutoBean produto = new ProdutoBean();
		String codigo = "123";
		produto.setCodigo(codigo);
		assertEquals(codigo, produto.getCodigo());
	}

	@Test
	void testGetAndSetNome() {
		ProdutoBean produto = new ProdutoBean();
		String nome = "Nome Teste 123";
		produto.setNome(nome);
		assertEquals(nome, produto.getNome());
	}

	@Test
	void testGetAndSetDescricao() {
		ProdutoBean produto = new ProdutoBean();
		String descricao = "Produto 1";
		produto.setDescricao(descricao);
		assertEquals(descricao, produto.getDescricao());
	}

	@Test
	void testGetAndSetValor() {
		ProdutoBean produto = new ProdutoBean();
		Double valor = 12.5;
		produto.setValor(valor);
		assertEquals(valor, produto.getValor());
	}

	@Test
	void testGetAndSetEstado() {
		ProdutoBean produto = new ProdutoBean();
		String estado = "SP";
		produto.setEstado(estado);
		assertEquals(estado, produto.getEstado());
	}

	@Test
	void testDefaultConstructor() {
		ProdutoBean produto = new ProdutoBean();
		assertAll("Verificação de atributos inicializados", () -> assertEquals(new String(), produto.getCodigo()),
				() -> assertEquals(new String(), produto.getNome()),
				() -> assertEquals(new String(), produto.getDescricao()),
				() -> assertEquals(Double.valueOf(0), produto.getValor()),
				() -> assertEquals(new String(), produto.getEstado()));
	}

	@Test
	void testConstructorWithParameters() {
		String codigo = "123";
		String nome = "Nome Produto";
		String descricao = "Descrição Produto";
		Double valor = 12.5;
		String estado = "SP";

		ProdutoBean produto = new ProdutoBean(codigo, nome, descricao, valor, estado);

		assertAll("Verificação de atributos inicializados", () -> assertEquals(codigo, produto.getCodigo()),
				() -> assertEquals(nome, produto.getNome()), () -> assertEquals(descricao, produto.getDescricao()),
				() -> assertEquals(valor, produto.getValor()), () -> assertEquals(estado, produto.getEstado()));
	}

	@Test
	void testCompareToMinorValue() {
		ProdutoBean produto1 = new ProdutoBean();
		ProdutoBean produto2 = new ProdutoBean();

		produto1.setValor(Double.valueOf(10));
		produto2.setValor(Double.valueOf(15));

		assertTrue(produto1.compareTo(produto2) < 0);
	}

	@Test
	void testCompareToEqualValue() {
		ProdutoBean produto1 = new ProdutoBean();
		ProdutoBean produto2 = new ProdutoBean();

		produto1.setValor(Double.valueOf(10));
		produto2.setValor(Double.valueOf(10));

		assertTrue(produto1.compareTo(produto2) == 0);
	}

	@Test
	void testCompareToGreaterValue() {
		ProdutoBean produto1 = new ProdutoBean();
		ProdutoBean produto2 = new ProdutoBean();

		produto1.setValor(Double.valueOf(15));
		produto2.setValor(Double.valueOf(10));

		assertTrue(produto1.compareTo(produto2) > 0);
	}

}
