//package com.system.swimmingpool_management_system;
//
//import com.system.swimmingpool_management_system.Repo.SwimmingpoolRepo;
//import com.system.swimmingpool_management_system.entity.Swimmingpool;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.core.annotation.Order;
//
//@DataJpaTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
//public class swimmingpoolRepositoryTest {
//    @Autowired
//    private SwimmingpoolRepo swimmingpoolRepo;
//
//
//    @Test
//    @Order(1)
//    public void saveswimmingpoolTest() {
//
//        Swimmingpool swimmingpool = Swimmingpool.builder()
//                .swimmingpoolname("rak")
//                .swimmingpoolcontact("123454")
//                .swimmingpoolprice("98888888")
//                .build();
//
//
//        swimmingpoolRepo.save(swimmingpool);
//
//        Assertions.assertThat(swimmingpool.getFut_salId()).isGreaterThan(0);
//    }
//
//    @Test
//    @Order(4)
//    public void updateswimmingpoolTest(){
//
//        Swimmingpool swimmingpool = Swimmingpool.builder()
//                .swimmingpoolname("rak")
//                .swimmingpoolcontact("123454")
//                .swimmingpoolprice("98888888")
//                .build();
//
//
//        swimmingpoolRepo.save(swimmingpool);
//
//        Swimmingpool swimmingpool1  = swimmingpoolRepo.findById(swimmingpool.getFut_salId()).get();
//
//        swimmingpool1.setswimmingpoolcontact("13265");
//
//        Swimmingpool swimmingpoolupdated  = swimmingpoolRepo.save(swimmingpool);
//
//        Assertions.assertThat(swimmingpoolupdated.getswimmingpoolcontact()).isEqualTo("85207410");
//
//    }
//}