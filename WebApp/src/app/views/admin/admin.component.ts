import { Component, OnInit } from "@angular/core";
import { RouterOutlet } from "@angular/router";
import { UserSidebarComponent } from "src/app/components/sidebar/user-sidebar.component";

@Component({
    selector: "app-admin",
    templateUrl: "./admin.component.html",
    standalone: true,
    imports: [RouterOutlet, UserSidebarComponent]
})
export class AdminComponent implements OnInit {
    constructor() { }

    ngOnInit(): void { }
}
