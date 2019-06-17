package JavaBeans;

public interface IDataOrganizer {
//    String[][] combineTable(String[][] table1, String[][] table2);
    String[][] addPatient(String[] patient, String[][] table);
    String[][] sortTable(String dados);
}
