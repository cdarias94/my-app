package com.code.myapp.repository;

import com.code.myapp.model.Product;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.ClientInfoStatus;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ProductRepo {

    private final NamedParameterJdbcTemplate JdbcTemplate;
    private final ProductMapper mapper = new ProductMapper();
    private final SimpleJdbcInsert insert;
    private final String table = "product7";


    public ProductRepo(NamedParameterJdbcTemplate namedParameterJdbcTemplate, DataSource dataSource) {
        this.JdbcTemplate = namedParameterJdbcTemplate;
        this.insert = new SimpleJdbcInsert(dataSource).withTableName(table).usingGeneratedKeyColumns("id");
    }

    public List<Product> getallProduct() {

        String sql = "Select * From product7";

        return JdbcTemplate.query(sql, mapper);
    }


    public Product getProduct(Long id) {

        String sql = "Select * FROM product7  where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return JdbcTemplate.queryForObject(sql, params, mapper);
    }


       public List<Product> productSimilar(Long id) {
        String sql = "SELECT * FROM product7 WHERE id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        try {
            Product product = JdbcTemplate.queryForObject(sql, params, new ProductMapper());

            if (product.productSimilar != null && !product.productSimilar.isEmpty()) {
                String similarProductsSql = "SELECT * FROM product7 WHERE id IN (:similarIds)";


                MapSqlParameterSource similarParams = new MapSqlParameterSource();
                similarParams.addValue("similarIds",product.productSimilar);

                return JdbcTemplate.query(similarProductsSql, similarParams, mapper);
            }

        } catch (EmptyResultDataAccessException e) {
            // Handle the case when the product is not found (Optional).
            // You can log an error or return an empty list to indicate no product found.
            return Collections.emptyList();
        }

        return Collections.emptyList();
    }



    private static class ProductMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            boolean availability = rs.getBoolean("availability");
            double price = rs.getDouble("price");


            // Retrieve the array of productSimilar IDs
            Array prodSimilarArray = rs.getArray("productSimilar");
            List<Long> prodSimilarIds = null;
            if (prodSimilarArray != null) {
                prodSimilarIds = Arrays.asList((Long[]) prodSimilarArray.getArray());
            }

            return new Product(id, name, availability, price, prodSimilarIds);
        }

        }

    }



