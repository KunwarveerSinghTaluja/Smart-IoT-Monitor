package com.iot.dashboard.repository;

import com.iot.dashboard.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> findByStatus(String status);
    List<Device> findByType(String type);
    Device findByDeviceId(String deviceId);
}