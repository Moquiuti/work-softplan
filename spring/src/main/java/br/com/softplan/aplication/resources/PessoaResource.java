/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softplan.aplication.resources;

import br.com.softplan.aplication.as.PessoaAS;
import br.com.softplan.aplication.entidades.Pessoa;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author moquiuti
 */
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping({"/"})
public class PessoaResource {

    @Autowired
    private PessoaAS pessoaService;

    @GetMapping(path = {"/pessoa/query"}, produces = "application/json")
    public ResponseEntity<ResponseEntity<List<Pessoa>>> findQuery(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nascimento,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findQuery(nome, cpf, nascimento, email, page, perPage));
    }

    @GetMapping(path = {"/pessoa"}, produces = "application/json")
    public ResponseEntity<ResponseEntity<List<Pessoa>>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.findAll());
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.POST)
    public ResponseEntity<Pessoa> create(@RequestBody Pessoa pessoa) {
        return pessoaService.create(pessoa);
    }

    @GetMapping(path = {"/pessoa/{id}"}, produces = "application/json")
    public ResponseEntity<Optional<Pessoa>> findOne(@PathVariable("id") Long id) {
        return pessoaService.findId(id);
    }

    @RequestMapping(value = "/pessoa", method = RequestMethod.PUT)
    public ResponseEntity<Pessoa> update(@RequestBody Pessoa pessoa) {
        return pessoaService.update(pessoa);
    }

    @DeleteMapping(path = {"/pessoa/{id}"}, produces = "application/json")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        return pessoaService.delete(id);
    }
}
