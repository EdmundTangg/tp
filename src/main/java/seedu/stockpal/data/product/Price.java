package seedu.stockpal.data.product;

import seedu.stockpal.common.CommandParameter;

public class Price implements CommandParameter {
    private static final String EMPTY_STRING = "";
    protected Double price;

    public Price(Double price) {
        this.price = (price == null || price < 0)
                ? null
                : price;
    }

    public Double getPrice() {
        return this.price;
    }
    public boolean isNull() {
        return this.price == null;
    }
    @Override
    public String toString() {
        return "Price: " + ((price == null)
                ? "[X]"
                : String.format("%.2f", price));
    }

    /**
     * Converts the Price to the specific format for saving to the data file.
     *
     * @return A formatted string containing the Price for saving.
     */
    public String toSave() {
        return this.isNull()
                ? EMPTY_STRING
                : this.price.toString();
    }
}