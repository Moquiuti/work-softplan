import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { PessoaComponent } from './pessoa/pessoa.component';
import {RouterModule} from '@angular/router';
import {PessoaService} from './core/service/pessoa.service';
import {HttpClientModule} from '@angular/common/http';
import { CadastroComponent } from './pessoa/cadastro/cadastro.component';
import {FormsModule} from '@angular/forms';
import {IMaskModule} from 'angular-imask';
import {CustomCpfPipe, CustomDatePipe} from './core/pipe/custom.pipe';

@NgModule({
  declarations: [
    AppComponent,
    PessoaComponent,
    CadastroComponent,
    CustomDatePipe,
    CustomCpfPipe
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NoopAnimationsModule,
    HttpClientModule,
    FormsModule,
    IMaskModule
  ],
  exports: [RouterModule],
  providers: [PessoaService],
  bootstrap: [AppComponent]
})
export class AppModule { }
