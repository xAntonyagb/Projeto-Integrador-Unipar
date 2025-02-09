import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarBlocoComponent } from './editar-bloco.component';

describe('EditarBlocoComponent', () => {
  let component: EditarBlocoComponent;
  let fixture: ComponentFixture<EditarBlocoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [EditarBlocoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(EditarBlocoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
