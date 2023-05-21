import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {TravelLogReportComponent} from './components/travelLogReport/travelLogReport.component';


const routes: Routes = [];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
