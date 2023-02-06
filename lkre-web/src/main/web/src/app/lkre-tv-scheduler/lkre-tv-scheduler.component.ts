import {Component, OnInit} from '@angular/core';
import {Seance} from "./models";
import {HttpClient} from '@angular/common/http';
import {FormControl} from "@angular/forms";
import {Observable} from "rxjs";
import {formatDate} from "@angular/common";
import {MatSnackBar} from "@angular/material/snack-bar";
import {MatSnackBarConfig} from "@angular/material/snack-bar/typings/snack-bar-config";

@Component({
  selector: 'lkre-tv-scheduler',
  templateUrl: './lkre-tv-scheduler.component.html',
  styleUrls: ['./lkre-tv-scheduler.component.css']
})
export class LkreTvSchedulerComponent implements OnInit {
  SEPARATOR: string = " :: ";

  toppings = new FormControl('');
  genres: string[];

  allSeances: Seance[];
  selectedSeances: Seance[];
  selectedSeancesString: string;

  constructor(private httpClient: HttpClient,
              private _snackBar: MatSnackBar) {
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
    this.selectedSeancesString = this.seancesToString(this.selectedSeances);
  }

  seancesToString(selectedSeances: Seance[]): string {
    return this.selectedSeances.map(value => this.seanceToString(value)).join('\n');
  }

  seanceToString(seance: Seance): string {
    let episode = seance.episode != null ? this.SEPARATOR + seance.episode : "";
    return formatDate(seance.time, "hh:mm", "en-US") + this.SEPARATOR + seance.channel + this.SEPARATOR + seance.genre + this.SEPARATOR + seance.title + episode;
  }

  copyToClipboard(): void {
    let listener = (e: ClipboardEvent) => {
      e.clipboardData.setData('text/plain', (this.selectedSeancesString));
      e.preventDefault();
    };
    document.addEventListener('copy', listener);
    document.execCommand('copy');
    document.removeEventListener('copy', listener);
    this._snackBar.open("copied", '', {duration: 5000} as MatSnackBarConfig);
  }
}
