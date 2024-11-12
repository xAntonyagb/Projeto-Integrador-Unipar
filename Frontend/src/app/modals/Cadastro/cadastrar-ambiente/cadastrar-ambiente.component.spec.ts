import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarAmbienteComponent } from './cadastrar-ambiente.component';

describe('CadastrarAmbienteComponent', () => {
  let component: CadastrarAmbienteComponent;
  let fixture: ComponentFixture<CadastrarAmbienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastrarAmbienteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastrarAmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
