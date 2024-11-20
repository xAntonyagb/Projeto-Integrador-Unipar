import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastrarPatrimonioComponent } from './cadastrar-patrimonio.component';

describe('CadastrarPatrimonioComponent', () => {
  let component: CadastrarPatrimonioComponent;
  let fixture: ComponentFixture<CadastrarPatrimonioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastrarPatrimonioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastrarPatrimonioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
