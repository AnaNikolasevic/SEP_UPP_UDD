package com.project.paypal.repository;

import com.project.paypal.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    public Subscription findOneByAggrementToken(String token);

    @Query("select subscription from Subscription subscription where subscription.status = :status")
    public List<Subscription> getSubscriptionsByStatus(@Param("status") String status);
}
