package org.bouncycastle.jsse.provider;

import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSession;

import org.bouncycastle.jsse.BCExtendedSSLSession;
import org.bouncycastle.jsse.BCSSLEngine;
import org.bouncycastle.jsse.BCSSLParameters;

abstract class SSLEngineUtil
{
    static ProvSSLEngine create(ProvSSLContextSpi context, ContextData contextData)
    {
        return new ProvSSLEngine_9(context, contextData);
    }

    static ProvSSLEngine create(ProvSSLContextSpi context, ContextData contextData, String host, int port)
    {
        return new ProvSSLEngine_9(context, contextData, host, port);
    }

    static BCExtendedSSLSession importHandshakeSession(SSLEngine sslEngine)
    {
        if (sslEngine instanceof BCSSLEngine)
        {
            return ((BCSSLEngine)sslEngine).getBCHandshakeSession();
        }
        if (null != sslEngine)
        {
            SSLSession sslSession = sslEngine.getHandshakeSession();
            if (null != sslSession)
            {
                return SSLSessionUtil.importSSLSession(sslSession);
            }
        }
        return null;
    }

    static BCSSLParameters importSSLParameters(SSLEngine sslEngine)
    {
        if (sslEngine instanceof BCSSLEngine)
        {
            return ((BCSSLEngine)sslEngine).getParameters();
        }
        if (null != sslEngine)
        {
            SSLParameters sslParameters = sslEngine.getSSLParameters();
            if (null != sslParameters)
            {
                return SSLParametersUtil.importSSLParameters(sslParameters);
            }
        }
        return null;
    }
}
