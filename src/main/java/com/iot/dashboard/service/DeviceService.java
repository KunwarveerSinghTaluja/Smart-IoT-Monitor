package com.iot.dashboard.service;

import com.iot.dashboard.entity.Device;
import com.iot.dashboard.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        return deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Device not found with id: " + id));
    }

    public Device createDevice(Device device) {
        // Generate deviceId if not provided
        if (device.getDeviceId() == null || device.getDeviceId().isEmpty()) {
            device.setDeviceId("device-" + System.currentTimeMillis());
        }
        return deviceRepository.save(device);
    }

    public Device updateDevice(Long id, Device deviceDetails) {
        Device device = getDeviceById(id);
        device.setName(deviceDetails.getName());
        device.setType(deviceDetails.getType());
        device.setLocation(deviceDetails.getLocation());
        device.setStatus(deviceDetails.getStatus());
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        Device device = getDeviceById(id);
        deviceRepository.delete(device);
    }
}