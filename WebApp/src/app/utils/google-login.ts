import { Provider } from "@angular/core";
import { SocialAuthServiceConfig, GoogleLoginProvider } from "@abacritt/angularx-social-login";

export const socialLoginProvider: Provider = {
    provide: 'SocialAuthServiceConfig',
    useValue: {
        autoLogin: false,
        providers: [
            {
                id: GoogleLoginProvider.PROVIDER_ID,
                provider: new GoogleLoginProvider('<CLIENT_ID>')
            }
        ],
        onError: (err) => {
            console.error('Erro no login social:', err);
        }
    } as SocialAuthServiceConfig
};
