package com.assetmanager.database;

import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.database.helper.PrimaryKey;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.reflections.Reflections;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Startup
@Singleton
public class MysqlDatabase {


    private Connection connection;

    @PostConstruct
    private void init() throws SQLException, NamingException {
        Context ctx = new InitialContext();
        DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/assetmanager");
        connection = dataSource.getConnection();
        this.createSchema();
    }


    public void createSchema() {
        System.out.println("*************** Asset Manager Database Tables Creation Initialized *************");

        try {
            Reflections reflections = new Reflections("com.assetmanager.app.model.entity");
            List<Class<?>> entities = reflections.getTypesAnnotatedWith(DbTable.class)
                    .stream().toList();

            Connection conn = connection;

            for (Class<?> clazz : entities) {
                if (!clazz.isAnnotationPresent(DbTable.class))
                    continue;
                DbTable dbTable = clazz.getAnnotation(DbTable.class);
                StringBuilder stringBuilder = new StringBuilder()
                        .append("CREATE TABLE IF NOT EXISTS ").append(dbTable.name()).append("(");

                String prefix = "";

                List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

                for (Field field : fields) {
                    field.setAccessible(true);

                    if (!field.isAnnotationPresent(DbColumn.class))
                        continue;

                    DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                    stringBuilder.append(prefix)
                            .append(dbColumn.name())
                            .append(" ")
                            .append(dbColumn.definition())
                            .append(field.isAnnotationPresent(NotNull.class) ? " NOT NULL" : "")
                            .append(field.isAnnotationPresent(PrimaryKey.class) ? " AUTO_INCREMENT PRIMARY KEY" : "");
                    prefix = ", ";

                }

                stringBuilder.append(");");
                System.out.println("Create Table SQL >> " + stringBuilder);

                PreparedStatement createTableStmt = conn.prepareStatement(stringBuilder.toString());
                createTableStmt.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void insert(Object entity) {
        Class<?> clazz = entity.getClass();
        try {
            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            if (dbTable == null) {
                throw new IllegalArgumentException("DbTable annotation is absent for the entity class");
            }

            String tableName = dbTable.name();

            StringBuilder columns = new StringBuilder();
            StringBuilder values = new StringBuilder();
            String prefix = "";

            //  List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbColumn.class))
                    continue;


                field.setAccessible(true);

                if (field.get(entity) == null)
                    continue;

                DbColumn column = field.getAnnotation(DbColumn.class);
                String columnName = column.name().isEmpty() ? field.getName() : column.name();
                columns.append(prefix).append(columnName);
                values.append(prefix).append("?");

                prefix = ", ";


            }

            StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (")
                    .append(columns)
                    .append(") VALUES (")
                    .append(values)
                    .append(");");
            System.out.println("insertQuery >>>" + insertQuery);

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertQuery.toString());
                int parameterIndex = 1;
                for (Field field : fields) {
                    field.setAccessible(true);

                    DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                    if (dbColumn != null) {
                        Object value = field.get(entity);

                        if (field.getType().isEnum()) {
                            value = ((Enum<?>) value).name();
                        } else if (value instanceof LocalDate) {
                            value = Date.valueOf((LocalDate) value);
                        }
                        preparedStatement.setObject(parameterIndex++, value);
                    }
                }
                preparedStatement.executeUpdate();
            } catch (SQLException | IllegalAccessException e) {
                throw new RuntimeException("Error inserting data into the database", e);
            }
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> List<T> select(T entity) {
        List<T> resultList = new ArrayList<>();

        try {
            Class<?> clazz = entity.getClass();
            if (!clazz.isAnnotationPresent(DbTable.class))
                return resultList;

            DbTable dbTable = clazz.getAnnotation(DbTable.class);

            String tableAlias = dbTable.name().charAt(0) + "_e_";
            System.out.println("table alias " + tableAlias);

            List<Field> fields = new ArrayList<>(Arrays.asList(clazz.getSuperclass().getDeclaredFields()));
            fields.addAll(Arrays.asList(clazz.getDeclaredFields()));

            StringBuilder columnBuilder = new StringBuilder();
            StringBuilder paramPlaceHolderBuilder = new StringBuilder();
            List<Object> whereParams = new ArrayList<>();

            for (Field field : fields) {
                if (!field.isAnnotationPresent(DbColumn.class))
                    continue;

                DbColumn dbTableColumn = field.getAnnotation(DbColumn.class);

                columnBuilder.append(tableAlias).append(".").append(dbTableColumn.name()).append(",");

                field.setAccessible(true);
                if (field.get(entity) != null) {
                    paramPlaceHolderBuilder
                            .append(whereParams.isEmpty() ? "" : " and ")
                            .append(tableAlias).append(".").append(dbTableColumn.name()).append("=?");
                    whereParams.add(field.get(entity));
                }

            }

            String queryBuilder =
                    "select " +
                            columnBuilder +
                            " from " +
                            dbTable.name() + " " + tableAlias;
                           // (whereParams.isEmpty() && StringUtils.isBlank(paramPlaceHolderBuilder) ? "" : " where " + paramPlaceHolderBuilder);

            String query = queryBuilder.replace(", from", " from");
            System.out.println("Query: " + query);

            PreparedStatement sqlStmt = connection.prepareStatement(query);

//            int paramIdx = 1;
//            for (Object whereParam : whereParams) {
//
//                if (whereParam.getClass().isAssignableFrom(BigDecimal.class)) {
//                    sqlStmt.setBigDecimal(paramIdx++, (BigDecimal) whereParam);
//                } else if (whereParam.getClass().isAssignableFrom(Long.class)) {
//                    sqlStmt.setLong(paramIdx++, (long) whereParam);
//                } else if (whereParam instanceof Enum<?> enumValue && whereParam.getClass().isEnum()) {
//                    sqlStmt.setObject(paramIdx++, enumValue);
//                } else if (whereParam instanceof Integer) {
//                    sqlStmt.setInt(paramIdx++, (int) whereParam);
//                } else if (whereParam instanceof java.sql.Date && whereParam.getClass().isAssignableFrom(LocalDate.class)) {
//                    sqlStmt.setObject(paramIdx++, ((java.sql.Date) whereParam).toLocalDate());
//                } else if (whereParam instanceof LocalDateTime) {
//                    sqlStmt.setObject(paramIdx++, (LocalDateTime) whereParam);
//                } else {
//                    sqlStmt.setString(paramIdx++, (String) whereParam);
//                }

            ResultSet resultSet = sqlStmt.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            int resultSetMetaDataCnt = resultSetMetaData.getColumnCount();
            System.out.println("resultSetMetaDataCnt = " + resultSetMetaDataCnt);

            while (resultSet.next()) {
                T bean = (T) entity.getClass().getDeclaredConstructor().newInstance();

                for (int idx = 1; idx <= resultSetMetaDataCnt; idx++) {
                    String colName = resultSetMetaData.getColumnName(idx);


                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(DbColumn.class))
                            continue;

                        DbColumn dbTableColumn = field.getAnnotation(DbColumn.class);

                        if (dbTableColumn.name().equals(colName)) {

                            Object value = resultSet.getObject(idx);

                            if (value instanceof java.sql.Date && field.getType() == LocalDate.class) {
                                value = ((java.sql.Date) value).toLocalDate();
                            }
                            if (field.getType().isEnum()) {
                                value = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                            }
                            if (field.getType() == Long.class) {
                                assert value instanceof Integer;
                                value = Long.valueOf((Integer) value);
                            }

                            field.setAccessible(true);
                            BeanUtils.setProperty(bean, field.getName(), value);
                            break;
                        }
                    }

                }

                resultList.add(bean);
            }


            return resultList;


        } catch (SQLException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteById(Class<?> clazz, Long id) {
        if (!clazz.isAnnotationPresent(DbTable.class))
            throw new RuntimeException("Database Table Annotation Does Not Exists");
        DbTable dbTable = clazz.getAnnotation(DbTable.class);
        String tableName = dbTable.name();

        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}