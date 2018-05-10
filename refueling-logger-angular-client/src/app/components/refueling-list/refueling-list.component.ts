import { Component, OnInit } from '@angular/core';
import { RefuelingService } from '../../services/refueling.service';
import { Refueling, FuelType } from '../../models/refueling';
import * as Comparators from './refueling-comparators';

@Component({
  selector: 'app-refueling-list',
  templateUrl: './refueling-list.component.html',
  styleUrls: ['./refueling-list.component.css']
})
export class RefuelingListComponent implements OnInit {

  allRefuelings: Refueling[] = [];
  toggleSort = -1;

  constructor(private refuelingService: RefuelingService) { }

  ngOnInit() {
    this.initList();
  }

  initList() {
    this.refuelingService.getAllRefuelings().subscribe(refuelingList => this.allRefuelings = refuelingList);
  }

  deleteRefueling(refueling: Refueling) {
    this.refuelingService.deleteRefuelingById(refueling.id).subscribe(response => this.initList());
  }

  filterByFuelType(fuelType: String) {
    this.refuelingService.getAllRefuelings().subscribe(refuelingList => {
      this.allRefuelings = refuelingList.filter((refueling: Refueling) => refueling.fuelType.toString() === fuelType);
    });
  }

  sortByFuelType() {
    this.toggleSort *= -1;
    this.allRefuelings.sort(Comparators.getComparatorByFuelType(this.toggleSort));
  }

  sortByAmount() {
    this.toggleSort *= -1;
    this.allRefuelings.sort(Comparators.getComparatorByFuelAmount(this.toggleSort));
  }

  sortByPerson() {
    this.toggleSort *= -1;
    this.allRefuelings.sort(Comparators.getComparatorByPerson(this.toggleSort));
  }

  sortByPayment() {
    this.toggleSort *= -1;
    this.allRefuelings.sort(Comparators.getComparatorByPayment(this.toggleSort));
  }

  sortByDate() {
    this.toggleSort *= -1;
    this.allRefuelings.sort(Comparators.getComparatorByDateTime(this.toggleSort));
  }


}
