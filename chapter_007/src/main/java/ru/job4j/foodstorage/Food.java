package ru.job4j.foodstorage;

import java.util.Objects;

/**
 * the class for all foods
 */
public class Food {
    /**
     * name of the food
     * expire date
     * create date
     * food prise
     * discount
     */
    private String name;
    private long expireDate;
    private long createDate;
    private long price;
    private double discount;

    public Food(String name, long expireDate, long createDate, long price, double discount) {
        this.name = name;
        this.expireDate = expireDate;
        this.createDate = createDate;
        this.price = price;
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public long getExpireDate() {
        return expireDate;
    }

    public long getCreateDate() {
        return createDate;
    }

    public long getPrice() {
        return price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * sets discount if discount is more than 0 and less then 90%.
     *
     * @param discount - discount.
     * @return true if the discount was applied, false if not.
     */
    public boolean setDiscount(double discount) {
        boolean result = false;
        if (discount >= 0 && discount <= 90) {
            this.discount = discount;
            setPrice(Math.round(this.price * (100 - this.discount) / 100));
            result = true;
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return expireDate == food.expireDate
                && createDate == food.createDate
                && price == food.price
                && Double.compare(food.discount, discount) == 0
                && Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, expireDate, createDate, price, discount);
    }
}
