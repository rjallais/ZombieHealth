package JavaBeans;

import tech.tablesaw.api.Table;

public interface IDataPlot {
    void plotTable(String dados);
    void plotTable(Table dados);
    void plotTable(String[][] dados);
    void plotGraph(String dados, String sintoma, String tipo, int caso);
    void plotGraph(String dados, String sintoma1, String sintoma2, String tipo, int caso);
    void plotGraph(String dados, String sintoma1, String sintoma2, String sintoma3, String tipo, int caso);
}
