import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { UserAuthenticationModel } from "src/app/models/user/user-authentication.model";
import { UserLoginModel } from "src/app/models/user/user-login.model";
import { UserRegisterModel } from "src/app/models/user/user-register.model";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root',
})
export class UserAuthService {
    constructor(private http: HttpClient) {}

    public login(data: UserLoginModel): Observable<UserAuthenticationModel> {
        let url = environment.url;

        return this.http.post<UserAuthenticationModel>(url + "/common/user/login", data);
    }

    public register(data: UserRegisterModel): Observable<UserAuthenticationModel> {
        let url = environment.url;

        return this.http.post<UserAuthenticationModel>(url + "/common/user/register", data);
    }

    public logout(): Observable<any> {
        let url = environment.url;

        return this.http.post(url + "/authenticated/user/logout", null);
    }
}
