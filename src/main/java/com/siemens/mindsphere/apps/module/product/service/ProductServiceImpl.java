package com.siemens.mindsphere.apps.module.product.service;

import com.siemens.mindsphere.apps.module.product.entity.ParamDetails;
import com.siemens.mindsphere.apps.module.product.entity.Product;
import com.siemens.mindsphere.apps.module.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Predicate;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductParamsService productParamsService;

    @Override
    public Product addProduct(Product product) {
        Predicate<ParamDetails> paramDetailsPredicate = paramDetails -> paramDetails != null
                && paramDetails.getProductParams() != null
                && paramDetails.getProductParams().getId() != null;
        if (product.getParamDetails() != null) {
            product.getParamDetails().stream()
                    .filter(paramDetailsPredicate)
                    .forEach(paramDetails -> {
                        if (productParamsService.getProductParam(paramDetails.getProductParams().getId()) != null) {
                            paramDetails.setProductParams(productParamsService.getProductParam(paramDetails.getProductParams().getId()));
                        }
                    });
        }
        productRepository.save(product);
        return getProduct(product.getId());
    }

    @Override
    public void deleteProduct(Integer productId) {
        if(productRepository.findById(productId) != null) {
            productRepository.deleteById(productId);
        } else {
            // TODO throw custom exception
        }
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Product getProduct(Integer productId) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        Product existingProduct = null;
        if(existingProductOptional.isPresent()) {
            existingProduct = existingProductOptional.get();
        } else {
            // TODO throw custom exception
        }
        return existingProduct;
    }

}
