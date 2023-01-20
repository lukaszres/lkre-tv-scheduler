import {Component, OnInit} from '@angular/core';
import {Seance} from "./models";
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'lkre-tv-scheduler',
  templateUrl: './lkre-tv-scheduler.component.html',
  styleUrls: ['./lkre-tv-scheduler.component.css']
})
export class LkreTvSchedulerComponent implements OnInit {

  seances: Seance[];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.getSeances();
  }


  getSeances(): any {
    this.httpClient.get<Seance[]>('/seances')
      .subscribe((seances) => {
        this.seances = seances
      });
  }
}
