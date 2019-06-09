import JavaBeans.DataVisualizerBean;
import tech.tablesaw.api.Table;

public class Main {
    public static void main(String[] args) {
        String[][] table = {{"paralysis", "yellow_tongue",
                "member_loss"}, {"t", "t", "f"}, {"f", "t", "f"}};
        DataVisualizerBean dv = new DataVisualizerBean();
        Table newTable = dv.sortTable(table);
        System.out.println(newTable);
    }
}
