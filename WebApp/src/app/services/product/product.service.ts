import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root',
})
export class ProductService {
    constructor(private http: HttpClient) {}

    public getProductsByPaging(page: number, size: number): Observable<any> {
        return this.http.get(environment.url + '/common/product/list-by-paging?page=' + page + '&size=' + size);
    }
}
