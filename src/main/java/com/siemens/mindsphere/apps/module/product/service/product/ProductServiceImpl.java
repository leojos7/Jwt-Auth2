package com.siemens.mindsphere.apps.module.product.service.product;

import com.siemens.mindsphere.apps.module.product.entity.ParamDetails;
import com.siemens.mindsphere.apps.module.product.entity.Product;
import com.siemens.mindsphere.apps.module.product.exception.AlreadyExistingProductException;
import com.siemens.mindsphere.apps.module.product.repository.ParamDetailsRespository;
import com.siemens.mindsphere.apps.module.product.repository.ProductRepository;
import com.siemens.mindsphere.apps.module.product.service.paramDetails.ParamDetailsService;
import com.siemens.mindsphere.apps.module.product.service.productParams.ProductParamsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
    public Product addProduct(Product product) throws AlreadyExistingProductException {
        Predicate<ParamDetails> paramDetailsPredicate = paramDetails -> paramDetails != null
                && paramDetails.getProductParams() != null
                && paramDetails.getProductParams().getId() != null;
        if(zDecoder(product.getCode()) == null) {
            throw new AlreadyExistingProductException("Already Existing Product");
        }
        if (product.getParamDetails() != null) {
            product.getParamDetails().stream()
                    .filter(paramDetailsPredicate)
                    .forEach(paramDetails -> {
                        if (productParamsService.getProductParam(paramDetails.getProductParams().getId()) != null) {
                            paramDetails.setProductParams(
                                    productParamsService.getProductParam(paramDetails.getProductParams().getId()));
                        }
                    });
        }
        productRepository.save(product);
        return getProduct(product.getId());
    }

    @Override
    public void deleteProduct(Integer productId) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        Product existingProduct = null;
        if(existingProductOptional.isPresent()) {
            existingProduct = existingProductOptional.get();
            paramDetailsService.deleteAllParamDetails(existingProduct.getParamDetails());
            productRepository.deleteById(productId);
        } else {
            // TODO throw custom exception
        }
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
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

    public Product zDecoder(String code) {
        Optional<Product> product = productRepository.findByCode(code).stream().findFirst();
        if(product.isPresent()) {
            return product.get();
        } else {
            return null;
        }
    }

}
