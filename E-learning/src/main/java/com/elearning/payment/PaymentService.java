package com.elearning.payment;

import com.elearning.Courses.Course;
import com.elearning.Courses.CourseState;
import com.elearning.Exceptions.BadRequestException;
import com.elearning.External.PaymentGateWayExternal.GeneratePaymentSessionFactory;
import com.elearning.External.PaymentGateWayExternal.Model.PaymentGatewayLineItem;
import com.elearning.External.PaymentGateWayExternal.Model.PaymentGatewayOrderModel;
import com.elearning.External.PaymentGateWayExternal.Model.PaymentSession;
import com.elearning.External.PaymentGateWayExternal.Model.SessionGenerationCommand;
import com.elearning.External.PaymentGateWayExternal.PaymentGatewayUtils;
import com.elearning.UserEnroll.IUserEnrollmentService;
import com.elearning.Videos.EnSortDir;
import com.elearning.entities.users.User;
import com.elearning.payment.dto.PaymentSessionDto;
import com.elearning.payment.interfaces.PaymentSessionService;
import com.elearning.payment.interfaces.PurchaseEligibilityService;
import com.elearning.util.Money;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class PaymentService implements PaymentSessionService , PurchaseEligibilityService {

    private final PaymentJpaRepo paymentJpaRepo;
    private final IUserEnrollmentService userEnrollmentService;
    private final GeneratePaymentSessionFactory paymentSessionFactory;
    private final PaymentGatewayUtils paymentGatewayUtils;
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

    @Override
    public boolean canBuy(User user, Course course) {
        if(course.getInstructor().getId().equals(user.getId()))
            return false;
        return course.getState() == CourseState.PUBLISHED && !userEnrollmentService.isEnrolled(user.getId(), course.getId());
    }

    public Payment create(User user ,Course course,PaymentMethod method ,Money totalAmount , PaymentStatus status){
        var payment = new Payment();
        payment.setUser(user);
        payment.setCourse(course);
        payment.setMethod(method);
        payment.setTotalAmount(totalAmount);
        payment.setStatus(status);

        return payment;
    }

    private PaymentGatewayOrderModel toOrderModel(User user,Course course)
    {
        var item = PaymentGatewayLineItem.builder().
                itemName(course.getTitle()).
                itemDescription(null)
                .currency(course.getPrice().getCurrency())
                .quantity(1)
                .finalAmountInCents(course.getPrice().getPriceInCents())
                .imgesUrl(null)
                .build();
        Map<String,String> metaData = new HashMap<>();
        metaData.put("usrId",user.getId().toString());
        metaData.put("courseId",course.getId().toString());

        return PaymentGatewayOrderModel.builder()
                .metaData(metaData)
                .items(Collections.singletonList(item)).build();

    }
    @Override
    public PaymentSessionDto createPaymentSession(User user, Course course, PaymentMethod method) {
        if(!canBuy(user,course))
            throw new BadRequestException("User is not allowed to buy this course");



        SessionGenerationCommand command = SessionGenerationCommand.builder()
                .orderModel(toOrderModel(user,course))
                .expireAfter(Duration.ofHours(2L))
                .successUrl(null)
                .failUrl(null).
                method(method)
                .build();
        var sessionGenerator = paymentSessionFactory.getStratigy(method);

        PaymentSession session = paymentGatewayUtils.rateLimitRetry(() -> sessionGenerator.generateSessionUrl(command)) ;

        Payment payment = create(user,course,method,course.getPrice(),PaymentStatus.PENDING);
        payment.setSessionId(session.getSession_id());
        paymentJpaRepo.save(payment);

        return PaymentSessionDto.builder()
                .session_id(session.getSession_id())
                .session_url(session.getSession_url())
                .expireAt(session.getExpireAt())
                .build();
    }
}
