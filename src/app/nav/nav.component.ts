import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsersService } from '../users.service';
import { RouterOutlet,RouterLink } from '@angular/router';
@Component({
  selector: 'app-nav',
  standalone: true,
  imports: [CommonModule,RouterLink,RouterOutlet],
  templateUrl: './nav.component.html',
  styleUrl: './nav.component.css'
})
export class NavComponent implements OnInit{
     
  isAuthenticated:boolean = false;
  isManager:boolean = false;
  isConsultant:boolean = false;
  isClient:boolean = false;
  constructor(public userService: UsersService) {}
  ngOnInit(): void {
    this.isAuthenticated = this.userService.isAuthenticated();
    this.isManager = this.userService.isManager();
    this.isClient = this.userService.isClient();
    this.isConsultant = this.userService.isConsultant();
}
logout():void{
  this.userService.logOut();
  this.isAuthenticated = false;
  this.isManager = false;
  this.isClient = false;
  this.isConsultant = false;
}
}
