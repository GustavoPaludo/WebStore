import { Routes } from '@angular/router';
import { IndexComponent } from './views/index/index.component';
import { AuthComponent } from './views/auth/auth.component';
import { LoginComponent } from './views/auth/login/login.component';
import { RegisterComponent } from './views/auth/register/register.component';
import { AdminComponent } from './views/admin/admin.component';
import { SettingsComponent } from './views/admin/settings/settings.component';
import { ProductEditComponent } from './components/cards/product-edit/product-edit.component';

export const routes: Routes = [
    {
        path: "admin",
        component: AdminComponent,
        children: [
            { path: "settings", component: SettingsComponent },
            { path: "product-edit", component: ProductEditComponent },
            { path: "", redirectTo: "settings", pathMatch: "full" },
        ],
    },
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
