package br.com.fiap.buy.it.config;

import br.com.fiap.buy.it.model.*;
import br.com.fiap.buy.it.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ContatoRepository contatoRepository;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CotacaoRepository cotacaoRepository;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private HistoricoRepository historicoRepository;

    @Override
    public void run(String... args) throws Exception {

        // Populando Usuários
        Usuario usuario1 = new Usuario();
        usuario1.setNome("One Servicos Administrativos LTDA.");
        usuario1.setEmail("comercial@oneservicos.com.br");
        usuario1.setSenha(passwordEncoder.encode("oneserv123"));
        usuario1.setCnpj("28434667000111");
        usuario1.setIsFornecedor(false);
        usuario1.setUrlImagem(null);

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Kalunga Comercio e Industria Grafica LTDA.");
        usuario2.setEmail("vendas@kalunga.com.br");
        usuario2.setSenha(passwordEncoder.encode("kalung4"));
        usuario2.setCnpj("43283811000150");
        usuario2.setIsFornecedor(true);
        usuario2.setUrlImagem("https://iguatemi.com.br/brasilia/sites/brasilia/files/2020-01/Kalunga_logo.png");

        Usuario usuario3 = new Usuario();
        usuario3.setNome("Kabum S.A.");
        usuario3.setEmail("adm@kabum.com.br");
        usuario3.setSenha(passwordEncoder.encode("kabuuuuum"));
        usuario3.setCnpj("05570714000159");
        usuario3.setIsFornecedor(true);
        usuario3.setUrlImagem(null);

        Usuario usuario4 = new Usuario();
        usuario4.setNome("Kuara Capital Gestora de Recursos LTDA.");
        usuario4.setEmail("operacional@kuaracapital.com");
        usuario4.setSenha(passwordEncoder.encode("farialima"));
        usuario4.setCnpj("41179663000100");
        usuario4.setIsFornecedor(false);
        usuario4.setUrlImagem(null);

        Usuario usuario5 = new Usuario();
        usuario5.setNome("Magazine Luiza S.A.");
        usuario5.setEmail("magalu@magalu.com.br");
        usuario5.setSenha(passwordEncoder.encode("vempromagalu"));
        usuario5.setCnpj("47960950000121");
        usuario5.setIsFornecedor(true);
        usuario5.setUrlImagem("https://logodownload.org/wp-content/uploads/2014/06/magalu-logo-0.png");        

        // Populando Contatos
        Contato contato1 = new Contato();
        contato1.setTipo("Email");
        contato1.setValor("kaue@oneservicos.com.br");
        contato1.setUsuario(usuario1);

        Contato contato2 = new Contato();
        contato2.setTipo("Email");
        contato2.setValor("vendas@kalunga.com.br");
        contato2.setUsuario(usuario2);

        Contato contato3 = new Contato();
        contato3.setTipo("Telefone");
        contato3.setValor("(11) 3200-0000");
        contato3.setUsuario(usuario2);

        Contato contato4 = new Contato();
        contato4.setTipo("Telefone");
        contato4.setValor("(11) 98282-0000");
        contato4.setUsuario(usuario2);

        Contato contato5 = new Contato();
        contato5.setTipo("Whatsapp");
        contato5.setValor("(11) 98282-0000");
        contato5.setUsuario(usuario2);

        // Populando Tags
        Tag tag1 = new Tag();
        tag1.setNome("Perifericos");

        Tag tag2 = new Tag();
        tag2.setNome("Calcas");

        Tag tag3 = new Tag();
        tag3.setNome("Eletrodomesticos");

        Tag tag4 = new Tag();
        tag4.setNome("Celulares");

        Tag tag5 = new Tag();
        tag5.setNome("Água");

        // Populando Departamentos
        Departamento departamento1 = new Departamento();
        departamento1.setNome("Informatica");
        departamento1.setIcone("icone-informatica");

        Departamento departamento2 = new Departamento();
        departamento2.setNome("Eletronicos");
        departamento2.setIcone("icone-eletronicos");

        Departamento departamento3 = new Departamento();
        departamento3.setNome("Vestuario");
        departamento3.setIcone("icone-vestuario");

        Departamento departamento4 = new Departamento();
        departamento4.setNome("Bebidas");
        departamento4.setIcone("icone-bebidas");

        Departamento departamento5 = new Departamento();
        departamento5.setNome("Cozinha");
        departamento5.setIcone(null);

        // Populando Produtos
        Produto produto1 = new Produto();
        produto1.setDepartamento(departamento1);
        produto1.setNome("Mouse");
        produto1.setMarca("Logitech");
        produto1.setCor("Preto");
        produto1.setTamanho(null);
        produto1.setMaterial(null);
        produto1.setObservacao(null);

        Produto produto2 = new Produto();
        produto2.setDepartamento(departamento4);
        produto2.setNome("Agua");
        produto2.setMarca("Lindoia");
        produto2.setCor(null);
        produto2.setTamanho("1 Litro");
        produto2.setMaterial(null);
        produto2.setObservacao(null);

        Produto produto3 = new Produto();
        produto3.setDepartamento(departamento2);
        produto3.setNome("Celular");
        produto3.setMarca("Apple");
        produto3.setCor("Vermelho");
        produto3.setTamanho(null);
        produto3.setMaterial(null);
        produto3.setObservacao(null);

        Produto produto4 = new Produto();
        produto4.setDepartamento(departamento3);
        produto4.setNome("Calça");
        produto4.setMarca("Hering");
        produto4.setCor("Vermelho");
        produto4.setTamanho("P");
        produto4.setMaterial("Jeans");
        produto4.setObservacao("Modelo XYZ");

        Produto produto5 = new Produto();
        produto5.setDepartamento(departamento5);
        produto5.setNome("Geladeira");
        produto5.setMarca(null);
        produto5.setCor(null);
        produto5.setTamanho(null);
        produto5.setMaterial(null);
        produto5.setObservacao(null);

        // Populando Status
        Status status1 = new Status();
        status1.setNome("Em Andamento");

        Status status2 = new Status();
        status2.setNome("Recusado");

        Status status3 = new Status();
        status3.setNome("Aprovado");

        Status status4 = new Status();
        status4.setNome("Fechado");

        Status status5 = new Status();
        status5.setNome("Concluído");

        // Populando Cotações
        Cotacao cotacao1 = new Cotacao();
        cotacao1.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-19"));
        cotacao1.setComprador(usuario1);
        cotacao1.setProduto(produto1);
        cotacao1.setQuantidadeProduto(new BigDecimal("20"));
        cotacao1.setValorProduto(new BigDecimal("50.99"));
        cotacao1.setStatus(status1);
        cotacao1.setPrioridadeEntrega(3L);
        cotacao1.setPrioridadeQualidade(1L);
        cotacao1.setPrioridadePreco(2L);
        cotacao1.setPrazo(10L);
        cotacao1.setDataFechamento(null);

        Cotacao cotacao2 = new Cotacao();
        cotacao2.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-18"));
        cotacao2.setComprador(usuario1);
        cotacao2.setProduto(produto1);
        cotacao2.setQuantidadeProduto(new BigDecimal("69.90"));
        cotacao2.setValorProduto(new BigDecimal("50.99"));
        cotacao2.setStatus(status5);
        cotacao2.setPrioridadeEntrega(2L);
        cotacao2.setPrioridadeQualidade(1L);
        cotacao2.setPrioridadePreco(3L);
        cotacao2.setPrazo(5L);
        cotacao2.setDataFechamento(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-18"));

        Cotacao cotacao3 = new Cotacao();
        cotacao3.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-17"));
        cotacao3.setComprador(usuario4);
        cotacao3.setProduto(produto3);
        cotacao3.setQuantidadeProduto(new BigDecimal("5"));
        cotacao3.setValorProduto(new BigDecimal("1500"));
        cotacao3.setStatus(status1);
        cotacao3.setPrioridadeEntrega(1L);
        cotacao3.setPrioridadeQualidade(2L);
        cotacao3.setPrioridadePreco(3L);
        cotacao3.setPrazo(2L);
        cotacao3.setDataFechamento(null);

        Cotacao cotacao4 = new Cotacao();
        cotacao4.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-16"));
        cotacao4.setComprador(usuario4);
        cotacao4.setProduto(produto2);
        cotacao4.setQuantidadeProduto(new BigDecimal("100"));
        cotacao4.setValorProduto(new BigDecimal("2"));
        cotacao4.setStatus(status1);
        cotacao4.setPrioridadeEntrega(2L);
        cotacao4.setPrioridadeQualidade(1L);
        cotacao4.setPrioridadePreco(3L);
        cotacao4.setPrazo(1L);
        cotacao4.setDataFechamento(null);

        Cotacao cotacao5 = new Cotacao();
        cotacao5.setDataAbertura(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-15"));
        cotacao5.setComprador(usuario1);
        cotacao5.setProduto(produto5);
        cotacao5.setQuantidadeProduto(new BigDecimal("2"));
        cotacao5.setValorProduto(new BigDecimal("3.50"));
        cotacao5.setStatus(status5);
        cotacao5.setPrioridadeEntrega(3L);
        cotacao5.setPrioridadeQualidade(1L);
        cotacao5.setPrioridadePreco(2L);
        cotacao5.setPrazo(7L);
        cotacao5.setDataFechamento(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-16"));

        // Populando Avaliações
        Avaliacao avaliacao1 = new Avaliacao();
        avaliacao1.setCotacao(cotacao2);
        avaliacao1.setData(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-19"));
        avaliacao1.setNotaEntrega(5L);
        avaliacao1.setNotaQualidade(3L);
        avaliacao1.setNotaPreco(3L);
        avaliacao1.setDescricao("Muito Bom!");

        Avaliacao avaliacao2 = new Avaliacao();
        avaliacao2.setCotacao(cotacao5);
        avaliacao2.setData(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-17"));
        avaliacao2.setNotaEntrega(3L);
        avaliacao2.setNotaQualidade(4L);
        avaliacao2.setNotaPreco(2L);
        avaliacao2.setDescricao(null);

        // Populando Históricos
        Historico historico1 = new Historico();
        historico1.setCotacao(cotacao2);
        historico1.setFornecedor(usuario2);
        historico1.setStatus(status2);
        historico1.setRecusadoPorProduto(false);
        historico1.setRecusadoPorQuantidade(true);
        historico1.setRecusadoPorPreco(false);
        historico1.setRecusadoPorPrazo(false);
        historico1.setDescricao("Não tenho a quantidade no momento.");
        historico1.setData(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-18"));
        historico1.setValorOfertado(null);
        
        Historico historico2 = new Historico();
        historico2.setCotacao(cotacao2);
        historico2.setFornecedor(usuario3);
        historico2.setStatus(status2);
        historico2.setRecusadoPorProduto(true);
        historico2.setRecusadoPorQuantidade(false);
        historico2.setRecusadoPorPreco(false);
        historico2.setRecusadoPorPrazo(false);
        historico2.setDescricao("Não trabalho com este produto.");
        historico2.setData(new SimpleDateFormat("yyyy-MM-dd").parse("2024-03-18"));
        historico2.setValorOfertado(null);

        // Salvando Listas no Banco H2

        List<Contato> contatos = List.of(contato1, contato2, contato3, contato4, contato5);
        contatoRepository.saveAll(contatos);
        
        List<Historico> historicos = List.of(historico1, historico2);
        historicoRepository.saveAll(historicos);

        List<Avaliacao> avaliacoes = List.of(avaliacao1, avaliacao2);
        avaliacaoRepository.saveAll(avaliacoes);

        List<Tag> tags = List.of(tag1, tag2, tag3, tag4, tag5);
        tagRepository.saveAll(tags);

        List<Cotacao> cotacoes = List.of(cotacao1, cotacao2, cotacao3, cotacao4, cotacao5);
        cotacaoRepository.saveAll(cotacoes);

        List<Usuario> usuarios = List.of(usuario1, usuario2, usuario3, usuario4, usuario5);
        usuarioRepository.saveAll(usuarios);

        List<Produto> produtos = List.of(produto1, produto2, produto3, produto4, produto5);
        produtoRepository.saveAll(produtos);

        List<Departamento> departamentos = List.of(departamento1, departamento2, departamento3, departamento4, departamento5);
        departamentoRepository.saveAll(departamentos);

        List<Status> status = List.of(status1, status2, status3, status4, status5);
        statusRepository.saveAll(status);

    }
}
