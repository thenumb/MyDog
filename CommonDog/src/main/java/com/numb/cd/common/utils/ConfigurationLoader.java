package com.numb.cd.common.utils;

import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.ReloadingFileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.FileBasedBuilderParameters;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.convert.DefaultListDelimiterHandler;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.reloading.PeriodicReloadingTrigger;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ConfigurationLoader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigurationLoader.class);
    private static final File propertiesFile = new File("application.properties");
    private static final Parameters params = new Parameters();
    private static final FileBasedBuilderParameters fileBasedBuilderParameters = params.fileBased()
            .setFile(propertiesFile)
            .setEncoding("UTF-8")
            .setListDelimiterHandler(new DefaultListDelimiterHandler(','))
            .setThrowExceptionOnMissing(true);
    private static final ReloadingFileBasedConfigurationBuilder<PropertiesConfiguration> builder = new ReloadingFileBasedConfigurationBuilder<>(
            PropertiesConfiguration.class)
            // 使用FileBasedBuilderParameters
            .configure(fileBasedBuilderParameters);
    private static final PeriodicReloadingTrigger trigger = new PeriodicReloadingTrigger(builder.getReloadingController(), null, 1,
            TimeUnit.SECONDS);


    public static void initConfigurationLoader() {
        trigger.start();
    }

    public static String getString(String key, String defValue) {
        if (StringUtils.isBlank(key)) {
            return defValue;
        }
        String value;
        try {
            value = builder.getConfiguration().getString(key);
        } catch (ConfigurationException | NoSuchElementException e) {
            LOGGER.error("load config value error:[{}]", ExceptionUtils.getMessage(e));
            return defValue;
        }
        return value;
    }
}
