import { Component, OnInit } from "@angular/core";
import { RouterModule } from "@angular/router";
import { FooterComponent } from "src/app/components/footers/footer/footer.component";
import { ProductListComponent } from "src/app/components/lists/product/product-list.component";
import { FeaturedProductsComponent } from "src/app/components/main/featured-products.component";
import { HeroSectionComponent } from "src/app/components/main/hero-section.component";
import { IndexNavbarComponent } from "src/app/components/navbars/index-navbar/index-navbar.component";

@Component({
    selector: "app-index",
    templateUrl: "./index.component.html",
    standalone: true,
    imports: [
        FooterComponent,
        RouterModule,
        IndexNavbarComponent,
        ProductListComponent,
        HeroSectionComponent,
        FeaturedProductsComponent
    ]
})
export class IndexComponent implements OnInit {
    constructor() { }

    ngOnInit(): void { }
}