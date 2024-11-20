import { Component } from '@angular/core';
import {DialogModule} from "primeng/dialog";
import {ButtonModule} from "primeng/button";
import {InputTextModule} from "primeng/inputtext";
import {AvatarModule} from "primeng/avatar";

@Component({
  selector: 'app-deletar-ambiente',
  templateUrl: './deletar-ambiente.component.html',
  styleUrls: ['./deletar-ambiente.component.scss'],
})
export class DeletarAmbienteComponent {
  visible: boolean = false;

  showDialog() {
    this.visible = true;
  }
}
