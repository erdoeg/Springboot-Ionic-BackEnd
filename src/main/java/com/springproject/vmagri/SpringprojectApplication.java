package com.springproject.vmagri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springproject.vmagri.domain.Categoria;
import com.springproject.vmagri.domain.Cidade;
import com.springproject.vmagri.domain.Cliente;
import com.springproject.vmagri.domain.Endereco;
import com.springproject.vmagri.domain.Estado;
import com.springproject.vmagri.domain.Pagamento;
import com.springproject.vmagri.domain.PagamentoBoleto;
import com.springproject.vmagri.domain.PagamentoCartao;
import com.springproject.vmagri.domain.Pedido;
import com.springproject.vmagri.domain.Produto;
import com.springproject.vmagri.domain.enums.StatusPagamento;
import com.springproject.vmagri.domain.enums.TipoCliente;
import com.springproject.vmagri.repositories.CategoriaRepository;
import com.springproject.vmagri.repositories.CidadeRepository;
import com.springproject.vmagri.repositories.ClienteRepository;
import com.springproject.vmagri.repositories.EnderecoRepository;
import com.springproject.vmagri.repositories.EstadoRepository;
import com.springproject.vmagri.repositories.PagamentoRepository;
import com.springproject.vmagri.repositories.PedidoRepository;
import com.springproject.vmagri.repositories.ProdutoRepository;


@SpringBootApplication
public class SpringprojectApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	@Autowired
	private ProdutoRepository produtoRepo;
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	@Autowired
	private CidadeRepository cidadeRepo;
	
	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	@Autowired
	private PedidoRepository pedidoRepo;
	
	@Autowired
	private PagamentoRepository pagamentoRepo;

	public static void main(String[] args) {
		SpringApplication.run(SpringprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "informatica");
		Categoria cat2 = new Categoria(null, "escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		

		// Opcional fazer esse passo
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));		
		
		
		categoriaRepo.saveAll(Arrays.asList(cat1, cat2));
		produtoRepo.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepo.saveAll(Arrays.asList(est1, est2));
		cidadeRepo.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Maria da Silva", "maria.silva@gmail.com", "23456734598", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("44563212", "987654434"));
		
		Cliente cli2 = new Cliente(null, "Amanda Castelli", "castellia@gmail.com", "22344567891", TipoCliente.PESSOAFISICA);
		cli2.getTelefones().addAll(Arrays.asList("76543323", "987675545"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "1456", "Apto 203", "Jardim", "38220084", c1, cli1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 80", "Centro", "387772012", c2, cli1);		
		Endereco e3 = new Endereco(null, "Rua ABC", "554", "apto 88", "Vila Luisa", "09877151", c2, cli2);		
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		cli2.getEnderecos().addAll(Arrays.asList(e3));
		
		clienteRepo.saveAll(Arrays.asList(cli1, cli2));
		enderecoRepo.saveAll(Arrays.asList(e1, e2, e3));
		
		
		DateFormat datePattern = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, datePattern.parse("30/09/2017 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, datePattern.parse(datePattern.format(new Date())), cli1, e2);
		
		Pagamento pag1 = new PagamentoCartao(null, StatusPagamento.EFETUADO, ped1, 6, datePattern.parse(datePattern.format(new Date())));
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, StatusPagamento.PENDENTE, ped2, datePattern.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepo.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepo.saveAll(Arrays.asList(pag1, pag2));

	}
}
