import { Component, OnInit } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { FormsModule } from '@angular/forms';
import { UserRegisterModel } from "src/app/models/user/user-register.model";
import { UserAuthService } from "src/app/services/user/user-auth.service";
import { encryptPasswordAES } from "src/app/utils/password-encrypt";
import { NgIf } from "@angular/common";
import { environment } from "src/environments/environment";
declare const google: any;

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    standalone: true,
    imports: [FormsModule, RouterLink, NgIf],
})
export class RegisterComponent implements OnInit {
    name = '';
    surname = '';
    cpfCnpj = '';
    email = '';
    password = '';
    confirmPassword = '';

    touched = {
        name: false,
        surname: false,
        email: false,
        cpfCnpj: false,
        password: false,
        confirmPassword: false
    };

    userRegisterModel: UserRegisterModel;

    constructor(private authService: UserAuthService, private router: Router) { }

    ngOnInit(): void {
        this.initializeGoogleSignUp();
    }

    initializeGoogleSignUp(): void {
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

        const googleRegisterModel: UserRegisterModel = {
            name: payload.given_name || '',
            surname: payload.family_name || '',
            email: payload.email,
            fedidentification: '',
            password: encryptPasswordAES(payload.sub)
        };

        this.authService.register(googleRegisterModel).subscribe({
            next: (res) => {
                if (res.problemList && res.problemList.problemList.length > 0) {
                    const mensagens = res.problemList.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao cadastrar com Google:\n" + mensagens);
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
        const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return regex.test(this.email);
    }

    isPasswordValid(): boolean {
        const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{6,}$/;
        return regex.test(this.password);
    }

    isCpfCnpjValid(): boolean {
        if (!this.cpfCnpj) return false;
        const cleaned = this.cpfCnpj.replace(/\D/g, '');
        return (cleaned.length === 11 || cleaned.length === 14);
    }

    isConfirmPasswordValid(): boolean {
        if (this.password !== this.confirmPassword) {
            return false;
        }

        return true;
    }

    isAnyFieldInvalid(): boolean {
        return (
            !this.name ||
            !this.cpfCnpj ||
            !this.email ||
            !this.password ||
            this.isCpfCnpjValid() ||
            this.isEmailValid() ||
            this.isPasswordValid()
        );
    }

    onRegister() {
        this.touched = {
            name: true,
            surname: true,
            email: true,
            cpfCnpj: true,
            password: true,
            confirmPassword: true
        };

        if (this.isAnyFieldInvalid()) {
            return;
        }

        const encryptedPassword = encryptPasswordAES(this.password);
        this.userRegisterModel = {
            name: this.name,
            surname: this.surname,
            fedidentification: this.cpfCnpj,
            email: this.email,
            password: encryptedPassword,
        };

        this.authService.register(this.userRegisterModel).subscribe({
            next: (res) => {
                if (res.problemList && res.problemList.problemList.length > 0) {
                    const mensagens = res.problemList.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao registrar:\n" + mensagens);
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
