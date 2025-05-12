import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { UserAuthService } from "src/app/services/user/user-auth.service";
import { FormsModule } from '@angular/forms';
import { UserLoginModel } from "src/app/models/user/user-login.model";
import { encryptPasswordAES } from "src/app/utils/password-encrypt";
import { CommonModule } from "@angular/common";
import { isEmailValid, isPasswordValid } from "src/app/utils/field-validation";
import { GoogleLoginProvider } from "@abacritt/angularx-social-login";
import { environment } from "src/environments/environment";
declare const google: any;

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    standalone: true,
    imports: [FormsModule, CommonModule],
})
export class LoginComponent implements OnInit {
    email = '';
    password = '';
    userLoginModel: UserLoginModel;

    emailTouched = false;
    passwordTouched = false;

    constructor(private authService: UserAuthService, private router: Router) { }

    ngOnInit(): void {
        this.initializeGoogleSignIn();
    }

    initializeGoogleSignIn(): void {
        let clientId = environment.googleClientId;
        google.accounts.id.initialize({
            client_id: clientId,
            callback: (response: any) => this.handleGoogleCallback(response),
        });

        google.accounts.id.renderButton(
            document.getElementById('googleBtn'),
            { theme: 'outline', size: 'large' }
        );
    }

    handleGoogleCallback(response: any): void {
        const credential = response.credential;

        const payload = JSON.parse(atob(credential.split('.')[1]));

        const googleLoginModel = {
            email: payload.email,
            password: payload.sub
        };

        this.authService.login(googleLoginModel).subscribe({
            next: (res) => {
                if (res.problemList && res.problemList.problemList.length > 0) {
                    const mensagens = res.problemList.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao logar com Google:\n" + mensagens);
                } else {
                    const token = res.jwtToken;
                    if (token) {
                        sessionStorage.setItem('jwtToken', token);
                    }

                    this.router.navigate(['/admin']);
                }
            },
            error: err => {
                alert(err.error.problemList.problemList[0].description);
            }
        });
    }

    isEmailValid(): boolean {
        return isEmailValid(this.email);
    }

    isPasswordValid(): boolean {
        return isPasswordValid(this.password);
    }

    onLogin() {
        this.emailTouched = true;
        this.passwordTouched = true;

        if (!isEmailValid(this.email) || !isPasswordValid(this.password)) {
            return;
        }

        const encryptedPassword = encryptPasswordAES(this.password);

        this.userLoginModel = {
            email: this.email,
            password: encryptedPassword,
        };

        this.authService.login(this.userLoginModel).subscribe({
            next: (res) => {
                if (res.problemList && res.problemList.problemList.length > 0) {
                    const mensagens = res.problemList.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao logar:\n" + mensagens);
                } else {
                    const token = res.jwtToken;
                    if (token) {
                        sessionStorage.setItem('jwtToken', token);
                    }

                    this.router.navigate(['/admin']);
                }
            },
            error: err => {
                alert(err.error.problemList.problemList[0].description);
            }
        });
    }
}
