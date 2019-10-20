package ru.job4j.foodstorage;

import java.util.LinkedList;
import java.util.List;

/**
 * ControlQuality the class which divide Food list into lists add them to different places
 * (shop, warehouse, trash).
 */
public class ControlQuality {
    /**
     * trashList - goes into Trash
     * shopList - goes into Shop
     * warehouseList - goes into Warehouse
     * trash - the Instance of Trash
     * shop - the instance of Shop
     * warehouse - the instance of warehouse
     * input - the instance of the class which is responsible for input
     */
    private List<Food> trashList;
    private List<Food> shopList;
    private List<Food> warehouseList;
    private Storage trash;
    private Storage shop;
    private Storage warehouse;
    private Input input;

    public ControlQuality(Storage trash, Storage shop, Storage warehouse, ConsoleInput consoleInput) {
        this.trashList = new LinkedList<>();
        this.shopList = new LinkedList<>();
        this.warehouseList = new LinkedList<>();
        this.trash = trash;
        this.shop = shop;
        this.warehouse = warehouse;
        this.input = consoleInput;
    }

    /**
     * Divides foodList into three different lists based on expire dated and create date.
     *
     * @param foodList - product list.
     */
    private void separate(List<Food> foodList) {
        long now = System.currentTimeMillis();
        for (Food food : foodList) {
            if (food.getExpireDate() <= now) {
                this.trashList.add(food);
                continue;
            }
            long qualityTime = food.getExpireDate() - food.getCreateDate();
            long threeQuoter = (3 * qualityTime / 4) + food.getCreateDate();
            if (threeQuoter <= now) {
                this.shopList.add(setDiscount(food));
                continue;
            }
            long quoter = (qualityTime / 4) + food.getCreateDate();
            if (quoter <= now) {
                this.shopList.add(food);
                continue;
            } else {
                this.warehouseList.add(food);
            }
        }
    }

    /**
     * sets discount if it is necessary for the food.
     *
     * @param food - the food
     * @return - the food with discount
     */
    private Food setDiscount(Food food) {
        boolean bigCycle = true;
        while (bigCycle) {
            boolean cycle = true;
            double disc = 0;
            System.out.println("Наименование: " + food.getName());
            double oldPrice = food.getPrice() / 100;
            System.out.println("Текущая цена: " + oldPrice);
            System.out.println("Текущая скидка: " + food.getDiscount());
            int i = 0;
            while (cycle) {
                i++;
                String discount = this.input.ask("Ввидите скидку на продукт: ");
                try {
                    disc = Math.round(100.0 * Float.parseFloat(discount)) / 100.0;
                    cycle = false;
                } catch (NullPointerException e) {
                    System.out.println("Незозможно распознать. Введите ещё раз.");
                    if (i > 3) {
                        cycle = false;
                        disc = 0;
                    }
                }
            }
            if (food.setDiscount(disc)) {
                System.out.println("Скидка %: " + food.getDiscount());
                double newPrice = food.getPrice() / 100;
                System.out.println("Новая цена: " + newPrice);
            } else {
                System.out.println("Скидка не действительна. Применена скидка 0%");
            }
            String answer = this.input.ask("Ввести ещё раз? (y/n)");
            char ch = answer.toLowerCase().charAt(0);
            if (ch != 'y') {
                bigCycle = false;
            }
        }
        return food;
    }

    /**
     * adds divided lists into the corresponding stores.
     */
    private void addInAllStores() {
        this.shop.writeToDB(shopList);
        this.trash.writeToDB(trashList);
        this.warehouse.writeToDB(warehouseList);
    }

    /**
     * start method
     *
     * @param foodList - list of the food
     */
    public void start(List<Food> foodList) {
        separate(foodList);
        addInAllStores();
    }

}
