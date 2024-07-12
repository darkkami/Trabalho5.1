package br.unicamp.ic.inf335.beans.test;

import static org.junit.jupiter.api.Assertions.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import br.unicamp.ic.inf335.beans.AnuncioBean;
import br.unicamp.ic.inf335.beans.ProdutoBean;

class AnuncioBeanTest {

	@Test
	void testGetAndSetProduto() {
		ProdutoBean produtoBean = new ProdutoBean("Código", "Nome", "Descrição", Double.valueOf(10), "Estado");
		AnuncioBean anuncioBean = new AnuncioBean();
		anuncioBean.setProduto(produtoBean);

		assertEquals(produtoBean, anuncioBean.getProduto());
	}

	@Test
	void testGetAndSetFotosUrl() throws MalformedURLException, URISyntaxException {
		ArrayList<URL> urlList = new ArrayList<URL>(
				Arrays.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL()));
		AnuncioBean anuncioBean = new AnuncioBean();

		anuncioBean.setFotosUrl(urlList);

		assertEquals(urlList, anuncioBean.getFotosUrl());
	}

	@ParameterizedTest
	@ValueSource(doubles = { -1, 0, 0.25, 0.5, 0.75, 1, 2 })
	void testGetAndSetDesconto(Double desconto) {
		AnuncioBean anuncioBean = new AnuncioBean();

		if (desconto >= 0 && desconto <= 1) {
			anuncioBean.setDesconto(desconto);
			assertEquals(desconto, anuncioBean.getDesconto());
		} else
			assertThrowsExactly(InvalidParameterException.class, () -> anuncioBean.setDesconto(desconto));
	}

	@Test
	void testDefaultConstructor() {
		AnuncioBean anuncioBean = new AnuncioBean();

		assertAll("Verificação de atributos inicializados",
				() -> assertInstanceOf(ProdutoBean.class, anuncioBean.getProduto()),
				() -> assertInstanceOf(ArrayList.class, anuncioBean.getFotosUrl()),
				() -> assertEquals(Double.valueOf(0), anuncioBean.getDesconto()));
	}

	@Test
	void testConstructorWithParameters() throws MalformedURLException, URISyntaxException {
		ProdutoBean produto = new ProdutoBean("123", "Nome Produto", "Descrição Produto", 12.5, "SP");
		ArrayList<URL> urlList = new ArrayList<URL>(
				Arrays.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL()));
		Double desconto = 0.7;

		AnuncioBean anuncioBean = new AnuncioBean(produto, urlList, desconto);

		assertAll("Verificação de atributos inicializados", () -> assertEquals(produto, anuncioBean.getProduto()),
				() -> assertEquals(urlList, anuncioBean.getFotosUrl()),
				() -> assertEquals(desconto, anuncioBean.getDesconto()));
	}

	@ParameterizedTest
	@ValueSource(doubles = { 0, 0.25, 0.5, 0.75, 1 })
	void testGetValor(double desconto) throws MalformedURLException, URISyntaxException {
		Double valor = 12.5;
		ProdutoBean produto = new ProdutoBean("123", "Nome Produto", "Descrição Produto", valor, "SP");
		ArrayList<URL> urlList = new ArrayList<URL>(
				Arrays.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL()));

		AnuncioBean anuncioBean = new AnuncioBean(produto, urlList, desconto);

		Double valorComDesconto = valor - (valor * desconto);

		assertEquals(valorComDesconto, anuncioBean.getValor());
	}

}
