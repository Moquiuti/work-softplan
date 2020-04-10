import {Component, OnInit} from '@angular/core';
import {Imagem, Pessoa} from '../pessoa.model';
import {PessoaService} from '../../core/service/pessoa.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.scss']
})
export class CadastroComponent implements OnInit {
  model: Pessoa;
  imgSubmited: File;

  constructor(private pessoaService: PessoaService,
              public router: Router
  ) {
  }

  ngOnInit() {
    this.model = new Pessoa();
    if (this.pessoaService.id) {
      this.pessoaService.buscarPorId(this.pessoaService.id).subscribe(resp => {
        this.model = resp;
        this.pessoaService.id = undefined;
      });
    } else {
      this.model.imagem = new Imagem();
    }
  }

  onSelectFile(event) {
    if (event.target.files && event.target.files[0]) {
      const reader = new FileReader();
      this.imgSubmited = event.target.files[0];
      reader.readAsDataURL(event.target.files[0]);
      reader.onload = e => {
        const img = new Imagem();
        img.base64 = e.target['result'];
        img.dataCadastro = new Date();
        img.titulo = event.target.files[0].name;
        img.tamanho = event.target.files[0].size;
        console.log(event.target.files[0].type);

        if (parseInt(img.tamanho) < 1000000) {
          if (event.target.files[0].type === 'image/png' ||
            event.target.files[0].type === 'image/bmp' ||
            event.target.files[0].type === 'image/jpeg' ||
            event.target.files[0].type === 'image/jpg') {
            this.model.imagem = img;
          } else {
            this.model.imagem = new Imagem();
          }
        } else {
          this.model.imagem = new Imagem();
        }

        reader.onerror = function(error) {
          console.error(error);
        };
      };
    }
  }

  removerImg(img: Imagem) {
    if (img) {
      this.model.imagem = new Imagem();
    }
  }

  cadastrar() {
    if (this.model.nome && this.model.cpf && this.model.email) {
      if (this.model.id) {
        this.pessoaService.atualizar(this.model).subscribe(resp => {
          this.router.navigate(['/pessoa']);
        });
      } else {
        this.pessoaService.cadastrar(this.model).subscribe(resp => {
          this.router.navigate(['/pessoa']);
        });
      }
    }
  }
}
