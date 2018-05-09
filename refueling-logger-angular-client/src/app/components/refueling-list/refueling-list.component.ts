import { Component, OnInit } from '@angular/core';
import { RefuelingService } from '../../services/refueling.service';
import { Refueling } from '../../models/refueling';

@Component({
  selector: 'app-refueling-list',
  templateUrl: './refueling-list.component.html',
  styleUrls: ['./refueling-list.component.css']
})
export class RefuelingListComponent implements OnInit {

  allRefuelings: Refueling[] = [];
  sortDESC = -1;

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

  sortByFuelType() {
    this.sortDESC *= -1;

    this.allRefuelings.sort((ref1: Refueling, ref2: Refueling) => {
      if (ref1.fuelType.toString() > ref2.fuelType.toString()) {
        return this.sortDESC;
      } else if (ref1.fuelType.toString() < ref2.fuelType.toString()) {
        return this.sortDESC * (-1);
      } else {
        return 0;
      }
    });
  }

}
