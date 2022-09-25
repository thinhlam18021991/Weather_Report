package com.assignment.nab.presentation

import com.assignment.shared.SharedApplication
import com.assignment.shared.SharedModuleMigration

class NabApplication: SharedApplication() {

    override fun onCreate() {
        super.onCreate()
        SharedModuleMigration.application = this
    }
}