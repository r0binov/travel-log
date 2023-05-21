import {TravelLogModel} from './travelLog.model';

export class TravelLogReportModel {
  constructor(
    public startDate: string,
    public endDate: string,
    public regNumber: string,
    public regNumbers: string[],
    public ownerNames: string[],
    public ownerName: string,
    public logsByDate: { [date: string]: TravelLogModel[] },
    public totalDistance: number
  ) {
  }
}
