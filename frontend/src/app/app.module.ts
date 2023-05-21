import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TravelLogComponent } from './components/travel-log/travel-log.component';
import { HttpClientModule } from '@angular/common/http';
import {TravelLogService} from './service/travelLog.service';
import {FormsModule} from '@angular/forms';
import {TravelLogReportComponent} from './components/travelLogReport/travelLogReport.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    TravelLogComponent,
    TravelLogReportComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    NgbModule
  ],
  providers: [TravelLogService],
  bootstrap: [AppComponent]
})
export class AppModule { }
