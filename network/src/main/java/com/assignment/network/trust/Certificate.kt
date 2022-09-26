package com.assignment.network.trust

import okhttp3.OkHttpClient
import okhttp3.tls.HandshakeCertificates
import okhttp3.tls.decodeCertificatePem
import java.security.cert.X509Certificate

object Certificate {

    // apply SSL certificate
    // for now dont have certificate
    fun OkHttpClient.Builder.applyCertificateIfNeeded(certificateString: String) = apply {
        if (certificateString.isEmpty()) return this@applyCertificateIfNeeded
        val handShakeCertificates = with(HandshakeCertificates.Builder()) {
            val certificate: X509Certificate = certificateString.decodeCertificatePem()
            addTrustedCertificate(certificate)
            build()
        }
        sslSocketFactory(
            handShakeCertificates.sslSocketFactory(),
            handShakeCertificates.trustManager,
        ).build()
    }
}