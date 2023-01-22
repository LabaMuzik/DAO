package ru.netology.dao.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MyRepository {
    final static String scriptName = "join.sql";

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public MyRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    public static String read(String scriptName) {
        try (InputStream is = new ClassPathResource(scriptName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            return bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    String product_name = read(scriptName);

    public List<String> getProductName(String name) {
        return template.queryForList(product_name, new MapSqlParameterSource("name", name), String.class);
    }
}