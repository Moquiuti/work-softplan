import {Component, OnInit} from '@angular/core';
import {Pessoa} from './pessoa.model';
import {PessoaService} from '../core/service/pessoa.service';

@Component({
  selector: 'app-pessoa',
  templateUrl: './pessoa.component.html',
  styleUrls: ['./pessoa.component.scss']
})
export class PessoaComponent implements OnInit {
  pessoas: Pessoa[];
  nome: string;
  cpf: string;
  email: string;
  nascimento: string;

  constructor(private pessoaService: PessoaService) {
  }

  ngOnInit() {
    this.pessoaService.byQuery().subscribe(resp => {
      console.log(resp);
      this.pessoas = resp.body;
    });
  }

  remover(id: number) {
    this.pessoaService.remover(id).subscribe(remov => {
      this.byQuery();
    });
  }

  editar(id: number) {
    this.pessoaService.id = id;
  }

  byQuery() {
    if (this.nascimento) {
      this.formatData();
      console.log(this.nascimento);
    }
    this.pessoaService.byQuery(this.nome, this.cpf, this.nascimento, this.email).subscribe(resp => {
      this.pessoas = resp.body;
    });
  }

  formatData() {
    this.nascimento = this.nascimento.replace(this.nascimento.substring(4, 5), '/');
    this.nascimento = this.nascimento.replace(this.nascimento.substring(7, 8), '/');
  }
}
