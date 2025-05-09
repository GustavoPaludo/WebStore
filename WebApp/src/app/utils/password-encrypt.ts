import CryptoJS from "crypto-js";

const SECRET_KEY = 'asd1g45g7uas90xe';

export function encryptPasswordAES(plainText: string): string {
    const key = CryptoJS.enc.Utf8.parse(SECRET_KEY);
    const encrypted = CryptoJS.AES.encrypt(plainText, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7,
    });

    return encrypted.toString();
}
