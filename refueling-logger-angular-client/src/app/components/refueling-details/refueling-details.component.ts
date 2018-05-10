import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RefuelingService } from '../../services/refueling.service';
import { Refueling } from '../../models/refueling';

@Component({
  selector: 'app-refueling-details',
  templateUrl: './refueling-details.component.html',
  styleUrls: ['./refueling-details.component.css']
})
export class RefuelingDetailsComponent implements OnInit {

  refueling: Refueling;
  id: number;

  constructor(private route: ActivatedRoute, private refuelService: RefuelingService, private router: Router) { }

  ngOnInit() {
    this.id = +this.route.snapshot.paramMap.get('id');
    this.refuelService.getRefuelingById(this.id).subscribe(refueling => this.refueling = refueling);
  }

  deleteThisRefueling() {
    this.refuelService.deleteRefuelingById(this.id).subscribe(() => this.router.navigateByUrl('/'));
  }

  editThisRefueling() {
    this.router.navigateByUrl('/updaterefueling/' + this.id);
  }

}
