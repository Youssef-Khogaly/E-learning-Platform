package com.elearning.payment;


import com.elearning.Videos.EnSortDir;
import com.elearning.payment.dto.PaymentDto;
import com.elearning.payment.mappers.PaymentDtoMapper;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDtoMapper paymentDtoMapper;

    @GetMapping("/me/purchase-history/")
    ResponseEntity<Page<PaymentDto>> getPurchaseHistory(@RequestParam(name = "page") @Positive Integer page
                                                        , @RequestParam(name = "size") @Range(min = 5,max = 20) Integer size)
    {
        var payments = paymentService.getPurchaseHistory(1L,size,page,EnPaymentSoryBy.CREATEDAT, EnSortDir.DES);
        var dtos = payments.map(paymentDtoMapper::from);
        return ResponseEntity.ok(dtos);
    }
}
