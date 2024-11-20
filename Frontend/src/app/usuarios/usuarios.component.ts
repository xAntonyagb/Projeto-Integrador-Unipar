import {Component, OnInit} from '@angular/core';
import {UsuarioResponse} from "../dtos/responses/usuario.response";
import {UsuarioRequest} from "../dtos/requests/usuario.request";
import {EMPTY, Observable, of} from "rxjs";
import {catchError, tap} from "rxjs/operators";

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.scss']
})
export class UsuariosComponent implements OnInit {
  isModalOpen = false;
  usuarios: UsuarioResponse[] = [];


  constructor(
    private usuarioRequest: UsuarioRequest
  ) {}

  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.isModalOpen = false;
  }
  ngOnInit(): void {
    this.carregarUsuarios()
  }
  carregarUsuarios(): void {
    this.usuarioRequest.getUsuario().subscribe((response) => {
      console.log(response);
      this.usuarios = response;
    });
  }
}
