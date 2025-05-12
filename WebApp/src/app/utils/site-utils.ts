export function getToken(): string {
    return sessionStorage.getItem('jwtToken');
}

export function hasToken(): boolean {
    return sessionStorage.getItem('jwtToken') !== null;
}

export function removeToken(): void {
    sessionStorage.removeItem('jwtToken');
}
