import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ModalRequest{
  private isOpenSubject = new BehaviorSubject<boolean>(false);
  isOpen$ = this.isOpenSubject.asObservable();

  openModal() {
    this.isOpenSubject.next(true);
  }

  closeModal() {
    this.isOpenSubject.next(false);
  }
}