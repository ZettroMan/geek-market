package com.geekbrains.geek.market.repositories;

import com.geekbrains.geek.market.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Long> {
//    @Query("select o from Order o where o.customer.id = ?1")
//    List<Order> findAllByCustomerId(Long customerId);

}
