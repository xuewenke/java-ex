

package com.x.atrs.util.system;

import lombok.experimental.UtilityClass;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.hardware.NetworkIF;

import java.util.List;

/**
 * @author xuewenke
 * @since 2020/9/10 11:58 上午
 */
@UtilityClass
public class SystemMessageUtil {


    public void main(String[] args) {
        HardwareAbstractionLayer hardware = OshiUtil.getHardware();
        List<NetworkIF> iFs = hardware.getNetworkIFs();
        for (NetworkIF anIf : iFs) {
            System.out.println(anIf.getSpeed());
            System.out.println(anIf.isKnownVmMacAddr());
            System.out.println(anIf);
        }
    }


}