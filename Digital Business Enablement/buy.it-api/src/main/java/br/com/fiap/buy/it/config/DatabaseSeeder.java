package br.com.fiap.buy.it.config;

import br.com.fiap.buy.it.model.*;
import br.com.fiap.buy.it.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PessoaRepository pessoaRepository;
    @Autowired
    private PessoaJuridicaRepository pessoaJuridicaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private TipoContatoRepository tipoContatoRepository;
    @Autowired
    private FormaContatoRepository formaContatoRepository;


    @Override
    public void run(String... args) throws Exception {
        List<Status> statusList = Arrays.asList(
                new Status(null, "Em Andamento"),
                new Status(null, "Recusado"),
                new Status(null, "Aprovado"),
                new Status(null, "Fechado"),
                new Status(null, "Concluído")
        );
        statusRepository.saveAll(statusList);

        List<Pessoa> pessoaList = Arrays.asList(
                new Pessoa(null, "One Serviços Administrativos LTDA.", "url_imagem_1"),
                new Pessoa(null, "Kabum S.A.", "url_imagem_2"),
                new Pessoa(null, "Kuará Capital Gestora de Recursos LTDA.", "url_imagem_3"),
                new Pessoa(null, "Magazine Luiza S.A.", "url_imagem_4"),
                new Pessoa(null, "Kalunga Comércio e Indústria Gráfica LTDA.", "url_imagem_4")
        );
        pessoaRepository.saveAll(pessoaList);

        /*List<PessoaJuridica> pessoaJuridicaList = Arrays.asList(
                new PessoaJuridica(false, "28.434.667/0001-11"),
                new PessoaJuridica(true, "43.283.811/0001-50"),
                new PessoaJuridica(true, "05.570.714/0001-59"),
                new PessoaJuridica(false, "41.179.663/0001-00"),
                new PessoaJuridica(true, "47.960.950/0001-21")
        );
        pessoaJuridicaRepository.saveAll(pessoaJuridicaList);*/

        List<Produto> produtoList = Arrays.asList(
                new Produto(1L, "Mouse", "Logitech", "Preto", null, null, null, null, null),
                new Produto(2L, "Água", "Lindóia", null, "1 Litro", null, null, null, null),
                new Produto(3L, "Celular", "Apple", "Vermelho", null, null, null, null, null),
                new Produto(4L, "Calça", "Hering", "Vermelho", "P", "Jeans", "Modelo XYZ", null, null),
                new Produto(5L, "Geladeira", null, null, null, null, null, null, null)
        );
        produtoRepository.saveAll(produtoList);

        List<TipoContato> tipoContatoList = Arrays.asList(
                new TipoContato(1L, "Email"),
                new TipoContato(2L, "Telefone"),
                new TipoContato(3L, "Celular"),
                new TipoContato(4L, "Whatsapp"),
                new TipoContato(5L, "Fax")
        );
        tipoContatoRepository.saveAll(tipoContatoList);

        /*List<FormaContato> formaContatoList = Arrays.asList(
                new FormaContato(null, tipoContatoRepository.findById(1L).orElseThrow(() -> new RuntimeException("TipoContato com ID 1 não encontrado")), "kaue@oneservicos.com.br", pessoaRepository.findById(1L).orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada"))),
                new FormaContato(null, tipoContatoRepository.findById(1L).orElseThrow(() -> new RuntimeException("TipoContato com ID 1 não encontrado")), "vendas@kalunga.com.br", pessoaRepository.findById(2L).orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada"))),
                new FormaContato(null, tipoContatoRepository.findById(1L).orElseThrow(() -> new RuntimeException("TipoContato com ID 1 não encontrado")), "(11) 3200-0000", pessoaRepository.findById(3L).orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada"))),
                new FormaContato(null, tipoContatoRepository.findById(1L).orElseThrow(() -> new RuntimeException("TipoContato com ID 1 não encontrado")), "(11) 98282-0000", pessoaRepository.findById(4L).orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada"))),
                new FormaContato(null, tipoContatoRepository.findById(1L).orElseThrow(() -> new RuntimeException("TipoContato com ID 1 não encontrado")), "(11) 98585-0000", pessoaRepository.findById(5L).orElseThrow(() -> new RuntimeException("Pessoa com ID 1 não encontrada")))
        );

        formaContatoRepository.saveAll(formaContatoList);*/
    }

}

