import { CommonModule, NgStyle } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ProductModel } from 'src/app/models/product/product.model';
import { ProductService } from 'src/app/services/product/product.service';

@Component({
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    standalone: true,
    imports: [CommonModule, NgStyle, ProductService],
})
export class ProductListComponent implements OnInit {
    @Input() limit?: number;
    products: ProductModel[] = [];

    constructor(private productService: ProductService) { }

    get displayedProducts(): ProductModel[] {
        return this.limit ? this.products.slice(0, this.limit) : this.products;
    }

    ngOnInit(): void {
        this.productService.getProductsByPaging(0, 10).subscribe((data) => {
            this.products = data.content;
        });
    }
}