import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PesquisarPatrimonioComponent } from './pesquisar-patrimonio.component';

describe('PesquisarPatrimonioComponent', () => {
  let component: PesquisarPatrimonioComponent;
  let fixture: ComponentFixture<PesquisarPatrimonioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PesquisarPatrimonioComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(PesquisarPatrimonioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
