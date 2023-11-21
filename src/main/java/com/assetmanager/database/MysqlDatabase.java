package com.assetmanager.database;

import com.assetmanager.database.helper.DbColumn;
import com.assetmanager.database.helper.DbTable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.*;
import java.time.LocalDate;

public class MysqlDatabase {


    private static MysqlDatabase database;
    private Connection connection;

    private MysqlDatabase() throws SQLException, NamingException {
            Context ctx = new InitialContext();
            DataSource dataSource = (DataSource) ctx.lookup("java:jboss/datasources/assetmanager");
            connection = dataSource.getConnection();

    }
    public static MysqlDatabase getDatabaseInstance()  {

        try {
            if (database == null) {
                database = new MysqlDatabase();
            }
            return database;
        }catch (SQLException | NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    public static void insert(Object entity) {
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
                System.out.println("Columns>>>>>>>" + columns.toString());
                System.out.println("Values>>>>>>>" + values.toString());


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
                Connection connection = MysqlDatabase.getDatabaseInstance().getConnection();
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
}
