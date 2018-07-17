package com.siemens.mindsphere.apps.modules.product.service.product;

import com.siemens.mindsphere.apps.modules.exception.AlreadyExistingResourceException;
import com.siemens.mindsphere.apps.modules.exception.ResourceNotFoundException;
import com.siemens.mindsphere.apps.modules.product.entity.Product;
import com.siemens.mindsphere.apps.modules.product.repository.ProductRepository;
import com.siemens.mindsphere.apps.modules.product.service.paramDetails.ParamDetailsService;
import com.siemens.mindsphere.apps.modules.product.service.productParams.ProductParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static com.siemens.mindsphere.apps.exception.ErrorMappings.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductParamsService productParamsService;

    @Autowired
    private ParamDetailsService paramDetailsService;

    @Override
    public Product addProduct(Product product) throws AlreadyExistingResourceException, ResourceNotFoundException {
        if (!CollectionUtils.isEmpty(productRepository.findByCode(product.getCode()))) {
            throw new AlreadyExistingResourceException(ALREADY_EXISTING_PRODUCT_CODE, ALREADY_EXISTING_PRODUCT_MESSAGE);
        }
        if (product.getParamDetails() != null) {
            product.setParamDetails(product.getParamDetails().stream()
                    .filter(Objects::nonNull)
                    .map(paramDetails -> {
                        if(paramDetails.getParamDetailId() != null) {
                            return paramDetailsService.getParamDetail(paramDetails.getParamDetailId());
                        } else {
                            return paramDetailsService.addParamDetails(paramDetails);
                        }
                    })
                    .collect(Collectors.toSet()));
        }
        productRepository.save(product);
        return getProduct(product.getProductId());
    }

    @Override
    public void deleteProduct(Integer productId) throws ResourceNotFoundException {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        Product existingProduct = null;
        if (existingProductOptional.isPresent()) {
            existingProduct = existingProductOptional.get();
            paramDetailsService.deleteAllParamDetails(existingProduct.getParamDetails());
            productRepository.deleteById(productId);
        } else {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_CODE, PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    public Product updateProduct(Product product) throws ResourceNotFoundException {
        Product existingProduct = getProduct(product.getProductId());
        Product newProduct = null;
        if (existingProduct != null) {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setAddedBy(product.getAddedBy());
            existingProduct.setCode(product.getCode());
            existingProduct.setStatus(product.getStatus());
            existingProduct.setModifiedDate(new Date());
            product.getParamDetails().stream()
                    .forEach(paramDetails -> paramDetailsService.updateParamDetails(paramDetails));
            newProduct = productRepository.save(existingProduct);
        } else {
            newProduct = productRepository.save(newProduct);
        }
        return newProduct;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProduct(Integer productId) throws ResourceNotFoundException {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            return existingProductOptional.get();
        } else {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_CODE, PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

    public Product zDecoder(String code) throws ResourceNotFoundException {
        Optional<Product> existingProductOptional = productRepository.findByCode(code) != null
                && !productRepository.findByCode(code).isEmpty() ?
                productRepository.findByCode(code).stream().findFirst() : Optional.empty();
        if (existingProductOptional.isPresent()) {
            return existingProductOptional.get();
        } else {
            throw new ResourceNotFoundException(PRODUCT_NOT_FOUND_CODE, PRODUCT_NOT_FOUND_MESSAGE);
        }
    }

}
