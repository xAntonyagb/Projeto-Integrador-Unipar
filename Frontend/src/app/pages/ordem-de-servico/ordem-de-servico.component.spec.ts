import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdemDeServicoComponent } from './ordem-de-servico.component';

describe('OrdemDeServicoComponent', () => {
  let component: OrdemDeServicoComponent;
  let fixture: ComponentFixture<OrdemDeServicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [OrdemDeServicoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(OrdemDeServicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
