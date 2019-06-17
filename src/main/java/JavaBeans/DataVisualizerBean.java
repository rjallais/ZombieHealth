package JavaBeans;

import tech.tablesaw.api.*;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

public class DataVisualizerBean implements Serializable, IDataVisualizer {

//  construtor sem parâmetro necessário no JavaBean
    public DataVisualizerBean() {
    }

/*************************************************************/

//  implementação dos métodos declarados nas interfaces
    public void plotTable(String Dados){
        Table t = null;
        try {
            t = Table.read().file(Dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(t != null ? t.printAll() : null);
    }

    public void plotTable(Table dados){
        System.out.println(dados != null ? dados.printAll() : null);
    }

    public void plotTable(String[][] Dados){
        Table t = createTable(Dados);

        System.out.println(t.printAll());
    }

    public void plotGraph(String Dados){
        try {
//            Table t = Table.read().file("src/main/" +
//                    "zombie-health-spreadsheet-ml-training.csv");
//
//            int row = t.rowCount();
//            int col = t.columnCount();

            Table wines = Table.read().csv("src/main/zombie-health-plot.csv");

            Table champagne =
                    wines.where(
                            wines.stringColumn("diagnostic").isEqualTo("viral_infection"));


//            int[] init = {0};
//            IntColumn ic = IntColumn.create("init", init);
//            for (int i = 0; i < row; i++) {
//                for (int j = 0; j < col; i++){
//                    System.out.println(t.getString(i, j));
//                    IntColumn c = (IntColumn) t.column("paralysis");
//                    c.set(1, 1);
//                    if ((t.getString(i, j)).equalsIgnoreCase("t")){
//                        if(i == 0){}
//                    }
//                    else if ((t.getString(i, j)).equalsIgnoreCase("f"))
//                        t.row(i).setInt(j,0);
//                }
//            }

//            String[] example = new String[row];
//            for (int i = 0; i < col; i++) {
//                example[i] = t.row(0).getString(i);
//            }
//            StringColumn sc = StringColumn.create("ex", example);
//            Table T = Table.create(sc);
//
//            for (int x = 1; x < row; x++){
//                for (int y = 0; y < col; y++){
//                    example[y] = t.row(x).getString(y);
//                }
//                sc = StringColumn.create("ex", example);
//                T.addColumns(sc);
//            }

//            IntColumn paralysis = t.intColumn("paralysis");
//            paralysis.set(paralysis.isEqualTo(0), IntColumnType.missingValueIndicator());
//
//            IntColumn chest_pain = t.intColumn("chest_pain");
//            chest_pain.set(chest_pain.isEqualTo(0), IntColumnType.missingValueIndicator());

//            Table example = t.summarize("chest_pain", sum).by("paralysis");

            Plot.show(
                    PiePlot.create("Relaçao sintomas",
                            wines, "diagnostic", "member_loss"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] combineTable(String[][] table1,
                                   String[][] table2){
        LinkedList<String> colNames = new LinkedList<String>();
        String[][] table = new String[table1.length + table2.length -1]
                [table1[0].length + table2[0].length -1];
        for (int i = 0; i < table1.length; i++) {
            System.arraycopy(table1[i], 0, table[i], 0, table1[0].length);
        }
        for (String item:
             table1[0]) {
            int x = 0;
            for (int i = 0; i < table2[0].length; i++) {
                if (table2[0][i].equalsIgnoreCase(item))
                    x++;
            }
            if (x == 0) {
                colNames.add(item);
            }
        }
        for (int i = colNames.size(); i > 0; i--) {
            table[0][table[0].length -i] = colNames.get(-(i-3));
        }
        return null;
    }

    public String[][] addPatient(String[] patient, String[][] table){
        String[][] table1 = new String[table.length+1][table[0].length];
        for (int i = 0; i < table.length; i++) {
            System.arraycopy(table[i], 0, table1[i], 0, table[0].length);
        }
        System.arraycopy(patient, 0, table1[table.length], 0, table[0].length);
        return table1;
    }

    public String[][] sortTable(String dados){
        Table table1 = Table.create(dados);
        try {
            table1 = Table.read().file(dados);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] v = new String[table1.columnCount()-1];
        int i = 0;
        for (String string:
                table1.columnNames()) {
            if (i == table1.columnCount()-1)
                break;
            v[i] = string;
            i++;
        }
        table1 = table1.sortDescendingOn(v);

        String[][] novo = table1 != null ? new String[table1.rowCount()
                + 1][table1.columnCount()] : new String[0][];
        for (i = 0; i < (table1 != null ? table1.rowCount() : 0); i++){
            for (int j = 0; j < table1.columnCount(); j++) {
                novo[i+1][j] = table1.getString(i, j);
            }
        }
        novo[0] = table1 != null ? table1.columnNames().toArray(new String[0]) : new String[0];

        return novo;
    }

//  métodos extras para auxiliar os demais
    private Table createTable(String[][] Dados){
        Table t = Table.create("Tabela de Dados");
        for (int j = 0; j < Dados[0].length; j++) {
            String[] v = new String[Dados.length-1];
            for (int i = 1; i < Dados.length; i++) {
                v[i-1] = Dados[i][j];
            }
            StringColumn sc = StringColumn.create(Dados[0][j], v);
            t.addColumns(sc);
        }
        return t;
    }
}
