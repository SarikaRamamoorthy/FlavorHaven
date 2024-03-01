package Controllers;

import java.util.ArrayList;
import java.util.List;

import DatabaseAccessObjects.DeskRelation;
import DatabaseModel.Desk;
import Utility.ExceptionHandler;

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

    public static boolean reserveDesk(int deskId, boolean reserveValue) {
        Desk temp = new Desk();
        try {
            temp.setDeskId(deskId);
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }

        boolean result = deskRelation.reserveDeskInRelation(temp, reserveValue);

        if(result) {
            Desk desk = desks.get(desks.indexOf(temp));
            desk.setReserved(reserveValue);
        }

        return result;
    }

    public static boolean payAmount(int deskId) {
        Desk desk = new Desk();
        int total = 0;
        try {

            desk.setDeskId(deskId);
            total = OrdersController.calculateBill(deskId);
            desk.setOrderAmount(total);

        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
        }

        boolean result = deskRelation.updatePayInRelation(desk);

        if(result) {
            Desk currentDesk = desks.get(desks.indexOf(desk));
            try {
                currentDesk.setOrderAmount(desk.getOrderAmount());
            } catch (Exception e) {
                ExceptionHandler.specialExceptions(e.getMessage());
            }
        }

        return result;
    }

    public static boolean hasAlreadyPaid(int deskId) {
        Desk temp = new Desk();
        try {
            temp.setDeskId(deskId);
            Desk desk = desks.get(desks.indexOf(temp));

            return desk.getOrderAmount() != 0;
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
    }

    public static boolean markAsPaid(int deskId) {
        Desk temp = new Desk();
        try {
            temp.setDeskId(deskId);
            Desk desk = desks.get(desks.indexOf(temp));
            
            boolean mark = deskRelation.markDeskAsPaidInRelation(desk);

            if(mark) {
                desk.setOrderAmount(0);
            }

            return mark;
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
    }

    public static boolean isReserved(int deskId) {
        Desk temp = new Desk();
        try {
            temp.setDeskId(deskId);
            Desk desk = desks.get(desks.indexOf(temp));

            return desk.isReserved();
        } catch (Exception e) {
            ExceptionHandler.specialExceptions(e.getMessage());
            return false;
        }
    }
}
