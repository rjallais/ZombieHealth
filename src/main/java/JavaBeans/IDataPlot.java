package JavaBeans;

import tech.tablesaw.api.Table;

public interface IDataPlot {
    void plotTable(String Dados);
    void plotTable(Table dados);
    void plotTable(String[][] Dados);
    void plotGraph(String Dados, String sintoma);
}
