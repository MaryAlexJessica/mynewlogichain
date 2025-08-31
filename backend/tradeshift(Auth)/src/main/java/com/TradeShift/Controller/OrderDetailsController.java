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

import com.TradeShift.Entity.OrderDetails;
import com.TradeShift.Service.OrderDetailsService;

@RestController
@RequestMapping("/api/auth/oD")
public class OrderDetailsController {

	    @Autowired
	    private OrderDetailsService orderDetailsService;

	    // GET /api/orders/{id}/details → Get items in an order
	    @GetMapping("/orders/{id}/details")
	    public ResponseEntity<List<OrderDetails>> getOrderDetails(@PathVariable Long id,
	                                                              Authentication authentication) {
	        String username = authentication.getName();
	        return ResponseEntity.ok(orderDetailsService.getOrderDetails(id, username));
	    }

	    // POST /api/orders/{id}/details → Add product to an order
	    @PostMapping("/orders/{id}/details")
	    public ResponseEntity<OrderDetails> addProduct(@PathVariable Long id,
	                                                   @RequestBody Long productId,
	                                                   @RequestParam int qty,
	                                                   Authentication authentication) {
	        String username = authentication.getName();
	        return ResponseEntity.ok(orderDetailsService.addProductToOrder(id, productId, qty, username));
	    }

	    // PUT /api/order-details/{id} → Update quantity
	    @PutMapping("/order-details/{id}")
	    public ResponseEntity<OrderDetails> updateQuantity(@PathVariable Long id,
	                                                       @RequestParam int qty,
	                                                       Authentication authentication) {
	        String username = authentication.getName();
	        return ResponseEntity.ok(orderDetailsService.updateQuantity(id, qty, username));
	    }

	    // DELETE /api/order-details/{id} → Remove product from order
	    @DeleteMapping("/order-details/{id}")
	    public ResponseEntity<String> delete(@PathVariable Long id,
	                                         Authentication authentication) {
	        String username = authentication.getName();
	        orderDetailsService.deleteOrderDetail(id, username);
	        return ResponseEntity.ok("ID : " + id + "\nOrder detail deleted successfully.");
	    }
}
