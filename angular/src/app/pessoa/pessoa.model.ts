export class Pessoa {
  id: number;
  nome: string;
  email: string;
  imagem: Imagem;
  cpf: string;
  nascimento: Date;
  ativo: boolean;
}

export class Imagem {
  id: number;
  dataCadastro: Date;
  titulo: string;
  tamanho: string;
  base64: string;
}
