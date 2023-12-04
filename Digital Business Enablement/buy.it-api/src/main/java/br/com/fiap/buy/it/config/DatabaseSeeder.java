package br.com.fiap.buy.it.config;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.buy.it.model.Departamento;
import br.com.fiap.buy.it.model.Tag;
import br.com.fiap.buy.it.repository.DepartamentoRepository;
import br.com.fiap.buy.it.repository.TagRepository;

import java.util.List;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        Tag tag1 = new Tag();
        tag1.setNome("Tag 1");

        Tag tag2 = new Tag();
        tag2.setNome("Tag 2");

        Tag tag3 = new Tag();
        tag3.setNome("Tag 3");

        tagRepository.saveAll(List.of(tag1, tag2, tag3));

        Departamento departamento1 = new Departamento();
        departamento1.setNome("Departamento 1");
        departamento1.setIcone("icone1.png");

        Departamento departamento2 = new Departamento();
        departamento2.setNome("Departamento 2");
        departamento2.setIcone("icone2.png");

        Departamento departamento3 = new Departamento();
        departamento3.setNome("Departamento 3");
        departamento3.setIcone("icone3.png");

        departamentoRepository.saveAll(List.of(departamento1, departamento2, departamento3));

    }
}
