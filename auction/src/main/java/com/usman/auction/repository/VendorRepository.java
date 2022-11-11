package com.usman.auction.repository;

import com.usman.auction.entities.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {
    @Query("select (count(v) > 0) from Vendor v where v.phoneNumber in ?1")
    boolean isPhoneNumberAvailable(Long phoneNumber);
}