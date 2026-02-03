package com.iot.dashboard.service;

import com.iot.dashboard.entity.DeviceData;
import com.iot.dashboard.repository.DeviceDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceDataService {

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    public List<DeviceData> getAllData() {
        return deviceDataRepository.findAll();
    }

    public DeviceData saveData(DeviceData data) {
        return deviceDataRepository.save(data);
    }

    public List<DeviceData> getLatestData(String deviceId, int limit) {
        List<DeviceData> allData = deviceDataRepository.findByDeviceIdOrderByTimestampDesc(deviceId);
        // Get latest 'limit' records
        return allData.stream().limit(limit).toList();
    }
}