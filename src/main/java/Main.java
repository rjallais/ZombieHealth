import JavaBeans.DataVisualizerBean;
import tech.tablesaw.api.Table;

public class Main {
    public static void main(String[] args) {
        String[][] table = {{"paralysis", "yellow_tongue",
                "member_loss", "member_loss", "chest_pain",
                "trembling_finger", "severe_anger"}};
        DataVisualizerBean dv = new DataVisualizerBean();
//        Table newTable = dv.sortTable(table);
//        System.out.println(newTable);
        dv.plotTable("src/main/zombie-health-cases500.csv");
    }
}
