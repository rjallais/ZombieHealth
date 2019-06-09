package JavaBeans;

import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

import java.io.IOException;
import java.io.Serializable;

public class DataVisualizerBean implements Serializable, IDataPlot, IDataOrganizer {
    private String attr1; //Primeiro Atributo
    private int attr2; //Segundo Atributo
    private double attr3; //Terceiro Atributo

//  construtor sem parâmetro necessário no JavaBean
    public DataVisualizerBean() {
    }

//  construtor com parâmetros para poder instanciar classe caso necessario
    public DataVisualizerBean(String attr1, int attr2, double attr3) {
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
    public void plotTable(String[][] Dados){
        System.out.println(0);
    }

    public void plotGraph(String[][] Dados){
        System.out.println(0);
    }

    public String[][] combineTable(String[][] table1, String[][] table2){
        return null;
    }

    public String[][] addColumn(String[] column){
        return null;
    }

    public String[][] sortTable(String[][] table){
        Table table1 = null;
        try {
            table1 = Table.read().csv("zombie-health-spreadsheet-ml-training.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (table1 != null) {
            table1.sortOn(table[0][0]);
        }
        return null;
    }

//    métodos extras
//    ...

//    public static void main(String[] args) {
//        double[] numbers = {1, 2, 3, 4};
//        DoubleColumn nc;
//        nc = DoubleColumn.create("Test", numbers);
//        System.out.println(nc.print());
//
//    }

}
