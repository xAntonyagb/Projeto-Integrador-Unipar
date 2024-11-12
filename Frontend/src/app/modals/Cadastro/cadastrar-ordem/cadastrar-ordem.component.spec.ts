import { ComponentFixture, TestBed } from '@angular/core/testing';
import { CadastrarOrdemComponent } from './cadastrar-ordem.component';


describe('CadastrarOrdemComponent', () => {
  let component: CadastrarOrdemComponent;
  let fixture: ComponentFixture<CadastrarOrdemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CadastrarOrdemComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CadastrarOrdemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
