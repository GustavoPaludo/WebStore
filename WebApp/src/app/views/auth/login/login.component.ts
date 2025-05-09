import { Component } from "@angular/core";
import { Router } from "@angular/router";
import { UserAuthService } from "src/app/services/user/user-auth.service";
import { FormsModule } from '@angular/forms';
import { UserLoginModel } from "src/app/models/user/user-login.model";
import { encryptPasswordAES } from "src/app/utils/password-encrypt";
import { CommonModule } from "@angular/common";
import { isEmailValid, isPasswordValid } from "src/app/utils/field-validation";

@Component({
    selector: "app-login",
    templateUrl: "./login.component.html",
    standalone: true,
    imports: [FormsModule, CommonModule],
})
export class LoginComponent {
    email = '';
    password = '';
    userLoginModel: UserLoginModel;

    emailTouched = false;
    passwordTouched = false;

    constructor(private authService: UserAuthService, private router: Router) { }

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
                if (res.problemList && res.problemList.length > 0) {
                    const mensagens = res.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao logar:\n" + mensagens);
                } else {
                    this.router.navigate(['/main']);
                }
            },
            error: err => {
                alert(err.error.problemList[0].description);
            }
        });
    }
}
