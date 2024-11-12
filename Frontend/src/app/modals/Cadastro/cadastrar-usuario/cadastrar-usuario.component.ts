import { Component, EventEmitter, Output } from '@angular/core';
import { UsuarioRequest } from '../../../dtos/requests/usuario.request';
import { AddUsuario, UsuarioPermissoes, UsuarioResponse } from '../../../dtos/responses/usuario.response';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.scss']
})
export class CadastrarUsuarioComponent {

  username: string = '';
  senha :string = ''
  permissoes :UsuarioPermissoes[] = [];
  usuarioResponse : UsuarioResponse[] = [];;          

  constructor(private usuario: UsuarioRequest) { 
    this.loadUsuarios();
  }
  @Output() usuarioAdicionado = new EventEmitter<void>();
  onSubmit( event: Event): void {
    
  }
  loadUsuarios() {
    this.usuario.getUsuario().subscribe((data: any[]) => {
      this.usuarioResponse = data;
    });
  }

  adicionarUsuario() {
    const usuario: AddUsuario = { 
      username: this.username,
      password:  this.senha,
      permissoes: this.permissoes
    };
    this.usuario.setUsuario(usuario).subscribe(() => {
      this.usuarioAdicionado.emit();
    });
  }
  @Output() close = new EventEmitter<void>();
  closeModal() {
    this.close.emit();
  }
}

