package org.samodumkina.client;

import io.grpc.Channel;
import org.samodumkina.stubs.product.ProductRequest;
import org.samodumkina.stubs.product.ProductResponse;
import org.samodumkina.stubs.product.ProductServiceGrpc;

public class ProductClient {

  private final ProductServiceGrpc.ProductServiceBlockingStub productServiceBlockingStub;

  public ProductClient(Channel channel) {
    productServiceBlockingStub = ProductServiceGrpc.newBlockingStub(channel);
  }

  public String isProductAvailableForOrder(int productId) {
    ProductRequest request = ProductRequest.newBuilder().setProductId(productId).build();
    ProductResponse product = productServiceBlockingStub.getProduct(request);

    if (product.getQuantity() > 0) {
      return "Product price is %f".formatted(product.getPrice());
    } else {
      return "Product is not available for order";
    }
  }
}
