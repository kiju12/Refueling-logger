import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RefuelingListComponent } from './components/refueling-list/refueling-list.component';
import { AddVehicleComponent } from './components/add-vehicle/add-vehicle.component';
import { AddRefuelingComponent } from './components/add-refueling/add-refueling.component';
import { AddPersonComponent } from './components/add-person/add-person.component';
import { RefuelingDetailsComponent } from './components/refueling-details/refueling-details.component';


const routes: Routes = [
  { path: '', component: RefuelingListComponent },
  { path: 'addvehicle', component: AddVehicleComponent },
  { path: 'addrefueling', component: AddRefuelingComponent },
  { path: 'updaterefueling/:id', component: AddRefuelingComponent },
  { path: 'addperson', component: AddPersonComponent },
  { path: 'refueling/:id', component: RefuelingDetailsComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class RoutingModule { }
