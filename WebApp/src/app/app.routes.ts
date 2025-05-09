import { Routes } from '@angular/router';
import { IndexComponent } from './views/index/index.component';
import { AuthComponent } from './views/auth/auth.component';
import { LoginComponent } from './views/auth/login/login.component';
import { RegisterComponent } from './views/auth/register/register.component';

export const routes: Routes = [
    {
        path: "auth",
        component: AuthComponent,
        children: [
            { path: "login", component: LoginComponent },
            { path: "register", component: RegisterComponent },
            { path: "", redirectTo: "login", pathMatch: "full" },
        ],
    },

    { path: "", component: IndexComponent },
    { path: "**", redirectTo: "", pathMatch: "full" }
];
