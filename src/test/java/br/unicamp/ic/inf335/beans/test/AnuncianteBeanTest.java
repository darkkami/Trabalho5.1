package br.unicamp.ic.inf335.beans.test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.unicamp.ic.inf335.beans.AnuncianteBean;
import br.unicamp.ic.inf335.beans.AnuncioBean;
import br.unicamp.ic.inf335.beans.ProdutoBean;

class AnuncianteBeanTest {

	@Test
	void testSetAndGetNome() {
		AnuncianteBean anuncianteBean = new AnuncianteBean();
		String nome = "Nome Teste 123";
		anuncianteBean.setNome(nome);
		assertEquals(nome, anuncianteBean.getNome());
	}

	@Test
	void testSetAndGetCPF() {
		AnuncianteBean anuncianteBean = new AnuncianteBean();
		String cpf = "12345678900";
		anuncianteBean.setCPF(cpf);
		assertEquals(cpf, anuncianteBean.getCPF());
	}

	@Test
	void testGetAndSetAnuncios() throws URISyntaxException, MalformedURLException {
		AnuncianteBean anuncianteBean = new AnuncianteBean();
		ArrayList<AnuncioBean> anuncioList = new ArrayList<AnuncioBean>();

		anuncioList.add(new AnuncioBean(
				new ProdutoBean("123", "Produto 1", "Produto 1", 10.5, "SP"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));
		anuncioList.add(new AnuncioBean(
				new ProdutoBean("456", "Produto 2", "Produto 2", 2.1, "PE"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));

		anuncianteBean.setAnuncios(anuncioList);

		assertEquals(anuncioList, anuncianteBean.getAnuncios());
	}

	@Test
	void testDefaltConstructor() {
		AnuncianteBean anuncianteBean = new AnuncianteBean();

		assertAll("Verificação de atributos inicializados", () -> assertEquals(new String(), anuncianteBean.getNome()),
				() -> assertEquals(new String(), anuncianteBean.getCPF()),
				() -> assertInstanceOf(ArrayList.class, anuncianteBean.getAnuncios()));
	}

	@Test
	void testConstructorWithParameters() throws MalformedURLException, URISyntaxException {
		ArrayList<AnuncioBean> anuncioList = new ArrayList<AnuncioBean>();
		String nome = "Nome Teste 123";
		String cpf = "12345678900";

		anuncioList.add(new AnuncioBean(
				new ProdutoBean("123", "Produto 1", "Produto 1", 10.5, "SP"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));
		anuncioList.add(new AnuncioBean(
				new ProdutoBean("456", "Produto 2", "Produto 2", 2.1, "PE"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));

		AnuncianteBean anuncianteBean = new AnuncianteBean(nome, cpf, anuncioList);

		assertAll("Verificação de atributos inicializados", () -> assertEquals(nome, anuncianteBean.getNome()),
				() -> assertEquals(cpf, anuncianteBean.getCPF()),
				() -> assertEquals(anuncioList, anuncianteBean.getAnuncios()));
	}

	@Test
	void testAddAnuncio() throws MalformedURLException, URISyntaxException {
		ArrayList<AnuncioBean> anuncioList = new ArrayList<AnuncioBean>();
		String nome = "Nome Teste 123";
		String cpf = "12345678900";

		anuncioList.add(new AnuncioBean(
				new ProdutoBean("123", "Produto 1", "Produto 1", 10.5, "SP"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));

		AnuncianteBean anuncianteBean = new AnuncianteBean(nome, cpf, anuncioList);

		AnuncioBean addAnuncio = new AnuncioBean(
				new ProdutoBean("456", "Produto 2", "Produto 2", 2.1, "PE"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0));

		anuncianteBean.addAnuncio(addAnuncio);
		anuncioList.add(addAnuncio);

		assertEquals(anuncioList, anuncianteBean.getAnuncios());
	}

	@Test
	void testRemoveAnuncio() throws MalformedURLException, URISyntaxException {
		ArrayList<AnuncioBean> anuncioList = new ArrayList<AnuncioBean>();
		String nome = "Nome Teste 123";
		String cpf = "12345678900";

		anuncioList.add(new AnuncioBean(
				new ProdutoBean("123", "Produto 1", "Produto 1", 10.5, "SP"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));
		anuncioList.add(new AnuncioBean(
				new ProdutoBean("456", "Produto 2", "Produto 2", 2.1, "PE"), new ArrayList<URL>(Arrays
						.asList(new URI("https://www.unicamp.br").toURL(), new URI("https://www.google.com").toURL())),
				Double.valueOf(0)));

		AnuncianteBean anuncianteBean = new AnuncianteBean(nome, cpf, anuncioList);

		anuncianteBean.removeAnuncio(0);
		anuncioList.remove(0);

		assertEquals(anuncioList, anuncianteBean.getAnuncios());
	}

	@Test
	void testValorMedioAnuncios() throws MalformedURLException, URISyntaxException {
		ArrayList<AnuncioBean> anuncioList = new ArrayList<AnuncioBean>();
		String nome = "Nome Teste 123";
		String cpf = "12345678900";
		List<Double> valores = new ArrayList<Double>(
				List.of(Double.valueOf(10.5), Double.valueOf(72.8), Double.valueOf(160), Double.valueOf(7.2)));
		List<Double> descontos = new ArrayList<Double>(
				List.of(Double.valueOf(0), Double.valueOf(1), Double.valueOf(0.5), Double.valueOf(0.2)));
		List<Double> valoresComDesconto = new ArrayList<Double>();

		for (Integer i = 0; i < valores.size(); i++) {
			AnuncioBean anuncioBean = new AnuncioBean(
					new ProdutoBean(i.toString(), "Produto " + i, "Produto " + i, valores.get(i), "SP"),
					new ArrayList<URL>(Arrays.asList(new URI("https://www.unicamp.br").toURL(),
							new URI("https://www.google.com").toURL())),
					descontos.get(i));
			anuncioList.add(anuncioBean);
			valoresComDesconto.add(anuncioBean.getValor());
		}

		AnuncianteBean anuncianteBean = new AnuncianteBean(nome, cpf, anuncioList);

		assertEquals(Double.valueOf(valoresComDesconto.stream().mapToDouble(a -> a).average().getAsDouble()),
				anuncianteBean.valorMedioAnuncios());
	}

}
