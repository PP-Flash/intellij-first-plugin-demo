package com.chenglong.tools

import com.github.nanbiango.services.NacosSyncDevService

/**
 * ...
 */
fun main() {
//    NacosSyncDevService.login()

//    NacosSyncDevService.getConfig("mps-order-joom-service.yml", "dev_mps_group")

    NacosSyncDevService.publishConfig("test222", "test_nacos_config.yaml")
}