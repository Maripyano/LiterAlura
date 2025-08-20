package com.marianey.literAlura;

import com.seuprimeironome.literAlura.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Scanner;
import com.seuprimeironome.literAlura.service.ConsumoApi;
import com.seuprimeironome.literAlura.dto.ResultadoBuscaDTO;
import com.seuprimeironome.literAlura.model.Livro;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LivroRepository repositorio;
	private ConsumoApi consumoApi = new ConsumoApi();
	private ObjectMapper objectMapper = new ObjectMapper();
	private static final String URL_API = "https://gutendex.com/books/";

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcao = -1;

		while (opcao != 0) {
			System.out.println("\n--- Catálogo de Livros LiterAlura ---");
			System.out.println("1. Buscar livro na API e salvar");
			System.out.println("2. Listar livros salvos");
			System.out.println("0. Sair");
			System.out.print("Escolha uma opção: ");
			opcao = scanner.nextInt();
			scanner.nextLine(); // Limpar o buffer

			switch (opcao) {
				case 1:
					buscarLivro(scanner);
					break;
				case 2:
					listarLivros();
					break;
				case 0:
					System.out.println("Saindo...");
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}

	private void buscarLivro(Scanner scanner) {
		System.out.print("Digite o nome do livro: ");
		String nomeLivro = scanner.nextLine();
		String endereco = URL_API + "?search=" + nomeLivro.replace(" ", "%20");

		System.out.println("Buscando na API...");
		String json = consumoApi.obterDados(endereco);

		if (json.isEmpty()) {
			System.out.println("Nenhum livro encontrado para o título: " + nomeLivro);
			return;
		}

		try {
			ResultadoBuscaDTO resultado = objectMapper.readValue(json, ResultadoBuscaDTO.class);
			if (resultado.results.isEmpty()) {
				System.out.println("Nenhum livro encontrado na API.");
				return;
			}
	}
		DadosLivroDTO dadosLivro = resultado.results.get(0);


		Livro novoLivro = new Livro(
				dadosLivro.title,
				dadosLivro.authors.get(0).name,
				dadosLivro.download_count
		);
		repositorio.save(novoLivro);
		System.out.println("Livro salvo com sucesso: " + novoLivro);

	} catch (IOException e) {
		System.err.println("Erro ao processar o JSON: " + e.getMessage());
	}
}

	private void listarLivros() {
		System.out.println("Listando livros salvos:");
		repositorio.findAll().forEach(System.out::println);
	}
}