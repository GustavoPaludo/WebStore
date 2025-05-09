export function isEmailValid(email: string): boolean {
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return regex.test(email);
}

export function isPasswordValid(password: string): boolean {
    const regex = /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&]{8,}$/;
    return regex.test(password);
}

export function isCpfCnpjValid(cpfCnpj: string): boolean {
    if (!cpfCnpj) return false;
    const cleaned = cpfCnpj.replace(/\D/g, '');
    return (cleaned.length === 11 || cleaned.length === 14);
}

export function isConfirmPasswordValid(password: string, confirmPassword: string): boolean {
    if (password !== confirmPassword) {
        return false;
    }

    return true;
}
