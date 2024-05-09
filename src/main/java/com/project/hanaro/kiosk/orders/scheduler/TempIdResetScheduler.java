package com.project.hanaro.kiosk.orders.scheduler;

import com.project.hanaro.kiosk.orders.domain.TempIdManager;
import com.project.hanaro.kiosk.orders.repository.OrderRepository;
import com.project.hanaro.kiosk.orders.repository.TempIdManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TempIdResetScheduler {
    private final OrderRepository orderRepository;
    private final TempIdManagerRepository tempIdManagerRepository;


    // 매일 자정
//    @Scheduled(cron = "0 0 0 * * ?")
    @Scheduled(cron = "0 * * * * ?") //test용 1분
    public void resetTempId() {
        TempIdManager tempIdManager = tempIdManagerRepository.findById(1L)
                .orElse(TempIdManager.builder().currentTempId(0).build());
        tempIdManager.setCurrentTempId(0); // 자정이 되면 temp_id를 1로 리셋
        tempIdManagerRepository.save(tempIdManager);
        System.out.println("주문번호 리셋완료.");
    }
}
