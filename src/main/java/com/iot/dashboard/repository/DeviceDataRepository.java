package com.iot.dashboard.repository;

import com.iot.dashboard.entity.DeviceData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceDataRepository extends JpaRepository<DeviceData, Long> {
    List<DeviceData> findByDeviceIdOrderByTimestampDesc(String deviceId);
}