package JavaBeans;

import tech.tablesaw.api.Table;

public interface IDataOrganizer {
    String[][] combineTable(String[][] table1, String[][] table2);
    String[][] addColumn(String[] column);
    String[][] sortTable(String dados);
}
