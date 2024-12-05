package comp3350.ims.persistence;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLWarning;

import comp3350.ims.objects.Inventory;
import comp3350.ims.objects.Item;
import comp3350.ims.objects.ItemType;

public class DataAccessDatabase implements DataAccess {
    private Statement st1, st2, st3;
    private Connection c1;
    private ResultSet rs2, rs3, rs4, rs5;

    private String dbName;
    private String dbType;

    private String cmdString;
    private int updateCount;
    private String result;
    private static String EOF = "  ";

    public DataAccessDatabase(String dbName) {
        this.dbName = dbName;
    }

    public void open(String dbPath) {
        String url;
        try {
            dbType = "HSQL";
            Class.forName("org.hsqldb.jdbcDriver").newInstance();
            url = "jdbc:hsqldb:file:" + dbPath;
            c1 = DriverManager.getConnection(url, "IMS", "");
            st1 = c1.createStatement();
            st2 = c1.createStatement();
            st3 = c1.createStatement();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Opened " + dbType + " database " + dbPath);
    }

    public void close() {
        try { // commit all changes to the database
            cmdString = "shutdown compact";
            rs2 = st1.executeQuery(cmdString);
            c1.close();
        } catch (Exception e) {
            processSQLError(e);
        }
        System.out.println("Closed " + dbType + " database " + dbName);
    }

    public Inventory getActiveInventory() {
        Inventory inventory = new Inventory();
        ArrayList < ItemType > itemTypes = new ArrayList < > ();
        try {
            cmdString = "Select * from ITEMTYPE";
            rs3 = st2.executeQuery(cmdString);

            while (rs3.next()) {
                int id = rs3.getInt("ID");
                String itemTypeName = rs3.getString("NAME");
                float price = rs3.getFloat("PRICE");
                int quantity = rs3.getInt("QUANTITY");
                String location = rs3.getString("LOCATIONNAME");
                String date = rs3.getString("DATE");
                String category = rs3.getString("CATEGORYNAME");
                ItemType type = new ItemType(itemTypeName, price, location, date, category);
                type.setID(id);
                itemTypes.add(type);
            }

            cmdString = "Select * from ITEM";
            rs4 = st3.executeQuery(cmdString);

            while (rs4.next()) {
                int id = rs4.getInt("ID");
                String location = rs4.getString("LOCATIONNAME");
                String date = rs4.getString("DATE");
                int itemTypeID = rs4.getInt("ITEMTYPEID");

                for (int i = 0; i < itemTypes.size(); i++) {
                    if (itemTypes.get(i).getID() == itemTypeID) {
                        Item newItem = new Item(location, date);
                        newItem.setItemId(id);
                        itemTypes.get(i).addItem(newItem);

                    }
                }
            }

            inventory = new Inventory(itemTypes);
        } catch (Exception e) {
            processSQLError(e);
        }

        return inventory;
    }

    public void addItemType(ItemType item) {
        String values;

        try {
            values = item.getID() +
                    ", '" + item.getName() + "\'" +
                    ", '" + "MAIN" +
                    "', '" + item.getPrice() +
                    "', '" + item.getCategory() +
                    "', '" + item.getLocation() +
                    "', '" + item.getDate() +
                    "', '" + item.getQuantity() +
                    "'";
            cmdString = "Insert into ITEMTYPE " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
        } catch (Exception e) {
            processSQLError(e);
        }


    }

    public String getCategoryList(ArrayList < String > categoryList) {
        String myName = EOF;

        try {
            cmdString = "Select * from CATEGORY";
            rs3 = st1.executeQuery(cmdString);

            while (rs3.next()) {
                myName = rs3.getString("NAME");
                categoryList.add(myName);
            }
            rs3.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }


    public void addItem(Item item, int itemTypeID) {
        String values;

        try {
            values = "\'" + item.getItemId() +
                    "', " + itemTypeID +
                    ", '" + item.getDate() +
                    "', '" + item.getLocation() +
                    "'";

            cmdString = "Insert into ITEM " + " Values(" + values + ")";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            processSQLError(e);
        }
    }


    public String getLocationList(ArrayList < String > locationList) {
        String myName = EOF;

        try {
            cmdString = "Select * from LOCATION";
            rs3 = st1.executeQuery(cmdString);

            while (rs3.next()) {
                myName = rs3.getString("NAME");
                locationList.add(myName);
            }
            rs3.close();
        } catch (Exception e) {
            processSQLError(e);
        }

        return null;
    }

    public void addCategory(String category) {
        String values;
        result = null;
        try {
            values = category;
            cmdString = "INSERT INTO CATEGORY " + " VALUES(\'" + values + "\')";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
    }

    public void addLocation(String location) {
        String values;
        result = null;
        try {
            values = location;
            cmdString = "INSERT INTO LOCATION " + " VALUES(\'" + values + "\')";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
        } catch (Exception e) {
            result = processSQLError(e);
        }
    }

    public boolean removeLocation(String name) {
        boolean flag = false;
        try {
            cmdString = "Delete from LOCATION where NAME= " + "\'" + name + "\'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            flag = true;
            flag = true;
        } catch (Exception e) {
            processSQLError(e);
        }
        return flag;
    }

    public boolean removeCategory(String name) {
        boolean flag = false;
        try {
            cmdString = "Delete from CATEGORY where NAME= " + "\'" + name + "\'";
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            flag = true;

        } catch (Exception e) {
            processSQLError(e);
        }
        return flag;
    }

    public boolean isCategory(String name) {
        boolean flag = false;
        try {
            cmdString = "select * from CATEGORY where NAME= " + "\'" + name + "\'";
            rs2 = st1.executeQuery(cmdString);
            flag = rs2.next();
        } catch (Exception e) {
            processSQLError(e);
        }
        return flag;
    }

    public boolean isLocation(String name) {
        boolean flag = false;
        try {
            cmdString = "select * from LOCATION where NAME= " + "\'" + name + "\'";
            rs2 = st1.executeQuery(cmdString);
            flag = rs2.next();

        } catch (Exception e) {
            processSQLError(e);
        }
        return flag;
    }

    public boolean removeItem(int itemID, int itemTypeID, int quantity) {
        boolean flag = false;
        result = null;
        try {
            cmdString = "Delete from ITEM where ID=" + itemID;
            updateCount = st1.executeUpdate(cmdString);
            result = checkWarning(st1, updateCount);
            cmdString = "Update ITEMTYPE Set QUANTITY=" + quantity + " where ID=" + itemTypeID;
            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
            flag = true;
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return flag;
    }

    public boolean editItemType(ItemType itemType, String name, float price, String category) {
        boolean flag = false;
        result = null;
        try {
            String values = " NAME='" + name +
                    "', PRICE='" + price +
                    "', CATEGORYNAME='" + category +
                    "'";
            cmdString = "Update ITEMTYPE Set " + values + " where ID=" + itemType.getID();

            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
            flag = true;
            itemType.setName(name);
            itemType.setPrice(price);
            itemType.setCategory(category);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return flag;
    }

    public boolean editItem(Item item, String location) {
        boolean flag = false;
        result = null;
        try {
            String values = " LOCATIONNAME='" + location +
                    "'";
            cmdString = "Update ITEM Set " + values + " where ID=" + item.getItemId();

            updateCount = st2.executeUpdate(cmdString);
            result = checkWarning(st2, updateCount);
            flag = true;
            item.setLocation(location);
        } catch (Exception e) {
            result = processSQLError(e);
        }
        return flag;
    }

    public String processSQLError(Exception e) {
        String result = "*** SQL Error: " + e.getMessage();

        e.printStackTrace();

        return result;
    }

    public String checkWarning(Statement st, int updateCount) {
        String result;

        result = null;
        try {
            SQLWarning warning = st.getWarnings();
            if (warning != null) {
                result = warning.getMessage();
            }
        } catch (Exception e) {
            result = processSQLError(e);
        }
        if (updateCount != 1) {
            result = "Tuple not inserted correctly.";
        }
        return result;
    }

    public void setAutoCommitOff() {
        try {
            c1.setAutoCommit(false);
        } catch (SQLException e) {

        }
    }
}