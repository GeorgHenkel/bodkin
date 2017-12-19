package de.georghenkel.bodkin.domain.configuration.service;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.slf4j.Logger;
import org.yaml.snakeyaml.Yaml;
import de.georghenkel.bodkin.domain.configuration.model.Collection;
import de.georghenkel.bodkin.domain.configuration.model.Configuration;
import de.georghenkel.bodkin.domain.configuration.model.Default;

@ApplicationScoped
public class YamlConfigReader {
  @Inject
  Logger log;

  @SuppressWarnings("unchecked")
  public Configuration readConfig(final InputStream configFileStream) {
    Configuration config = new Configuration();

    Yaml yaml = new Yaml();
    Map<String, Object> configData = (Map<String, Object>) yaml.load(configFileStream);

    parseServerSettings(config, configData);
    parseBuildSettings(config, configData);

    if (configData.containsKey("baseurl")) {
      config.setBaseurl((String) configData.get("baseurl"));
      configData.remove("baseurl");
    }

    config.setValues(configData);

    return config;
  }

  @SuppressWarnings("unchecked")
  private void parseServerSettings(final Configuration config,
      final Map<String, Object> configData) {
    if (configData.containsKey("server")) {
      Map<String, Object> serverValues = (Map<String, Object>) configData.get("server");
      if (serverValues.containsKey("port")) {
        int port = (Integer) serverValues.get("port");
        config.getServer().setPort(port);
      }

      if (serverValues.containsKey("host")) {
        config.getServer().setHost((String) serverValues.get("host"));
      }
    }

    configData.remove("server");
  }

  @SuppressWarnings("unchecked")
  private void parseBuildSettings(final Configuration config,
      final Map<String, Object> configData) {
    if (configData.containsKey("defaults")) {
      List<Map<String, Object>> defaultsData =
          (List<Map<String, Object>>) configData.get("defaults");
      defaultsData.forEach(defaultData -> {
        if (defaultData.containsKey("scope") && defaultData.containsKey("values")) {
          Default defaultValue = new Default();

          Map<String, String> scopeData = (Map<String, String>) defaultData.get("scope");
          String path = scopeData.containsKey("path") ? scopeData.get("path") : "";
          defaultValue.setPath(path);
          String type = scopeData.containsKey("type") ? scopeData.get("type") : "";
          defaultValue.setType(type);

          Map<String, Object> valuesData = (Map<String, Object>) defaultData.get("values");
          defaultValue.setValues(valuesData);

          config.getDefaults().add(defaultValue);
        } else {
          if (!defaultData.containsKey("scope")) {
            log.warn("defaults must contain a scope");
          }

          if (!defaultData.containsKey("values")) {
            log.warn("defaults must contain values");
          }
        }

      });
    }

    if (configData.containsKey("collections")) {
      Map<String, Object> collectionData = (Map<String, Object>) configData.get("collections");
      collectionData.forEach((key, value) -> {
        Map<String, Object> valueData = (Map<String, Object>) value;

        boolean output =
            valueData.containsKey("output") ? (Boolean) valueData.get("output") : false;
        String permalink =
            valueData.containsKey("permalink") ? (String) valueData.get("permalink") : "";

        Collection collection = new Collection(key, output, permalink);
        config.getBuild().getCollections().add(collection);
      });
    }

    configData.remove("defaults");
    configData.remove("collections");
  }
}
