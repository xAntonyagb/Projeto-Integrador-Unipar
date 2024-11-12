import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ServicosArquivadosComponent } from './servicos-arquivados.component';

describe('ServicosArquivadosComponent', () => {
  let component: ServicosArquivadosComponent;
  let fixture: ComponentFixture<ServicosArquivadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ServicosArquivadosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ServicosArquivadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
