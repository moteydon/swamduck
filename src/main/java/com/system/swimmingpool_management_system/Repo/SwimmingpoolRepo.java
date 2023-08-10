package com.system.swimmingpool_management_system.Repo;

import com.system.swimmingpool_management_system.entity.Swimmingpool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SwimmingpoolRepo extends JpaRepository <Swimmingpool, Integer>{
}
