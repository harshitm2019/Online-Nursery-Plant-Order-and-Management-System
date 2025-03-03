import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchPlants1Component } from './search-plants.component';

describe('SearchPlantsComponent', () => {
  let component: SearchPlants1Component;
  let fixture: ComponentFixture<SearchPlants1Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SearchPlants1Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SearchPlants1Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
