import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PessoaComponent} from './pessoa/pessoa.component';
import {CadastroComponent} from './pessoa/cadastro/cadastro.component';


const routes: Routes = [
  {
    path: '',
    component: PessoaComponent
  },
  {
    path: 'pessoa',
    component: PessoaComponent
  },
  {
    path: 'pessoa/:id',
    component: CadastroComponent
  },
  {
    path: 'cadastro',
    component: CadastroComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
