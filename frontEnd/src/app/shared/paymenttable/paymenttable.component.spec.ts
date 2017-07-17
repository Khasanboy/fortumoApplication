import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymenttableComponent } from './paymenttable.component';

describe('PaymenttableComponent', () => {
  let component: PaymenttableComponent;
  let fixture: ComponentFixture<PaymenttableComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PaymenttableComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PaymenttableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
