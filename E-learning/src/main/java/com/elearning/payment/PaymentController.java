package com.elearning.payment;


import com.elearning.Courses.CourseService;
import com.elearning.Security.services.AuthenticationService;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import com.elearning.payment.dto.CreatePaymentSessionReq;
import com.elearning.payment.dto.PaymentDto;
import com.elearning.payment.dto.PaymentSessionDto;
import com.elearning.payment.mappers.PaymentDtoMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Validated
public class PaymentController {

    private final PaymentService paymentService;
    private final PaymentDtoMapper paymentDtoMapper;
    private final CourseService courseService;
    @GetMapping("/payment/purchase-history/")
    ResponseEntity<Page<PaymentDto>> getPurchaseHistory(@RequestParam(name = "page") @PositiveOrZero Integer page
                                                        , @RequestParam(name = "size") @Range(min = 5,max = 20) Integer size)
    {
        var currentUsr = AuthenticationService.getCurrentUser();
        if(currentUsr.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        var payments = paymentService.getPurchaseHistory(currentUsr.get().getId(),size,page,EnPaymentSoryBy.CREATEDAT, EnSortDir.DES);
        var dtos = payments.map(paymentDtoMapper::from);
        return ResponseEntity.ok(dtos);
    }


    @PostMapping("/payment/check-out")
    ResponseEntity<PaymentSessionDto> createPaymentSession(@RequestBody @Valid CreatePaymentSessionReq req)
    {
        var course = courseService.findByIdOnly(req.courseId());
        var currentUser = AuthenticationService.getCurrentUser();
        if(currentUser.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

        var ret = paymentService.createPaymentSession(currentUser.get(),course,req.method());

        return ResponseEntity.ok(ret);
    }
}
