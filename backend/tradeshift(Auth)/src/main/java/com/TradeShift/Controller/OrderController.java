package com.TradeShift.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.TradeShift.Entity.Orders;
import com.TradeShift.Repository.OrdersRepository;
import com.TradeShift.Service.OrderService;
import com.TradeShift.dto.Order.OrderRequest;

@RestController
@RequestMapping("/api/auth/order")
public class OrderController {

	@Autowired
	private OrdersRepository ordersRepository;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping
	public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest,
	                                     Authentication authentication) {
	    String username = authentication.getName(); // comes from JWT
	    Orders orders = orderService.createOrder(orderRequest, username);
	    return ResponseEntity.ok(orders);
	}
	
	@GetMapping
	public ResponseEntity<List<Orders>> list(Authentication authentication) {
	    String username = authentication.getName(); // comes from JWT token

	    // fetch only this user's orders
	    List<Orders> orders = ordersRepository.findByPlacedByUsername(username);

	    return ResponseEntity.ok(orders);
	}

    // GET /api/orders/{id} → Get order details
    @GetMapping("/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    // PUT /api/orders/{id}/status → Update status
    @PutMapping("/{id}/status")
    public ResponseEntity<Orders> updateStatus(@PathVariable Long id,
                                               @RequestParam String status,
                                               Authentication authentication) {
        String username = authentication.getName();

        Orders updatedOrder = orderService.updateStatus(id, status, username);

        return ResponseEntity.ok(updatedOrder);
    }

	
    // DELETE /api/orders/{id} → Cancel order(s)
    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteOrdersByUserId(@PathVariable Long userId,
                                                  Authentication authentication) {
        String username = authentication.getName(); // from JWT

        orderService.deleteOrdersByUserId(userId, username);

        return ResponseEntity.ok("All orders for user " + userId + " deleted successfully.");
    }

}
