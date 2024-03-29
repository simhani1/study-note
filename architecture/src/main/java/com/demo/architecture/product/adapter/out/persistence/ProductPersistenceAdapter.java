package com.demo.architecture.product.adapter.out.persistence;

import com.demo.architecture.product.application.port.out.LoadProductPort;
import com.demo.architecture.product.application.port.out.SaveProductPort;
import com.demo.architecture.product.domain.Product;
import com.demo.architecture.product.exception.NoProductException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ProductPersistenceAdapter implements LoadProductPort, SaveProductPort {

    private final ProductJpaRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public Product findById(Long productId) {
        ProductJpaEntity productJpaEntity = productRepository.findById(productId).orElseThrow(NoProductException::new);
        return mapper.toDomain(productJpaEntity);
    }

    @Override
    public void save(Product product) {
        ProductJpaEntity target = mapper.toJpaEntity(product);
        ProductJpaEntity productJpaEntity = productRepository.findById(product.getProductId()).orElseThrow(NoProductException::new);
        productJpaEntity.update(target);
    }
}
