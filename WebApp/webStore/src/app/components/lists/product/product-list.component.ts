import { CommonModule, NgStyle } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { ProductModel } from 'src/app/models/product/product.model';

@Component({
    selector: 'app-product-list',
    templateUrl: './product-list.component.html',
    standalone: true,
    imports: [CommonModule, NgStyle],
})
export class ProductListComponent implements OnInit {
    @Input() limit?: number;
    products: ProductModel[] = [];

    get displayedProducts(): ProductModel[] {
        return this.limit ? this.products.slice(0, this.limit) : this.products;
    }

    ngOnInit(): void {
        this.products = [
            {
                id: 1,
                title: 'Camisa Esportiva',
                price: 79.90,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 2,
                title: 'Tênis Corrida',
                price: 299.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 3,
                title: 'Mochila Executiva',
                price: 149.50,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 4,
                title: 'Relógio Digital',
                price: 399.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 5,
                title: 'Garrafa Térmica',
                price: 89.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 6,
                title: 'Camisa Esportiva',
                price: 79.90,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 7,
                title: 'Tênis Corrida',
                price: 299.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 8,
                title: 'Mochila Executiva',
                price: 149.50,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 9,
                title: 'Relógio Digital',
                price: 399.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 10,
                title: 'Garrafa Térmica',
                price: 89.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 11,
                title: 'Camisa Esportiva',
                price: 79.90,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 12,
                title: 'Tênis Corrida',
                price: 299.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 13,
                title: 'Mochila Executiva',
                price: 149.50,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 14,
                title: 'Relógio Digital',
                price: 399.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
            {
                id: 15,
                title: 'Garrafa Térmica',
                price: 89.99,
                description: 'Camisa leve, ideal para atividades físicas.',
                image: 'assets/img/product-placeholder.png',
            },
        ];
    }
}