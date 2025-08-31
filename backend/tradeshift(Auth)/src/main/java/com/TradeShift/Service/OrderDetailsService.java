package com.TradeShift.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.OrderDetails;
import com.TradeShift.Entity.Orders;
import com.TradeShift.Entity.Products;
import com.TradeShift.Entity.User;
import com.TradeShift.Repository.OrderDetailsRepository;
import com.TradeShift.Repository.OrdersRepository;
import com.TradeShift.Repository.ProductsRepository;
import com.TradeShift.Repository.UserRepository;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ProductsRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // check if user is admin/staff
    private boolean isAdmin(User user) {
        String roleName = user.getRole().getRoleName();
        return roleName.equalsIgnoreCase("ADMIN") || roleName.equalsIgnoreCase("STAFF");
    }

    // ✅ Get items in an order
    public List<OrderDetails> getOrderDetails(Long orderId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!isAdmin(user) && !order.getPlacedBy().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied: You can only view your own orders");
        }

        return orderDetailsRepository.findByOrders_Id(orderId);
    }

    // ✅ Add product to an order
    public OrderDetails addProductToOrder(Long orderId, Long productId, int qty, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!isAdmin(user) && !order.getPlacedBy().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied: You can only add products to your own orders");
        }

        Products product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        OrderDetails details = new OrderDetails();
        details.setOrders(order);
        details.setProducts(product);
        details.setQty(qty);
        details.setPrice(product.getPrice());

        // ✅ Calculate subtotal
        details.setSubtotal(details.getSubtotal());

        return orderDetailsRepository.save(details);
    }


    // ✅ Update quantity
    public OrderDetails updateQuantity(Long orderDetailsId, int qty, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderDetails details = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        Orders order = details.getOrders();

        if (!isAdmin(user) && !order.getPlacedBy().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied: You can only update your own order details");
        }

        details.setQty(qty);
        return orderDetailsRepository.save(details);
    }

    // ✅ Remove product from order
    public void deleteOrderDetail(Long orderDetailsId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        OrderDetails details = orderDetailsRepository.findById(orderDetailsId)
                .orElseThrow(() -> new RuntimeException("Order detail not found"));

        Orders order = details.getOrders();

        if (!isAdmin(user) && !order.getPlacedBy().getId().equals(user.getId())) {
            throw new RuntimeException("Access denied: You can only delete your own order details");
        }

        orderDetailsRepository.delete(details);
    }
}
