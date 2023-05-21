import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TravelLogReportComponent } from './travelLogReport.component';

describe('ServiceComponent', () => {
  let component: TravelLogReportComponent;
  let fixture: ComponentFixture<TravelLogReportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TravelLogReportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TravelLogReportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
