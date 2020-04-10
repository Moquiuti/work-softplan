/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softplan.aplication.as;

import br.com.softplan.aplication.entidades.Pessoa;
import br.com.softplan.aplication.repostory.PessoaRepository;
import br.com.softplan.aplication.util.ImagemUtil;
import br.com.softplan.aplication.util.Removedor;
import br.com.softplan.aplication.util.Validador;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author moquiuti
 */
@Service
public class PessoaAS {

    private final PessoaRepository repository;

    public PessoaAS(PessoaRepository repository) {
        this.repository = repository;
    }

    public ResponseEntity create(Pessoa pessoa) {
        if (pessoa.getNome().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o nome!");
        }
        if (pessoa.getNome().length() > 150) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o nome com no máximo 150 caracteres!");
        }
        if (pessoa.getCpf() != null) {
            if (Validador.isCpfValid(pessoa.getCpf())) {
                pessoa.setCpf(Removedor.removeCaracteresEspeciais(pessoa.getCpf()));
                Pessoa pes = repository.findCpf(pessoa.getCpf());
                if (pes != null) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CPF já cadastrado!");
                }
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CPF inválido!");
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("CPF Obrigatório!");
        }
        if (pessoa.getEmail().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o e-mail!");
        }
        if (!Validador.isValidEmailAddressRegex(pessoa.getEmail())) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe um e-mail válido!");
        }
        if (pessoa.getEmail().length() > 400) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe um e-mail com no máximo 400 caracteres!");
        }
        if (pessoa.getImagem() != null) {
            try {
                pessoa.getImagem().setBase64(ImagemUtil.redimensionarLarge(pessoa.getImagem().getBase64()));
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(ImagemUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        pessoa.setAtivo(Boolean.TRUE);
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(pessoa));
    }

    public ResponseEntity update(Pessoa pessoa) {
        if (pessoa.getId() != null) {
            if (pessoa.getNome().isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o nome!");
            }
            if (pessoa.getNome().length() > 150) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o nome com no máximo 150 caracteres!");
            }
            if (pessoa.getEmail().isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe o e-mail!");
            }
            if (!Validador.isValidEmailAddressRegex(pessoa.getEmail())) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe um e-mail válido!");
            }
            if (pessoa.getEmail().length() > 400) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Informe um e-mail com no máximo 400 caracteres!");
            }
            if (pessoa.getImagem() != null) {
                try {
                    pessoa.getImagem().setBase64(ImagemUtil.redimensionarLarge(pessoa.getImagem().getBase64()));
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(ImagemUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            return create(pessoa);
        }
        pessoa.setCpf(Removedor.removeCaracteresEspeciais(pessoa.getCpf()));
        return ResponseEntity.status(HttpStatus.OK).body(repository.save(pessoa));
    }

    public ResponseEntity delete(Long id) {
        Pessoa pessoa = (Pessoa) repository.findId(id);
        pessoa.setAtivo(Boolean.FALSE);
        repository.save(pessoa);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findAll());
    }

    public ResponseEntity<Optional<Pessoa>> findId(Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(repository.findById(id));
    }

    public ResponseEntity<List<Pessoa>> findQuery(String nome, String cpf, String nascimento, String email, Integer page, Integer perPage) {
        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("nome", contains().contains())
                .withMatcher("cpf", contains().exact())
                .withMatcher("email", contains().exact())
                .withMatcher("nascimento", contains().exact())
                .withMatcher("ativo", contains().exact());
        if (cpf != null) {
            cpf = Removedor.removeCaracteresEspeciais(cpf);
        }

        Date dataNascimento = null;
        if (nascimento != null) {
            ZoneId defaultZoneId = ZoneId.systemDefault();
            LocalDate localDate = LocalDate.parse(nascimento);
            dataNascimento = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
        }

        Pessoa pessoa = Pessoa.builder().nome(nome).cpf(cpf).nascimento(dataNascimento).email(email).ativo(Boolean.TRUE).build();
        PageRequest paging = PageRequest.of(page - 1, perPage, Sort.by(Sort.Direction.fromString("asc"), "nome"));
        Page<Pessoa> result = this.repository.findAll(Example.of(pessoa, matcher), paging);
        if (result.hasContent()) {
            return ResponseEntity.status(HttpStatus.OK).body(result.getContent());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
