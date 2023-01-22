import {Component, OnInit} from '@angular/core';
import {Seance} from "./models";
import {HttpClient} from '@angular/common/http';
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";

@Component({
  selector: 'lkre-tv-scheduler',
  templateUrl: './lkre-tv-scheduler.component.html',
  styleUrls: ['./lkre-tv-scheduler.component.css']
})
export class LkreTvSchedulerComponent implements OnInit {

  toppings = new FormControl('');
  genres: string[];

  allSeances: Seance[];
  selectedSeances: Seance[];

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit() {
    this.getSeances().subscribe(seances => {
      this.allSeances = seances.sort((a, b) => (a.time > b.time) ? 0 : -1);
      this.selectedSeances = seances;
      this.genres = Array.from(new Set(seances.map(seance => seance.genre)));
    })
  }

  getSeances(): Observable<Seance[]> {
    return this.httpClient.get<Seance[]>('/seances')
  }

  onToppingsChanged() {
    let genres: string[] = this.toppings.value;
    this.selectedSeances = this.allSeances.filter(value => genres.find(e => e === value.genre));
  }
}
