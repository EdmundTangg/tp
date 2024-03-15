package seedu.stockpal.commands;

import seedu.stockpal.data.ProductList;
import seedu.stockpal.ui.Ui;

public class ListCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_USAGE = COMMAND_KEYWORD + ": ";
    protected ProductList productList;

    public ListCommand(ProductList productList) {
        this.productList = productList;
    }

    /**
     * Prints out all products that are in the list.
     * If the list is empty, it tells the user that the list is empty.
     */
    @Override
    public void execute() {
        if (productList.isEmpty()) {
            System.out.println("ProductList is empty");
            return;
        }

        Ui.printListTasks(productList);
    }

}
