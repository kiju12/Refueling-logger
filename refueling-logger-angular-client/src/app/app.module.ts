import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { HeaderComponent } from './components/header/header.component';
import { RefuelingListComponent } from './components/refueling-list/refueling-list.component';
import { AddVehicleComponent } from './components/add-vehicle/add-vehicle.component';
import { AddRefuelingComponent } from './components/add-refueling/add-refueling.component';
import { AddPersonComponent } from './components/add-person/add-person.component';
import { RefuelingDetailsComponent } from './components/refueling-details/refueling-details.component';
import { VehicleService } from './services/vehicle.service';
import { RefuelingService } from './services/refueling.service';
import { PersonService } from './services/person.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { RoutingModule } from './routing-module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FuelTypePipe } from './components/pipes/fuelTypePipe';
import { RoundPipe } from './components/pipes/roundPipe';
import { TimePipe } from './components/pipes/timePipe';
import { VehicleTypePipe } from './components/pipes/vehicleTypePipe';

@NgModule({
  declarations: [
    AppComponent,
    NavBarComponent,
    HeaderComponent,
    RefuelingListComponent,
    AddVehicleComponent,
    AddRefuelingComponent,
    AddPersonComponent,
    RefuelingDetailsComponent,
    FuelTypePipe,
    RoundPipe,
    TimePipe,
    VehicleTypePipe
  ],
  imports: [
    BrowserModule,
    RoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [VehicleService, RefuelingService, PersonService, HttpClient],
  bootstrap: [AppComponent]
})
export class AppModule { }
