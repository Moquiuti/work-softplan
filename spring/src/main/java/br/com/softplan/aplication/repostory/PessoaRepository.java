/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.softplan.aplication.repostory;

import br.com.softplan.aplication.entidades.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author moquiuti
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa findCpf(@Param("p0") String p0);

    Pessoa findId(@Param("p0") Long p0);

}
