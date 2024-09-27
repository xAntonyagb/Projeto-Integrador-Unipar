import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabOrdemDeServicoComponent } from './tab-ordem-de-servico.component';

describe('TabOrdemDeServicoComponent', () => {
  let component: TabOrdemDeServicoComponent;
  let fixture: ComponentFixture<TabOrdemDeServicoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TabOrdemDeServicoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TabOrdemDeServicoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
