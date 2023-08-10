package com.system.swimmingpool_management_system.Service;

import com.system.swimmingpool_management_system.Pojo.SwimmingpoolPojo;
import com.system.swimmingpool_management_system.entity.Swimmingpool;

import java.io.IOException;
import java.util.List;

public interface SwimmingpoolService {
    SwimmingpoolPojo saveswimmingpool(SwimmingpoolPojo swimmingpoolPojo) throws IOException;

    Swimmingpool fetchById(Integer id);

    List<Swimmingpool> fetchAll();

    void deleteById(Integer id);
}
