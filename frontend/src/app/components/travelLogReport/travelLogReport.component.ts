import {Component, Injectable, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TravelLogReportModel} from '../../model/travelLogReport.model';
import {environment} from '../../../environments/environment';
import {TravelLogModel} from '../../model/travelLog.model';

@Component({
  selector: 'app-travel-log-report',
  templateUrl: './travelLogReport.component.html',
  styleUrls: ['./travelLogReport.component.scss']
})
@Injectable({providedIn: 'root'})
export class TravelLogReportComponent implements OnInit {
  startDate: string;
  endDate: string;
  regNumber: string;
  ownerName: string;
  report: TravelLogReportModel | null = null;
  regNumbers: string[] = [];
  ownerNames: string[] = [];

  constructor(private http: HttpClient) {
  }

  ngOnInit(): void {
    this.fetchDropdownData();
  }

  fetchDropdownData(): void {
    const url = environment.apiUrl;
    this.http.get<TravelLogModel[]>(url).subscribe(
      (data: TravelLogModel[]) => {
        this.regNumbers = [...new Set(data.map((log) => log.regNumber))];
        this.ownerNames = [...new Set(data.map((log) => log.ownerName))];
      },
      (errorResp) => {
        console.error(errorResp);
      }
    );
  }


  generateReport(): void {
    const url = environment.apiUrl + `/report?startDate=${this.startDate}&endDate=${this.endDate}&regNumber=${this.regNumber}&ownerName=${this.ownerName}`;

    this.http.get(url).subscribe(
      (data: TravelLogReportModel) => {
        this.report = data;
      }
      , (errorResp) => {
        console.error(errorResp);
      }
    );
  }

  getLogModels(): TravelLogModel[] {
    if (this.report && this.report.logsByDate) {
      const logModels: TravelLogModel[] = [];
      Object.values(this.report.logsByDate).forEach((logArray) => {
        logModels.push(...logArray);
      });
      return logModels;
    }
    return [];
  }

}

