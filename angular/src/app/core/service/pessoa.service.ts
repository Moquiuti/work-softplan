import {HttpClient, HttpParams} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {Pessoa} from '../../pessoa/pessoa.model';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {

  id: number;

  constructor(private http: HttpClient) {
  }

  cadastrar(pessoa: Pessoa): Observable<any> {
    return this.http.post<any>(`${environment.app_local}/pessoa`, pessoa);
  }

  buscarPorId(id: number): Observable<any> {
    return this.http.get<any>(`${environment.app_local}/pessoa/${id}`);
  }

  atualizar(pessoa: Pessoa): Observable<any> {
    return this.http.put<any>(`${environment.app_local}/pessoa`, pessoa);
  }

  remover(id ?: number): Observable<any> {
    return this.http.delete<any>(`${environment.app_local}/pessoa/${id}`);
  }

  byQuery(nome ?: string, cpf ?: string, nascimento ?: string, email ?: string, page ?: string, perPage ?: string): Observable<any> {
    let httpParams = new HttpParams();

    if (page) {
      httpParams = httpParams.append('page', page);
    }

    if (perPage) {
      httpParams = httpParams.append('perPage', perPage);
    }

    if (nome) {
      httpParams = httpParams.append('nome', nome);
    }

    if (cpf) {
      httpParams = httpParams.append('cpf', cpf);
    }

    if (nascimento) {
      httpParams = httpParams.append('nascimento', nascimento.toString());
    }

    if (email) {
      httpParams = httpParams.append('email', email);
    }
    return this.http.get<any>(`${environment.app_local}pessoa/query`, {params: httpParams});
  }

}
