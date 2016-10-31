package com.tubb.multichannel

public class AppChannelExtension{
    /**
     * channel config file path
     */
    String channelFilePath
    /**
     * custom productFlavor
     */
    Closure buildProductFlavor
    /**
     * disable debug task
     */
    boolean disableDebugTask
    /**
     * disable lint task
     */
    boolean disableLintTask
    /**
     * disable test task
     */
    boolean disableTestTask
    /**
     * apk output dir
     */
    String outputDir
    /**
     * config apk file name
     */
    Closure buildOutputFileName
}