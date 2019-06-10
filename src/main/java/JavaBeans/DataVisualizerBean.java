package JavaBeans;

import tech.tablesaw.api.*;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.*;

import java.io.IOException;
import java.io.Serializable;

public class DataVisualizerBean implements Serializable,
        IDataPlot, IDataOrganizer {
    private String attr1; //Primeiro Atributo
    private int attr2; //Segundo Atributo
    private double attr3; //Terceiro Atributo

//  construtor sem parâmetro necessário no JavaBean
    public DataVisualizerBean() {
    }

//  construtor com parâmetros para poder
//  instanciar classe caso necessario
    public DataVisualizerBean(String attr1, int attr2,
                              double attr3) {
        this.attr1 = attr1;
        this.attr2 = attr2;
        this.attr3 = attr3;
    }

//  getters e setters padronizados do JavaBean
    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public int getAttr2() {
        return attr2;
    }

    public void setAttr2(int attr2) {
        this.attr2 = attr2;
    }

    public double getAttr3() {
        return attr3;
    }

    public void setAttr3(double attr3) {
        this.attr3 = attr3;
    }
/*************************************************************/

//  implementação dos métodos declarados nas interfaces
    public void plotTable(String Dados){
        Table t = null;
        try {
            t = Table.read().csv(Dados);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(t != null ? t.printAll() : null);
    }

    public void plotTable(Table dados){
        System.out.println(dados != null ? dados.printAll() : null);
    }

    public void plotTable(String[][] Dados){
        Table t = Table.create("Tabela de Dados");
        for (int j = 0; j < Dados[0].length; j++) {
            String[] v = new String[Dados.length];
            for (int i = 1; i < Dados.length; i++) {
                v[i] = Dados[i][j];
            }
            StringColumn sc = StringColumn.create(Dados[0][j], v);
            t.addColumns(sc);
        }

        System.out.println(t.printAll());
    }

    public void plotGraph(String Dados){
        try {
            Table t = Table.read().csv("src/main/" +
                    "zombie-health-spreadsheet-ml-training.csv");

            int row = t.rowCount();
            int col = t.columnCount();

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
                    VerticalBarPlot.create("Zombie Health",
                            t,
                            "diagnostic",
                            "chest_pain"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[][] combineTable(String[][] table1,
                                   String[][] table2){
        return null;
    }

    public String[][] addColumn(String[] column){
        return null;
    }

    public String[][] sortTable(String dados){
        Table table1 = Table.create(dados);
        try {
            table1 = Table.read().csv(dados);
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

        String[][] novo = table1 != null ? new String[table1.rowCount()][table1.columnCount()] : new String[0][];
        for (i = 1; i < (table1 != null ? table1.rowCount() : 0); i++){
            for (int j = 0; j < table1.columnCount(); j++) {
                novo[i][j] = table1.getString(i, j);
            }
        }
        novo[0] = table1 != null ? table1.columnNames().toArray(new String[0]) : new String[0];

        return novo;
    }

//    métodos extras
//    ...

}
