import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarWrapperComponent } from './navbar-wrapper.component';

describe('NavbarWrapperComponent', () => {
  let component: NavbarWrapperComponent;
  let fixture: ComponentFixture<NavbarWrapperComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NavbarWrapperComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(NavbarWrapperComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
