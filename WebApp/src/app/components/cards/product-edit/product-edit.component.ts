import { Component, OnInit, signal } from "@angular/core";
import { ProductService } from "src/app/services/product/product.service";
import { ProductModel } from "src/app/models/product/product.model";
import { CommonModule } from "@angular/common";
import { FormsModule } from "@angular/forms";

@Component({
  selector: "app-product-edit",
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: "./product-edit.component.html",
})
export class ProductEditComponent implements OnInit {
  products: ProductModel[] = [];
  selectedProduct: ProductModel | null = null;

  showFormModal = false;
  showDeleteModal = false;

  nameTouched = false;
  priceTouched = false;
  quantityTouched = false;
  descriptionTouched = false;
  formattedPrice: string = '';

  page = 0;
  size = 5;
  totalPages = 0;

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
    this.loadProducts();
  }

  loadProducts(): void {
    this.productService.getProductsByPaging(this.page, this.size).subscribe({
      next: (response) => {
        this.products = response.products;
        this.totalPages = Math.ceil(response.totalItems / this.size);
      },
      error: (err) => {
        this.products = [];
        this.totalPages = 0;
      }
    });
  }

  onPriceInput(event: Event): void {
    const input = event.target as HTMLInputElement;
    let numericString = input.value.replace(/\D/g, '');

    if (!numericString) {
      numericString = '0';
    }

    const numericValue = parseFloat(numericString) / 100;
    this.selectedProduct.price = numericValue;

    const formatted = numericValue.toLocaleString('pt-BR', {
      style: 'currency',
      currency: 'BRL',
    });

    input.value = formatted;
    this.formattedPrice = formatted;
  }

  isNameValid(): boolean {
    return !!this.selectedProduct?.name?.trim();
  }

  isPriceValid(): boolean {
    return this.selectedProduct?.price != null && this.selectedProduct.price > 0;
  }

  isQuantityValid(): boolean {
    return this.selectedProduct?.quantity != null && this.selectedProduct.quantity > 0;
  }

  isDescriptionValid(): boolean {
    return !!this.selectedProduct?.description?.trim();
  }

  openEditModal(product: ProductModel): void {
    this.nameTouched = false;
    this.priceTouched = false;
    this.quantityTouched = false;
    this.descriptionTouched = false;

    this.selectedProduct = product;
    this.showFormModal = true;
  }

  newProduct(): void {
    this.nameTouched = false;
    this.priceTouched = false;
    this.quantityTouched = false;
    this.descriptionTouched = false;

    this.selectedProduct = {
      id: 0,
      name: '',
      description: '',
      price: 0,
      quantity: 0,
      image: ''
    };
    this.showFormModal = true;
  }

  saveProduct(): void {
    this.nameTouched = true;
    this.priceTouched = true;
    this.quantityTouched = true;
    this.descriptionTouched = true;

    if (!this.isNameValid() || !this.isPriceValid() || !this.isQuantityValid() || !this.isDescriptionValid()) {
      return;
    }

    if (!this.selectedProduct) return;

    const product = this.selectedProduct;
    const request = product.id
      ? this.productService.updateProduct(product)
      : this.productService.createProduct(product);

    request.subscribe(() => {
      this.closeFormModal();
      this.loadProducts();
    });
  }

  closeFormModal(): void {
    this.selectedProduct = null;
    this.showFormModal = false;
  }

  openDeleteModal(product: ProductModel): void {
    this.selectedProduct = product;
    this.showDeleteModal = true;
  }

  confirmDelete(): void {
    if (!this.selectedProduct) return;
    this.productService.deleteProduct(this.selectedProduct.id).subscribe(() => {
      this.closeDeleteModal();
      this.loadProducts();
    });
  }

  closeDeleteModal(): void {
    this.selectedProduct = null;
    this.showDeleteModal = false;
  }

  previousPage(): void {
    if (this.page > 0) {
      this.page--;
      this.loadProducts();
    }
  }

  nextPage(): void {
    if (this.page < this.totalPages - 1) {
      this.page++;
      this.loadProducts();
    }
  }
}
