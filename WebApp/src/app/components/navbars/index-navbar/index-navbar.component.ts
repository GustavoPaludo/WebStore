import { Component, OnInit } from "@angular/core";
import { IndexDropdownComponent } from "../../dropdowns/index-dropdown/index-dropdown.component";
import { NgClass, NgIf } from "@angular/common";
import { RouterModule } from "@angular/router";
import { hasToken } from "src/app/utils/site-utils";

@Component({
  selector: "app-index-navbar",
  templateUrl: "./index-navbar.component.html",
  standalone: true,
  imports: [
    IndexDropdownComponent,
    RouterModule,
    NgIf
  ]
})
export class IndexNavbarComponent implements OnInit {
  navbarOpen = false;
  hasValidSession = false;

  constructor() { }

  ngOnInit(): void {
    this.validateSession();
  }

  setNavbarOpen() {
    this.navbarOpen = !this.navbarOpen;
  }

  validateSession() {
    this.hasValidSession = hasToken();
  }
}
