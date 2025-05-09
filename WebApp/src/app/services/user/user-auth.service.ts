import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ProblemList } from "src/app/models/problem/problem-list.model";
import { UserLoginModel } from "src/app/models/user/user-login.model";
import { UserRegisterModel } from "src/app/models/user/user-register.model";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root',
})
export class UserAuthService {
    constructor(private http: HttpClient) {}

    public login(data: UserLoginModel): Observable<ProblemList> {
        let url = environment.url;

        return this.http.post<ProblemList>(url + "/common/user/login", data, { headers: { 'Content-Type': 'application/json' } });
    }

    public register(data: UserRegisterModel): Observable<ProblemList> {
        let url = environment.url;

        return this.http.post<ProblemList>(url + "/common/user/register", data, { headers: { 'Content-Type': 'application/json' } });
    }
}
