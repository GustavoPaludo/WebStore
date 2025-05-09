import { Component } from "@angular/core";
import { Router, RouterLink } from "@angular/router";
import { FormsModule } from '@angular/forms';
import { UserRegisterModel } from "src/app/models/user/user-register.model";
import { UserAuthService } from "src/app/services/user/user-auth.service";
import { encryptPasswordAES } from "src/app/utils/password-encrypt";
import { NgIf } from "@angular/common";

@Component({
    selector: "app-register",
    templateUrl: "./register.component.html",
    standalone: true,
    imports: [FormsModule, RouterLink, NgIf],
})
export class RegisterComponent {
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
                if (res.problemList && res.problemList.length > 0) {
                    const mensagens = res.problemList.map(p => `- ${p.description}`).join('\n');
                    alert("Erro ao registrar:\n" + mensagens);
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
