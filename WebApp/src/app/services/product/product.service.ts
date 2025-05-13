import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ProductPageModel } from "src/app/models/product/product-page.model";
import { ProductModel } from "src/app/models/product/product.model";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root',
})
export class ProductService {
    constructor(private http: HttpClient) { }

    public getProductsByPaging(page: number, size: number): Observable<ProductPageModel> {
        let url = environment.url;
        return this.http.get<ProductPageModel>(url + '/common/product/list-by-paging?page=' + page + '&size=' + size);
    }

    public createProduct(product: ProductModel): Observable<ProductModel> {
        let url = environment.url;
        return this.http.post<ProductModel>(url + '/authenticated/product/create', product);
    }

    public updateProduct(product: ProductModel): Observable<ProductModel> {
        let url = environment.url;
        return this.http.post<ProductModel>(url + '/authenticated/product/update', product, { withCredentials: true });
    }

    public deleteProduct(id: number): Observable<void> {
        let url = environment.url;
        return this.http.post<void>(url + '/authenticated/product/delete', { id }, { withCredentials: true });
    }
}
