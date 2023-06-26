package com.ehrengrenlund.mysticenchants.configuration;

import com.ehrengrenlund.mysticenchants.utilities.MysticLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.*;

public class MysticConfig {
    private final MysticLogger logger = MysticLogger.getInstance();
    private BaseConfiguration config;
    private static MysticConfig instance;
    private static final String CONFIG_NAME = "MysticEnchants.json";

    private MysticConfig() { }

    /**
     * Singleton instance getter
     *
     * @return Returns the current instance of the config
     */
    public static MysticConfig getInstance() {
        if (instance == null) {
            return new MysticConfig();
        }

        return instance;
    }

    public int loadConfiguration() {
        File configurationFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME).toFile();

        try {
            Gson gson = new Gson();
            BaseConfiguration configObject = gson.fromJson(new FileReader(configurationFile), BaseConfiguration.class);

            if (configObject == null)
                throw new Exception("Failed to parse config");

            this.config = configObject;
        } catch (FileNotFoundException e) {
            this.logger.warn("File not found, creating new config.");
            this.config = new BaseConfiguration();

            if (this.saveConfiguration() == Status.CONFIG_ERROR) {
                this.logger.error("Failed to save config");
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage());
            return Status.CONFIG_ERROR;
        }

        return Status.CONFIG_SUCCESS;
    }

    public int saveConfiguration() {
        try {
            File configurationFile = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME).toFile();
            FileWriter writer = new FileWriter(configurationFile);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();

            gson.toJson(this.config, writer);
            writer.close();
        } catch (IOException e) {
            logger.error("Failed to save config.");
        }

        return Status.CONFIG_SUCCESS;
    }

    public boolean getFrostEnchantEnabled() {
        return this.config.frostEnchant;
    }

    public boolean getBountyEnchantEnabled() {
        return this.config.bountyEnchant;
    }

    public boolean getToxicBladeEnabled() {
        return this.config.poisonEnchant;
    }

    public boolean getChargedBladeEnabled() {
        return this.config.chargedBlade;
    }

    public int getBountyBaseExperience() {
        return this.config.bountyBaseExperience;
    }

    public int getToxicBladeDuration() {
        return this.config.toxicBladeDuration;
    }

    public int getChargedBladeChance() {
        return this.config.chargedBladeChance;
    }

    public static class Status {
        public static final int CONFIG_SUCCESS = 0;
        public static final int CONFIG_ERROR = 1;
    }
}
