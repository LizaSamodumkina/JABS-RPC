package org.samodumkina.service;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.samodumkina.client.ProductClient;
import org.samodumkina.stubs.order.OrderRequest;
import org.samodumkina.stubs.order.OrderResponse;
import org.samodumkina.stubs.order.OrderServiceGrpc.OrderServiceImplBase;

@GRpcService
public class OrderService extends OrderServiceImplBase {

  private final ProductClient productClient;

  public OrderService(ProductClient productClient) {
    this.productClient = productClient;
  }

  @Override
  public void createOrder(OrderRequest request, StreamObserver<OrderResponse> responseObserver) {
    int productId = request.getProductId();
    String message = productClient.isProductAvailableForOrder(productId);

    OrderResponse response = OrderResponse.newBuilder().setMessage(message).build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();

  }
}
