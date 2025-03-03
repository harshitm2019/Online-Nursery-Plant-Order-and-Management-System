import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminSearchPlantComponent } from './admin-search-plant.component';

describe('AdminSearchPlantComponent', () => {
  let component: AdminSearchPlantComponent;
  let fixture: ComponentFixture<AdminSearchPlantComponent>;
  
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminSearchPlantComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(AdminSearchPlantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
