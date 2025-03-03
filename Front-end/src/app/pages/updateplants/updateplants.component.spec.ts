import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateplantsComponent } from './updateplants.component';

describe('UpdateplantsComponent', () => {
  let component: UpdateplantsComponent;
  let fixture: ComponentFixture<UpdateplantsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateplantsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateplantsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
