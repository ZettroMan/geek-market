package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.entities.Order;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.services.OrderService;
import com.geekbrains.geek.market.services.UserService;
import com.geekbrains.geek.market.utils.Cart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {
    private UserService userService;
    private OrderService orderService;
    private Cart cart;

    @GetMapping
    public String showUserOrders(Model model, Principal principal) {
        model.addAttribute("orders", orderService.findAllByUserName(principal.getName()));
        model.addAttribute("username", principal.getName());
        return "user_orders";
    }

    @GetMapping("/all")
    public String showAllOrders(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/create")
    public String showOrderPage(Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        return "create_order";
    }

    @PostMapping("/confirm")
    public String confirmOrder(Principal principal,
                              @RequestParam(name = "address") String address,
                              @RequestParam(name = "receiver_name") String receiverName,
                              @RequestParam(name = "phone_number") String phone
                              ) {
        User user = userService.findByUsername(principal.getName());
        Order order = new Order(user, cart, address);
        orderService.save(order);
        return "redirect:/orders";
    }
}
