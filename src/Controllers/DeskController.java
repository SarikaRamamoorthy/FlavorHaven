package Controllers;

import java.util.ArrayList;
import java.util.List;

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
        List<String> columns = new ArrayList<String>(deskRelation.getTableAttributes().values());
        String[] headers = columns.toArray(new String[columns.size()]);
        String[][] data = new String[desks.size()][];

        for (int i = 0; i < desks.size(); i++) {
            String[] deskRow = desks.get(i).toString().split(Desk.seperator);
            deskRow[deskRow.length - 2] = deskRow[deskRow.length - 2].equals("false") ? "Unreserved" : "Reserved";

            deskRow[deskRow.length - 1] = deskRow[deskRow.length - 1].equals("0") ? "Order Not Finished"
                    : deskRow[deskRow.length - 1];
            data[i] = deskRow;
        }

        Table table = new Table(headers, data);
        return table;
    }

    public static Table returnAllAvailableDesks() {
        List<String> columns = new ArrayList<String>(deskRelation.getTableAttributes().values());
        columns = columns.subList(0, columns.size() - 2);

        String[] headers = columns.toArray(new String[columns.size()]);
        ArrayList<String[]> data = new ArrayList<String[]>();

        for (Desk desk : desks) {
            if(!desk.isReserved()) {
                String[] deskRow = new String[3];

                deskRow[0] = desk.getDeskId() + "";
                deskRow[1] = desk.getDeskName();
                deskRow[2] = desk.getSeatCount() + "";

                data.add(deskRow);
            }
        }

        Table table = new Table(headers, data.toArray(new String[data.size()][]));

        return table;
        
    }

    public static boolean isValidDesk(int deskId) {
        
        for (Desk desk : desks) {
            if(desk.getDeskId() == deskId)
            return true;
        }

        return false;
    }
}
