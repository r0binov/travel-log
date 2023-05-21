import {environment} from '../../environments/environment';
import {TravelLogModel} from '../model/travelLog.model';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class TravelLogService {
  private apiUrl = environment.apiUrl;
  private travelLog: TravelLogModel[] = [];
  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'POST, GET, PUT, DELETE',
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept'
  });

  constructor(private http: HttpClient) {
  }

  getLogsFromApi = () => {
    return this.http.get<TravelLogModel[]>(this.apiUrl);
  }

  postLogsToApi = (travelLog: TravelLogModel) => {
    return this.http.post<TravelLogModel>(this.apiUrl, travelLog, {headers: this.headers});
  }

  editLogInApi = (id: number, travelLog: TravelLogModel) => {
    return this.http.put((this.apiUrl + `/${id}`), travelLog, {headers: this.headers});
  }

  deleteLogInApi = (id: number) => {
    return this.http.delete((this.apiUrl + `/${id}`), {headers: this.headers});
  }
}
