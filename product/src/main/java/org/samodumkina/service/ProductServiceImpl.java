package org.samodumkina.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.samodumkina.dao.Product;
import org.samodumkina.dao.ProductRepository;
import org.samodumkina.stubs.product.ProductRequest;
import org.samodumkina.stubs.product.ProductResponse;
import org.samodumkina.stubs.product.ProductServiceGrpc.ProductServiceImplBase;

@GRpcService
public class ProductServiceImpl extends ProductServiceImplBase {

  private final ProductRepository productRepository;

  public ProductServiceImpl(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  @Override
  public void getProduct(ProductRequest request, StreamObserver<ProductResponse> responseObserver) {
    Product product = productRepository.findById(request.getProductId()).orElse(new Product());

    ProductResponse productResponse = ProductResponse.newBuilder()
        .setProductId(product.getId())
        .setName(product.getName())
        .setPrice(product.getPrice())
        .setQuantity(product.getQuantity())
        .build();

    responseObserver.onNext(productResponse);
    responseObserver.onCompleted();
  }
}
