import { Component } from '@angular/core';
import { ProductListComponent } from '../lists/product/product-list.component';

@Component({
  selector: 'app-featured-products',
  templateUrl: './featured-products.component.html',
  standalone: true,
  imports: [
    ProductListComponent
  ]
})
export class FeaturedProductsComponent {}