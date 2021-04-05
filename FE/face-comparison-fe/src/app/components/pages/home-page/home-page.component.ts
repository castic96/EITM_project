import { Component, OnInit } from '@angular/core';
import {QueryService} from '../../../services/query/query.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  message = '';

  constructor(private queryService: QueryService) { }

  ngOnInit(): void {
    this.queryService.testBackend().subscribe( data => {
      this.message = data.errorMessage;
    });
  }

}
