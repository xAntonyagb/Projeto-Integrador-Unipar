import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarBlocoComponent } from './cadastrar-bloco.component';

describe('CadastrarBlocoComponent', () => {
  let component: CadastrarBlocoComponent;
  let fixture: ComponentFixture<CadastrarBlocoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastrarBlocoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(CadastrarBlocoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
