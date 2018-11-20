package com.appzoneltd.lastmile.microservice.opensocket;

import io.vertx.core.json.JsonObject;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * @author alaa.nabil
 */
public class Configurations {

    private static final String FILE_NAME = "configurations.yml";
    private static Configurations configurations = new Configurations();
    private JsonObject properties;

    /**
     * @throws IOException
     */
    private Configurations() {
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        if (inputStream != null)
            properties = new JsonObject((Map<String, Object>) yaml.load(inputStream));
    }

    public static JsonObject getProperties() {
        return configurations.properties;
    }

    public static JsonObject getProperties(String key) {
        return configurations.properties.getJsonObject(key);
    }

    public static JsonObject getProperties(String... key) {
        JsonObject jsonObject = new JsonObject();
        for (int i = 0; i < key.length; i++) {
            jsonObject.put(key[i], configurations.properties.getJsonObject(key[i]));
        }
        return jsonObject;
    }

    public static Properties getKafkaProperties() {
        Properties kafkaPros = new Properties();
        kafkaPros.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                getProperties("kafka").getString(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG));
        kafkaPros.put(ConsumerConfig.GROUP_ID_CONFIG, getProperties("kafka").getString(ConsumerConfig.GROUP_ID_CONFIG) + getServerId());
        kafkaPros.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG,
                getProperties("kafka").getString(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG));
        kafkaPros.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaPros.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        kafkaPros.put("topic", getProperties("kafka").getString("topic"));
        return kafkaPros;
    }

    public static JsonObject getCouchbaseProperties() {
        return getProperties("couchbase");
    }

    public static Long getThreadSleepProperties() {
        return configurations.properties.getLong("thread.sleep");
    }

    public static boolean isDevelopment() {
        return configurations.properties.getBoolean("development");
    }

    public static Integer getServerId() {
        return configurations.properties.getInteger("server.id");
    }
}
