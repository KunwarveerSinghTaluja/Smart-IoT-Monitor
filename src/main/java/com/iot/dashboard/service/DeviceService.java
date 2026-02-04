package com.iot.dashboard.service;

import com.iot.dashboard.entity.Device;
import com.iot.dashboard.repository.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device getDeviceById(Long id) {
        Optional<Device> device = deviceRepository.findById(id);
        if (device.isPresent()) {
            return device.get();
        } else {
            throw new RuntimeException("Device not found with id: " + id);
        }
    }

    public Device createDevice(Device device) {
        if (device.getDeviceId() == null || device.getDeviceId().isEmpty()) {
            device.setDeviceId("DEV-" + System.currentTimeMillis());
        }
        if (device.getStatus() == null) {
            device.setStatus("offline");
        }
        device.setLastSeen(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public Device updateDevice(Long id, Device deviceDetails) {
        Device device = getDeviceById(id);
        if (deviceDetails.getName() != null) {
            device.setName(deviceDetails.getName());
        }
        if (deviceDetails.getType() != null) {
            device.setType(deviceDetails.getType());
        }
        if (deviceDetails.getLocation() != null) {
            device.setLocation(deviceDetails.getLocation());
        }
        if (deviceDetails.getStatus() != null) {
            device.setStatus(deviceDetails.getStatus());
        }
        if (deviceDetails.getIpAddress() != null) {
            device.setIpAddress(deviceDetails.getIpAddress());
        }
        return deviceRepository.save(device);
    }

    public void deleteDevice(Long id) {
        Device device = getDeviceById(id);
        deviceRepository.delete(device);
    }

    public Map<String, Object> getStatusSummary() {
        List<Device> allDevices = getAllDevices();

        Map<String, Object> summary = new HashMap<>();

        int onlineCount = (int) allDevices.stream().filter(d -> "online".equals(d.getStatus())).count();
        int offlineCount = (int) allDevices.stream().filter(d -> "offline".equals(d.getStatus())).count();
        int warningCount = (int) allDevices.stream().filter(d -> "warning".equals(d.getStatus())).count();

        summary.put("totalDevices", allDevices.size());
        summary.put("onlineDevices", onlineCount);
        summary.put("offlineDevices", offlineCount);
        summary.put("warningDevices", warningCount);
        summary.put("activeAlerts", 0);

        int total = allDevices.size();
        double uptime = total > 0 ? (onlineCount * 100.0) / total : 0;
        summary.put("uptimePercentage", String.format("%.1f%%", uptime));

        return summary;
    }

    public Device updateDeviceStatus(Long id, String status) {
        Device device = getDeviceById(id);
        device.setStatus(status);
        device.setLastSeen(LocalDateTime.now());
        return deviceRepository.save(device);
    }

    public List<Device> getDevicesByType(String type) {
        return deviceRepository.findByType(type);
    }

    public List<Device> getOnlineDevices() {
        return deviceRepository.findByStatus("online");
    }
}