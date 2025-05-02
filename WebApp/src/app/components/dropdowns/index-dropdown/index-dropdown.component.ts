import { NgClass } from "@angular/common";
import { Component, OnInit, ViewChild, ElementRef } from "@angular/core";
import { RouterModule } from "@angular/router";
import { createPopper } from "@popperjs/core";

@Component({
    selector: "app-index-dropdown",
    templateUrl: "./index-dropdown.component.html",
    standalone: true,
    imports: [
        RouterModule,
        NgClass
    ]
})
export class IndexDropdownComponent implements OnInit {
    dropdownPopoverShow = false;
    @ViewChild("btnDropdownRef", { static: false }) btnDropdownRef: ElementRef;
    @ViewChild("popoverDropdownRef", { static: false })
    popoverDropdownRef: ElementRef;
    ngOnInit() { }
    toggleDropdown(event) {
        event.preventDefault();
        if (this.dropdownPopoverShow) {
            this.dropdownPopoverShow = false;
        } else {
            this.dropdownPopoverShow = true;
            this.createPoppper();
        }
    }
    createPoppper() {
        createPopper(
            this.btnDropdownRef.nativeElement,
            this.popoverDropdownRef.nativeElement,
            {
                placement: "bottom-start",
            }
        );
    }
}