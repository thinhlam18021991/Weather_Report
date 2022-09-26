package com.assignment.nab.presentation

import com.assignment.shared.SharedApplication
import com.assignment.shared.SharedModuleMigration
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NabApplication: SharedApplication() {

    override fun onCreate() {
        super.onCreate()
        SharedModuleMigration.application = this
    }
}