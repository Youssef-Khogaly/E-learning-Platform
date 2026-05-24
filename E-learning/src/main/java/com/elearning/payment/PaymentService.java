package com.elearning.payment;

import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@AllArgsConstructor
public class PaymentService {



    private final PaymentJpaRepo paymentJpaRepo;


    public final Page<Payment> getPurchaseHistory(long userId, int pageSize, int page, EnPaymentSoryBy enSortBy , EnSortDir dir){

        Sort sort = Sort.by(dir.toDirection(),enSortBy.toString());
        Pageable pageable = PageRequest.of(page,pageSize,sort);
        User usrProb = new User();
        usrProb.setId(userId);
        Payment paymentRoot = new Payment();
        paymentRoot.setUser(usrProb);

        Example<Payment> paymentExample = Example.of(paymentRoot);
        return paymentJpaRepo.findAll(paymentExample,pageable);
    }
}
