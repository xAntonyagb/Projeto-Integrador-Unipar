import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabTodosOsServicosComponent } from './tab-todos-os-servicos.component';

describe('TabTodosOsServicosComponent', () => {
  let component: TabTodosOsServicosComponent;
  let fixture: ComponentFixture<TabTodosOsServicosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TabTodosOsServicosComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(TabTodosOsServicosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
