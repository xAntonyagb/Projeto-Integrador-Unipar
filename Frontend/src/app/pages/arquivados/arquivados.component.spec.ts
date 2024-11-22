import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ArquivadosComponent } from './arquivados.component';

describe('ArquivadosComponent', () => {
  let component: ArquivadosComponent;
  let fixture: ComponentFixture<ArquivadosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ArquivadosComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ArquivadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
