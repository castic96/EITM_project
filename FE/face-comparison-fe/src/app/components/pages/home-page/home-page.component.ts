import { Component, OnInit } from '@angular/core';
import {QueryService} from '../../../services/query/query.service';
import {User} from '../../../domain/User';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {

  users: User[] = [];

  constructor(private queryService: QueryService) { }

  ngOnInit(): void {
    this.queryService.testBackend().subscribe( data => {
      this.users = [];
      // tslint:disable-next-line:forin
      for (const key in data){
        this.users.push(data[key]);
        console.log(data[key]);
      }
    });
  }

}
