import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddAmcComponent } from './add-amc.component';

describe('AddAmcComponent', () => {
  let component: AddAmcComponent;
  let fixture: ComponentFixture<AddAmcComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddAmcComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AddAmcComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
