export class TravelLogModel {

  constructor(
    public id: number,
    public description: string,
    public date: string,
    public regNumber: string,
    public ownerName: string,
    public startOdometer: number,
    public endOdometer: number,
    public route: string
  ) {
  }
}
