import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TabAmbientesComponent } from './tab-ambientes.component';

describe('TabAmbientesComponent', () => {
  let component: TabAmbientesComponent;
  let fixture: ComponentFixture<TabAmbientesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TabAmbientesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TabAmbientesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
