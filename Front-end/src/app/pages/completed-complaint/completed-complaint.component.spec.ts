import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CompletedComplaintComponent } from './completed-complaint.component';

describe('CompletedComplaintComponent', () => {
  let component: CompletedComplaintComponent;
  let fixture: ComponentFixture<CompletedComplaintComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CompletedComplaintComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CompletedComplaintComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
