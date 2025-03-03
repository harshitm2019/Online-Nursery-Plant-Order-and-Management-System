import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ManageAMCComponent } from './manage-amc.component';

describe('ManageAMCComponent', () => {
  let component: ManageAMCComponent;
  let fixture: ComponentFixture<ManageAMCComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ManageAMCComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ManageAMCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
