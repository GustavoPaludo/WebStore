import { Component, OnInit } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { IndexNavbarComponent } from "src/app/components/navbars/index-navbar/index-navbar.component";

@Component({
    selector: "app-auth",
    templateUrl: "./auth.component.html",
    standalone: true,
    imports: [RouterOutlet, IndexNavbarComponent]
})
export class AuthComponent implements OnInit {
    constructor() { }

    ngOnInit(): void { }
}
