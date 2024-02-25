package Controllers;

import java.util.ArrayList;

import DatabaseAccessObjects.DeskRelation;
import DatabaseModel.Desk;

public class DeskController {
    private static DeskRelation deskRelation;
    private static ArrayList<Desk> desks;

    static {
        deskRelation = DeskRelation.getInstance();
        desks = deskRelation.getDesks();
    }

    public static Table returnAllDesks() {
        ArrayList<String> columns = new ArrayList<>(deskRelation.getTableAttributes().values());
        String[] headers = columns.toArray(new String[columns.size()]);
        String[][] data = new String[desks.size()][];

        for (int i = 0; i < desks.size(); i++) {
            String[] deskRow = desks.get(i).toString().split(Desk.seperator);
            deskRow[deskRow.length - 1] = deskRow[deskRow.length - 1].equals("false") ? "Unreserved" : "Reserved";
            data[i] = deskRow;
        }

        Table table = new Table(headers, data);
        return table;
    }
}
