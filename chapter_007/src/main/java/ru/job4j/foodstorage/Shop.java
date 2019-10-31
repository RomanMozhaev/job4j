package ru.job4j.foodstorage;

import java.util.ArrayList;
import java.util.List;

/**
 * the class for the shop
 */
public class Shop implements Store {

    /**
     * the list of food in shop
     */
    private List<Food> list = new ArrayList<>();

    /**
     * the input object
     */
    private Input input;

    public Shop(Input input) {
        this.input = input;
    }

    @Override
    public List<Food> getList() {
        return list;
    }

    @Override
    public void clearList() {
        this.list.clear();
    }

    @Override
    public boolean accept(Food food) {
        boolean result = false;
        long now = System.currentTimeMillis();
        long qualityTime = food.getExpireDate() - food.getCreateDate();
        long threeQuoter = (3 * qualityTime / 4) + food.getCreateDate();
        long quoter = (qualityTime / 4) + food.getCreateDate();
        if (threeQuoter <= now && now < food.getExpireDate()) {
            setDiscount(food);
            result = true;
        } else if (quoter <= now && threeQuoter > now) {
            result = true;
        }
        return result;
    }

    @Override
    public void add(Food food) {
        this.list.add(food);
    }

    /**
     * sets discount if it is necessary for the food.
     *
     * @param food - the food
     */
    private void setDiscount(Food food) {
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
    }
}
