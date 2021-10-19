
import service.DatabaseService;
import service.TableService;
import ui.UI;

public class Main {
    public static void main(String[] args) throws Exception {
        DatabaseService databaseService = new DatabaseService();
        TableService tableService = new TableService();
        UI ui = new UI(databaseService, tableService);
        ui.runUi();
    }
}
