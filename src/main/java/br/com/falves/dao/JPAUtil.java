/**
 * @author falvesmac
 */

package br.com.falves.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.HashMap;
import java.util.Map;

public class JPAUtil {
    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            Map<String, String> env = System.getenv();
            Map<String, Object> configOverrides = new HashMap<>();

            if (env.get("DB_URL") != null) {
                configOverrides.put("jakarta.persistence.jdbc.url", env.get("DB_URL"));
                configOverrides.put("jakarta.persistence.jdbc.user", env.get("DB_USER"));
                configOverrides.put("jakarta.persistence.jdbc.password", env.get("DB_PASSWORD"));
            }

            emf = Persistence.createEntityManagerFactory("controle-financeiro", configOverrides);
        }
        return emf;
    }
}