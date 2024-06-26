package seedu.stockpal.commands;

import seedu.stockpal.data.CommandParameter;
import seedu.stockpal.common.FormatUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.DuplicateProductNameException;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.ui.Ui;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author Kobot7
public class EditCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "edit";
    public static final String COMMAND_DESCRIPTION = "Edits an existing product in the inventory.\n" +
            "Note that at least 1 flag is required when using the edit command.";
    public static final String COMMAND_USAGE = "edit PID [n/PRODUCT_NAME] [q/QUANTITY] [p/PRICE] [d/DESCRIPTION]";

    public static final String[] COMMAND_FLAGS = {
        "PID"
        , "PRODUCT_NAME"
        , "QUANTITY"
        , "PRICE"
        , "DESCRIPTION"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
        , "Updated name of the product"
        , "Updated quantity of the product"
        , "Updated price of the product"
        , "Updated description of the product"
    };
    protected static Logger logger = Logger.getLogger(EditCommand.class.getName());

    private final Pid pid;
    private final Name name;
    private final Quantity quantity;
    private final Description description;
    private final Price price;

    public EditCommand(Integer pid, String name, Integer quantity, Double price, String description) {
        this.pid = new Pid(pid);
        this.name = new Name(name);
        this.quantity = new Quantity(quantity, false);
        this.price = new Price(price);
        this.description = new Description(description);
    }

    /**
     * Checks if at least 1 parameter (name, quantity, price, description)
     * is provided in the command.
     *
     * @return True if there is at least 1 parameter provided. False otherwise.
     */
    private boolean atLeastOneValidParameter() {
        for (CommandParameter parameter : Arrays.asList(name, quantity, price, description)) {
            if (!parameter.isNull()) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void execute(ProductList productList) throws DuplicateProductNameException, PidNotFoundException {
        if (!atLeastOneValidParameter()) {
            Ui.printMissingParametersMessage();
            return;
        }
        int productIndex = productList.findProductIndex(this.pid);
        assert productList.getSize() > 0;

        productList.updateProduct(productIndex, name, quantity, description, price);
        logger.log(Level.INFO, Messages.MESSAGE_EDIT_SUCCESS);
        Ui.printEditSuccessMessage();
    }

    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
}
