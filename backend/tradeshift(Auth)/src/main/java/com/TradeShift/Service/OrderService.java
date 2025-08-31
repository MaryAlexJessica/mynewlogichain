package com.TradeShift.Service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.TradeShift.Entity.Orders;
import com.TradeShift.Entity.Orders.OrderStatus;
import com.TradeShift.Entity.User;
import com.TradeShift.Repository.OrdersRepository;
import com.TradeShift.Repository.UserRepository;
import com.TradeShift.dto.Order.OrderRequest;

@Service
public class OrderService {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private UserRepository userRepository;
	
    public Orders createOrder(OrderRequest request, String username) {
        // fetch logged-in user from DB using username from token
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setPlacedBy(user);
        order.setCname(request.getCustomerName());
        order.setTamount(request.getTotalAmount());
        order.setStatus(Orders.OrderStatus.Pending);

        return ordersRepository.save(order);
    }
	
    public Orders getOrder(Long id) {
        return ordersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }
    
    public Orders updateStatus(Long orderId, String status, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // ✅ Direct role check (no stream needed)
        String roleName = user.getRole().getRoleName(); 
        if (!(roleName.equalsIgnoreCase("ADMIN") || roleName.equalsIgnoreCase("STAFF"))) {
            throw new RuntimeException("Access denied: Only admins/staff can update order status");
        }

        Orders order = ordersRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(Orders.OrderStatus.valueOf(status));
        return ordersRepository.save(order);
    }

	
//    public void deleteOrdersByUserId(Long userId) {
//        ordersRepository.deleteByPlacedBy_Id(userId);
////        ResponseEntity.ok("Cancellation success");
//    }
//    
//    public void cancelOrder(Long id) {
//        Orders order = getOrder(id);
//        order.setStatus(OrderStatus.Cancelled);
//        ordersRepository.save(order);
//    }
    
    public void deleteOrdersByUserId(Long userId, String username) {
        User loggedInUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String roleName = loggedInUser.getRole().getRoleName(); // ✅ since you have single role per user

        // ✅ If not admin/staff, only allow delete of their own orders
        if (!(roleName.equalsIgnoreCase("ADMIN") || roleName.equalsIgnoreCase("STAFF"))) {
            if (!loggedInUser.getId().equals(userId)) {
                throw new RuntimeException("Access denied: You can only delete your own orders");
            }
        }

        // delete orders
        List<Orders> orders = ordersRepository.findByPlacedById(userId);
        if (orders.isEmpty()) {
            throw new RuntimeException("No orders found for user " + userId);
        }

        ordersRepository.deleteAll(orders);
    }
}
