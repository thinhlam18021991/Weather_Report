package com.assignment.security

class SecurityNabImpl: SecurityNab {

    override fun appId(): String = SecurityHelper.appId()
}