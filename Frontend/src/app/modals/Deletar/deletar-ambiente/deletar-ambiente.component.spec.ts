import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeletarAmbienteComponent } from './deletar-ambiente.component';

describe('DeletarAmbienteComponent', () => {
  let component: DeletarAmbienteComponent;
  let fixture: ComponentFixture<DeletarAmbienteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DeletarAmbienteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeletarAmbienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
