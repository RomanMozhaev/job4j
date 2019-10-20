package ru.job4j.foodstorage;

/**
 * the method return the instance of the incoming type
 */
public class FoodType {

    /**
     * creates the instance of the food which incomes.
     *
     * @param className  - food type
     * @param name       - name
     * @param expireDate - expire date
     * @param createDate - create date
     * @param price      - food price
     * @param discount   - discount
     * @return the instance of the food with specific type
     */
    public Food getType(String className, String name, long expireDate, long createDate, long price, double discount) {
        Food food = null;
        if (className.equals("Bread")) {
            food = new Bread(name, expireDate, createDate, price, discount);
        }
        if (className.equals("Butter")) {
            food = new Butter(name, expireDate, createDate, price, discount);
        }
        if (className.equals("SaltCrackers")) {
            food = new SaltCrackers(name, expireDate, createDate, price, discount);
        }
        if (className.equals("SweetCookies")) {
            food = new SweetCookies(name, expireDate, createDate, price, discount);
        }
        return food;
    }
}
