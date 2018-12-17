/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.test.oshitest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import oshi.SystemInfo;
import oshi.hardware.Baseboard;
import oshi.hardware.ComputerSystem;
import oshi.hardware.Firmware;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HWDiskStore;
import oshi.hardware.HWPartition;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

/**
 * Produces the following
 * 
 * setting timeout
get inventory
startup
hal
loop
10:50:29,539 WARN  [windows.PerfDataUtil] Failed to get counter. Error code: 0xC0000BBC
10:50:29,540 WARN  [windows.PerfDataUtil] Removing and re-adding counter: \Memory\Pages Input/sec
loop
 * 
 */
public class OshiDriver1 {

    public static void main(String[] args) throws Exception {
        configureLog4j("log4j2.xml");
        System.out.println("setting timeout");
        oshi.util.platform.windows.WmiUtil.setWmiTimeout(500);
        System.out.println("get inventory");
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        ComputerSystem computerSystem = hal.getComputerSystem();
 
        Baseboard baseboard = computerSystem.getBaseboard();
        Firmware firmware = computerSystem.getFirmware();

        computerSystem.getManufacturer();
        computerSystem.getModel();
        computerSystem.getSerialNumber();
        baseboard.getManufacturer();
        baseboard.getModel();
        baseboard.getSerialNumber();
        baseboard.getVersion();
        //ok
      firmware.getManufacturer();
        firmware.getDescription();
        firmware.getName();
        firmware.getVersion();
        firmware.getReleaseDate();
        //ok
     //COMMENTED OUT hal.getProcessor().isCpu64bit();
        //yatzee
         
           si.getOperatingSystem().getManufacturer();
        si.getOperatingSystem().getFamily();
       
        si.getOperatingSystem().getVersion().getBuildNumber();
        si.getOperatingSystem().getVersion().getCodeName();
        si.getOperatingSystem().getVersion().getVersion();
        

        if (si.getHardware() != null && si.getHardware().getComputerSystem() != null && si.getHardware().getComputerSystem().getModel() != null) {
            si.getHardware().getComputerSystem().getModel();

        }
        if (si.getHardware() != null && si.getHardware().getComputerSystem() != null && si.getHardware().getComputerSystem().getSerialNumber() != null) {
            si.getHardware().getComputerSystem().getSerialNumber();

        }

        if (si.getHardware() != null && si.getHardware().getComputerSystem() != null && si.getHardware().getComputerSystem().getBaseboard() != null) {
            if (si.getHardware().getComputerSystem().getBaseboard().getManufacturer() != null) {
                si.getHardware().getComputerSystem().getBaseboard().getManufacturer();

            }

            if (si.getHardware().getComputerSystem().getBaseboard().getModel() != null) {
                si.getHardware().getComputerSystem().getBaseboard().getModel();
            }

            if (si.getHardware().getComputerSystem().getBaseboard().getSerialNumber() != null) {
                si.getHardware().getComputerSystem().getBaseboard().getSerialNumber();

            }

            if (si.getHardware().getComputerSystem().getBaseboard().getVersion() != null) {
                si.getHardware().getComputerSystem().getBaseboard().getVersion();

            }

            if (si.getHardware() != null && si.getHardware().getComputerSystem() != null && si.getHardware().getComputerSystem().getFirmware() != null) {

                if (si.getHardware().getComputerSystem().getFirmware().getManufacturer() != null) {
                    si.getHardware().getComputerSystem().getFirmware().getManufacturer();

                }

                if (si.getHardware().getComputerSystem().getFirmware().getDescription() != null) {
                    si.getHardware().getComputerSystem().getFirmware().getDescription();

                }

                if (si.getHardware().getComputerSystem().getFirmware().getName() != null) {
                    si.getHardware().getComputerSystem().getFirmware().getName();

                }

                if (si.getHardware().getComputerSystem().getFirmware().getVersion() != null) {
                    si.getHardware().getComputerSystem().getFirmware().getVersion();

                }

                if (si.getHardware().getComputerSystem().getFirmware().getReleaseDate() != null) {

                    if (si.getHardware().getComputerSystem().getFirmware().getReleaseDate() != null) {
                        si.getHardware().getComputerSystem().getFirmware().getReleaseDate().toString();

                    }
                }

            }
        }
        HWDiskStore[] drives2 = hal.getDiskStores();

        for (HWDiskStore h : drives2) {

            for (HWPartition p : h.getPartitions()) {
                //should we list unmounted drives?
                if (p.getMountPoint() != null) {

                    String s = (p.getUuid() + p.getIdentification());
                    s = (h.getModel() + " " + p.getType());
                    s = "" + (p.getSize());
                    s = "" + p.getMountPoint();

                }

            }

        }

        System.out.println("startup");

        System.out.println("hal");

        System.out.println("loop");

        while (true) {
            // hal = si.getHardware();
            GlobalMemory mem = hal.getMemory();
            //hal = si.getHardware();
            HWDiskStore[] drives = hal.getDiskStores();
            for (HWDiskStore d : drives) {
                d.getReadBytes();
                d.getWriteBytes();
                d.getTimeStamp();
            }
            // drives[0].getReadBytes();
            //drives[0].getWriteBytes();
            //Thread.sleep(1000);
            mem.getSwapPagesIn();
            mem.getSwapPagesOut();
            for (NetworkIF nic : hal.getNetworkIFs()) {
                nic.getBytesRecv();
                nic.getBytesSent();
                nic.getPacketsRecv();
                nic.getPacketsSent();
                nic.getTimeStamp();
            }
            //mem.getSwapPagesOut();
            //mem.getSwapTotal();
            //mem.getSwapUsed();
            System.out.println("loop");

        }
    }

    public static void configureLog4j(String filename) throws Exception {
        File cfg = new File(filename);
        if (cfg.exists()) {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(cfg);
                ConfigurationSource source = new ConfigurationSource(fis);
                Configurator.initialize(null, source);

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        } else {
            InputStream fis = null;
            try {
                fis = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
                if (fis != null) {
                    ConfigurationSource source = new ConfigurationSource(fis);
                    Configurator.initialize(null, source);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (fis != null) {
                    fis.close();
                }
            }
        }
    }
}
