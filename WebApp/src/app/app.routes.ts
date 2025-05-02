import { Routes } from '@angular/router';
import { IndexComponent } from './views/index/index.component';

export const routes: Routes = [
    { path: "", component: IndexComponent },
    { path: "**", redirectTo: "", pathMatch: "full" }
];
