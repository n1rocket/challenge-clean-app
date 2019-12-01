package com.abuenoben.challenge.setup

import com.abuenoben.challenge.BuildConfig

object Constants {
    val LIMIT_TO_FLUSH: Int = 10
    const val ENVIRONMENT_MOCK = "MOCK"
    const val ENVIRONMENT_DEV = "DEV"
    const val ENVIRONMENT_TEST = "TEST"
    const val ENVIRONMENT_PRE = "PRE"
    const val ENVIRONMENT_PRO = "PRO"
    val isMock: Boolean = BuildConfig.ENVIRONMENT == ENVIRONMENT_MOCK
}
