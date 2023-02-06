import {Component, OnInit} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  version: string;

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.getSeances().subscribe(version => {
      this.version = version;
    });
  }

  getSeances(): Observable<any> {
    return this.httpClient.get('/version', {responseType: 'text'});
  }
}
