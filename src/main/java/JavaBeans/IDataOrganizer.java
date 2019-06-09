package JavaBeans;

public interface IDataOrganizer {
    String[][] combineTable(String[][] table1, String[][] table2);
    String[][] addColumn(String[] column);
    String[][] sortTable(String[][] table);
}
