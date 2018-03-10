import { Component, OnInit } from '@angular/core';
import {DataService} from '../../services/data.service';
import {User} from '../../models/user';

@Component({
  selector: 'app-search-users',
  templateUrl: './search-users.component.html',
  styleUrls: ['./search-users.component.css']
})

export class SearchUsersComponent implements OnInit {
  lastName: string;
  users: User[];

  constructor(private dataService: DataService) {}

  ngOnInit() {
    this.lastName = '';
  }

  private searchUsers() {
    this.dataService.getUsersByLastName(this.lastName).then(users => this.users = users);
  }

  onSubmit() {
    this.searchUsers();
  }

}
