import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabBlocoComponent } from './tab-bloco.component';

describe('TabBlocoComponent', () => {
  let component: TabBlocoComponent;
  let fixture: ComponentFixture<TabBlocoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TabBlocoComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TabBlocoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
