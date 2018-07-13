package com.siemens.mindsphere.apps.modules.order.dto;

import com.siemens.mindsphere.apps.modules.product.dto.ProductDto;

public class ProductDetailsDto {

    private ProductDto product;

    private Integer quantity;

    public ProductDetailsDto() {
    }

    public ProductDetailsDto(ProductDto product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
