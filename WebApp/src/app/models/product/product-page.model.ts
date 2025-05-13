import { ProductModel } from "./product.model";

export interface ProductPageModel {
    products: ProductModel[];
    totalItems: number;
}
