import {Component, ElementRef, HostListener, Injectable, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TravelLogService} from '../../service/travelLog.service';
import {TravelLogModel} from '../../model/travelLog.model';

@Component({
  selector: 'app-travel-log',
  templateUrl: './travel-log.component.html',
  styleUrls: ['./travel-log.component.scss']
})
@Injectable({providedIn: 'root'})
export class TravelLogComponent implements OnInit{

  constructor(private http: HttpClient, private travelLogService: TravelLogService) {
  }
  isTableVisible = false;
  expandRow = false;
  selectedLog: TravelLogModel | null = null;
  travelLogs: TravelLogModel[] = [];
  newTravelLog: TravelLogModel = {
    id: null,
    description: '',
    date: '',
    regNumber: '',
    ownerName: '',
    startOdometer: 0,
    endOdometer: 0,
    route: ''
  };

  @ViewChild('scrollToElement') scrollToElement: ElementRef;

  ngOnInit(): void {
    this.travelLogService.getLogsFromApi().subscribe((data) => {
      this.travelLogs = data;
    });

    this.checkScreenWidth();
  }

  @HostListener('window:resize', ['$event'])
  onWindowResize(event: any): void {
    this.checkScreenWidth();
  }

  checkScreenWidth(): void {
    this.isTableVisible = window.innerWidth > 630;
  }
  onEditClickScrollToElement(): void {
    if (window.innerWidth < 630) {
      setTimeout(() => {
        if (this.scrollToElement) {
          this.scrollToElement.nativeElement.scrollIntoView({ behavior: 'smooth' });
        }
      }, 100);
    }
  }

  createTravelLog(): void {
    this.travelLogService.postLogsToApi(this.newTravelLog).subscribe((response) => {
        this.newTravelLog = response;
        this.travelLogs.push(response);
        this.expandRow = false;
      },
      (error) => {
        console.error('Error creating travel log:', error);
      }
    );
  }

  cancelCreateTravelLog(): void {
    this.expandRow = false;
  }

  editLog(log: TravelLogModel): void {
    this.selectedLog = {...log};
  }

  saveEditedLog(): void {
    this.travelLogService.editLogInApi(this.selectedLog.id, this.selectedLog).subscribe(
      (response) => {
        const updatedLogIndex = this.travelLogs.findIndex((log) => log.id === this.selectedLog.id);
        if (updatedLogIndex !== -1) {
          this.travelLogs[updatedLogIndex] = response as TravelLogModel;
          this.selectedLog = null;
          console.log('Log updated:', response);
        }
      },
      (error) => {
        console.error('Error updating travel log:', error);
      }
    );
  }

  cancelEditedLog(): void {
    // Reset the properties without saving changes
    this.selectedLog = null;
  }

  deleteTravelLog(log: TravelLogModel): void {
    const id = log.id;
    this.travelLogService.deleteLogInApi(id).subscribe(
      () => {
        this.travelLogs = this.travelLogs.filter((tLog) => tLog.id !== id);
        this.selectedLog = null;
      },
      (error) => {
        console.error('Error deleting log:', error);
      }
    );
  }

}
