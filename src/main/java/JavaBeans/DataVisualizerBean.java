package JavaBeans;

import tech.tablesaw.api.*;
import tech.tablesaw.columns.numbers.IntColumnType;
import tech.tablesaw.plotly.Plot;
import tech.tablesaw.plotly.api.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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

    public void plotGraph(String Dados, String sintoma){
        try {
            Table t = Table.read().csv(Dados);
            String[][] tb = createMatrix(t);

            List<String> diagList = new LinkedList<String>();
            int cols = tb[0].length - 1;
            for (int i = 1; i < tb.length; i++) {
                if (diagList.contains(tb[i][cols]))
                    continue;
                diagList.add(tb[i][cols]);
            }

            int[][][] mat = new int[2][cols][diagList.size()];

            for (int i = 0; i < cols; i++) {
                for (int j = 0; j < diagList.size(); j++) {
                    for (int k = 0; k < 2; k++) {
                        mat[k][i][j] = sumOccurrences(t, tb[0][i], diagList.get(j), k);
                    }
                }
            }

            int[][] tab1 = mat[0];
            int[][] tab2 = mat[1];

            Table False = Table.create("False");
            Table True = Table.create("True");

            for (int i = 0; i < cols; i++) {
                False.addColumns(IntColumn.create(tb[0][i], tab1[i]));
            }
            False.addColumns(StringColumn.create(tb[0][cols], diagList));

            for (int i = 0; i < cols; i++) {
                True.addColumns(IntColumn.create(tb[0][i], tab2[i]));
            }
            True.addColumns(StringColumn.create(tb[0][cols], diagList));

            NumberColumn ytF = False.intColumn(sintoma);
            ytF.set(ytF.isEqualTo(0), 1);

            NumberColumn ytT = True.intColumn(sintoma);
            ytT.set(ytT.isEqualTo(0), 1);

            Plot.show(
                    BubblePlot.create("Relação entre dois sintomas", False, "chest_pain",
                            "trembling_finger", sintoma, "diagnostic"));
//
            Plot.show(
                    BubblePlot.create("Relação entre dois sintomas", True, "chest_pain",
                            "trembling_finger", sintoma,"diagnostic"));

            Plot.show(
                    PiePlot.create(sintoma + " não está muito relacionado ao(s) diagnóstico(s)", False, "diagnostic",
                            sintoma));

            Plot.show(
                    PiePlot.create(sintoma + " está muito relacionado ao(s) diagnóstico(s)", True, "diagnostic",
                            sintoma));

            Plot.show(
                    Scatter3DPlot.create("Olá Mundo", False, "trembling_finger",
                        "chest_pain", sintoma, "diagnostic"));

            Plot.show(
                    Scatter3DPlot.create("Olá Mundo", True, "trembling_finger",
                            "chest_pain", "severe_anger", sintoma,
                            "diagnostic"));

        } catch (IOException e) {
            e.printStackTrace();
        }
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

        String[][] New = createMatrix(table1);
        return New;
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

    private Table createTable(int[][] Dados){
        Table t = Table.create("Tabela de Dados");
        for (int j = 0; j < Dados[0].length; j++) {
            int[] v = new int[Dados.length-1];
            for (int i = 1; i < Dados.length; i++) {
                v[i-1] = Dados[i][j];
            }
            IntColumn ic = IntColumn.create(String.valueOf(Dados[0][j]), v);
            t.addColumns(ic);
        }
        return t;
    }

    private String[][] createMatrix(Table Dados){
        String[][] novo = Dados != null ? new String[Dados.rowCount()
                + 1][Dados.columnCount()] : new String[0][];
        for (int i = 0; i < (Dados != null ? Dados.rowCount() : 0); i++){
            for (int j = 0; j < Dados.columnCount(); j++) {
                novo[i+1][j] = Dados.getString(i, j);
            }
        }
        novo[0] = Dados != null ? Dados.columnNames().toArray(new String[0]) : new String[0];

        return novo;
    }

    private int sumOccurrences(Table Dados, String symptom, String diag, int is){

        Table occurs = Dados.where(Dados.stringColumn("diagnostic").
                isEqualTo(diag).and(is==1 ? Dados.booleanColumn(symptom).isTrue() :
                Dados.booleanColumn(symptom).isFalse()));

       return occurs.rowCount();
    }
}
