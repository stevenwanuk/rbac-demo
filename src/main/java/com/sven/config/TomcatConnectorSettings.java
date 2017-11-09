package com.sven.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "connector")
public class TomcatConnectorSettings
{

    private int ajpredirectport;
    private int ajpport;
    private boolean enablelookups;
    private boolean allowtrace;
    private String proxyname;
    private int proxyport;
    private String scheme;
    private boolean secure;

    public int getAjpredirectport()
    {
        return ajpredirectport;
    }

    public void setAjpredirectport(final int ajpredirectport)
    {
        this.ajpredirectport = ajpredirectport;
    }

    public int getAjpport()
    {
        return ajpport;
    }

    public void setAjpport(final int ajpport)
    {
        this.ajpport = ajpport;
    }

    public boolean isEnablelookups()
    {
        return enablelookups;
    }

    public void setEnablelookups(final boolean enablelookups)
    {
        this.enablelookups = enablelookups;
    }

    public boolean isAllowtrace()
    {
        return allowtrace;
    }

    public void setAllowtrace(final boolean allowtrace)
    {
        this.allowtrace = allowtrace;
    }

    public String getProxyname()
    {
        return proxyname;
    }

    public void setProxyname(final String proxyname)
    {
        this.proxyname = proxyname;
    }

    public String getScheme()
    {
        return scheme;
    }

    public void setScheme(final String scheme)
    {
        this.scheme = scheme;
    }

    public int getProxyport()
    {
        return proxyport;
    }

    public void setProxyport(final int proxyport)
    {
        this.proxyport = proxyport;
    }

    @Override
    public String toString()
    {
        return "TomcatConnectorSettings [ajpredirectport=" + ajpredirectport +
                ", ajpport=" + ajpport +
                ", proxyname=" + proxyname +
                ", proxyport=" + proxyport +
                ", enablelookups=" + enablelookups +
                ", allowtrace=" + allowtrace +
                ", scheme=" + scheme +
                "]";
    }

    public boolean isSecure()
    {
        return secure;
    }

    public void setSecure(final boolean secure)
    {
        this.secure = secure;
    }
}
