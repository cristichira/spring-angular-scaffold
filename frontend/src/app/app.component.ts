import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ConfigService } from './config.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: [ './app.component.scss' ]
})
export class AppComponent implements OnInit {
  message: string;
  name: string;

  constructor(private httpClient: HttpClient,
              private configService: ConfigService) {
  }

  ngOnInit(): void {
    this.name = this.configService.getName();

    this.httpClient.get("/api/test").subscribe((response: any) => {
      this.message = response.value;
    })
  }
}
