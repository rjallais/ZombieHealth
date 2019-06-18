import JavaBeans.DataVisualizerBean;
import JavaBeans.IDataOrganizer;
import JavaBeans.IDataPlot;
import JavaBeans.IDataVisualizer;

public class Main {
    public static void main(String[] args) {

        IDataVisualizer dv = new DataVisualizerBean();

        String[][] tabela = dv.sortTable("src/main/zombie-health-spreadsheet-ml-training.csv");
        dv.plotGraph("src/main/zombie-health-cases500.csv", "paralysis");
//        dv.plotTable(tabela);
//        String[] paciente = {"false", "false", "false", "false", "true", "false", "false", "false", "NOVA DOENÇA"};
//        tabela = dv.addPatient(paciente, tabela);
//        dv.plotTable(tabela);

//        dv.plotGraph("/home/rjallais/IdeaProjects/ZombieHealth/src/main/zombie-health-cases500.csv");


//        try {
//            Table tabela = Table.read().csv("src/main/zombie-health-new-cases20.csv");
//            BooleanColumn yellow_tongue = tabela.booleanColumn("yellow_tongue");
//            Table trueYellowTongue = tabela.where(yellow_tongue.isTrue());
//            dv.plotTable(trueYellowTongue);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
