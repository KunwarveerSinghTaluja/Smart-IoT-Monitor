package com.iot.dashboard.controller;

import com.iot.dashboard.entity.DeviceData;
import com.iot.dashboard.service.DeviceDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/data")
@CrossOrigin(origins = "*")
public class DataController {

    @Autowired
    private DeviceDataService deviceDataService;

    @GetMapping("/all")
    public List<DeviceData> getAllData() {
        return deviceDataService.getAllData();
    }

    @PostMapping
    public DeviceData saveData(@RequestBody DeviceData data) {
        return deviceDataService.saveData(data);
    }

    @GetMapping("/device/{deviceId}")
    public List<DeviceData> getDeviceData(@PathVariable String deviceId) {
        return deviceDataService.getLatestData(deviceId, 50);
    }
}