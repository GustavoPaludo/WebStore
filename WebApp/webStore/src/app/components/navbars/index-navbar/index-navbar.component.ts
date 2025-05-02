import { Component, OnInit } from "@angular/core";
import { IndexDropdownComponent } from "../../dropdowns/index-dropdown/index-dropdown.component";
import { NgClass } from "@angular/common";
import { RouterModule } from "@angular/router";

@Component({
  selector: "app-index-navbar",
  templateUrl: "./index-navbar.component.html",
  standalone: true,
  imports: [
    IndexDropdownComponent,
    NgClass,
    RouterModule
  ]
})
export class IndexNavbarComponent implements OnInit {
  navbarOpen = false;

  constructor() { }

  ngOnInit(): void { }

  setNavbarOpen() {
    this.navbarOpen = !this.navbarOpen;
  }
}
