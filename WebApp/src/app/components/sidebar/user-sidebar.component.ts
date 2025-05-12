import { Component, OnInit } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { UserAuthService } from "src/app/services/user/user-auth.service";
import { removeToken } from "src/app/utils/site-utils";

@Component({
    selector: "user-sidebar",
    templateUrl: "./user-sidebar.component.html",
    standalone: true,
    imports: [RouterLink]
})
export class UserSidebarComponent implements OnInit {
    collapseShow = "hidden";
    constructor(private authService: UserAuthService, private router: Router) { }

    ngOnInit() { }

    toggleCollapseShow(classes) {
        this.collapseShow = classes;
    }

    logout() {
        removeToken();
        this.authService.logout().subscribe({});
    }
}
