import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabLancamentosComponent } from './tab-lancamentos.component';

describe('TabLancamentosComponent', () => {
  let component: TabLancamentosComponent;
  let fixture: ComponentFixture<TabLancamentosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TabLancamentosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TabLancamentosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
