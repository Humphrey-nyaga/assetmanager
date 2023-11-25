package com.assetmanager.database;

import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;
import com.assetmanager.database.helper.NotNull;
import com.assetmanager.database.helper.PrimaryKey;
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
import java.sql.*;
import java.time.LocalDate;
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
                System.out.println("Columns>>>>>>>" + columns);
                System.out.println("Values>>>>>>>" + values);


            }

            StringBuilder insertQuery = new StringBuilder("INSERT INTO ")
                    .append(tableName)
                    .append(" (")
                    .append(columns)
                    .append(") VALUES (")
                    .append(values)
                    .append(");");
            System.out.println("insertQuery >>>>>>>>>>>>>>> " + insertQuery);

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
                System.out.println("FINAL SQL STATEMENT>>" + preparedStatement.toString());
                preparedStatement.executeUpdate();
            } catch (SQLException | IllegalAccessException e) {
                throw new RuntimeException("Error inserting data into the database", e);
            }
        } catch (IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    public <T> List<T> select(Class<T> filter) {
        try {
            Class<?> clazz = filter;
            System.out.println();
            System.out.println("Clazz>>>>>>>>>>" + clazz.getName());

            if (!clazz.isAnnotationPresent(DbTable.class))
                return new ArrayList<>();

            DbTable dbTable = clazz.getAnnotation(DbTable.class);
            String stringBuilder = "SELECT * FROM " +
                    dbTable.name() + ";";
            Connection conn = connection;
            PreparedStatement preparedStatement = conn.prepareStatement(stringBuilder);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> result = new ArrayList<>();

            while (resultSet.next()) {
                T object = (T) clazz.getDeclaredConstructor().newInstance();

                List<Field> fields = new ArrayList<>(Arrays.asList(filter.getSuperclass().getDeclaredFields()));
                fields.addAll(Arrays.asList(filter.getDeclaredFields()));

                for (Field field : fields) {
                    DbColumn dbColumn = field.getAnnotation(DbColumn.class);
                    if (dbColumn != null) {
                        String columnName = dbColumn.name();

                        Object value = resultSet.getObject(columnName);
                        /*Check dates and convert to Local date.
                         * Specific date classes may need to be handled differently
                         * */
                        if (value instanceof java.sql.Date && field.getType() == LocalDate.class) {
                            value = ((java.sql.Date) value).toLocalDate();
                        }
                        if (field.getType().isEnum() && value instanceof String) {
                            value = Enum.valueOf((Class<Enum>) field.getType(), (String) value);
                        }
                        if (field.getType() == Long.class) {
                            assert value instanceof Integer;
                            value = Long.valueOf((Integer) value);
                        }

                        field.setAccessible(true);
                        field.set(object, value);
                    }
                }

                result.add(object);
            }
            return result;

        } catch (SQLException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException ex) {
            throw new RuntimeException(ex);
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