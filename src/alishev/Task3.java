package alishev;

// задача на заполнение рюкзака предметами с возможностью их делить
// смысл = заполнить рюкзак так, чтобы стоимость вещей в нем была максимальной

import java.util.Arrays;
import java.util.Comparator;

public class Task3 {

    public static void main(String[] args) {


        final Item item1 = new Item(4, 20);
        final Item item2 = new Item(3, 18);
        final Item item3 = new Item(2, 14);

        final Item[] items = {item1, item2, item3};

        // сначала нужно отсортировать предметы по их удельной стоимости (стоимость за кг)
        Arrays.sort(items, Comparator.comparingDouble(Item::valuePerUnitOfWeight).reversed());

        System.out.println(Arrays.toString(items));

        // вместимость рюкзака, кг
        final int W = 7;
        int currentWeight = 0;
        double currentValue = 0;
        int currentItem = 0;

        while(currentItem < items.length && currentWeight != W) {
            // если мы положим текущий предмет, и вес рюкзака не будет превышен
            if(currentWeight + items[currentItem].getWeight() < W) {
                // то берем объект целиком
                currentValue += items[currentItem].getValue();
                currentWeight += items[currentItem].getWeight();
            } else {
                // иначе берем часть от предмета
                // получаем ценность части объекта
                // вычисляем оставшийся вес, затем это делим на вес объекта, чтобы получить ту часть, которую можем взять
                // затем умножаем на ценность этого объекта целиком
                currentValue += ((W - currentWeight) / (double) items[currentItem].getWeight()) *
                        items[currentItem].getValue();

                currentWeight = W; // полный рюкзак, выходим из цикла

            }
            currentItem++;
        }
        System.out.println("Ценность наилучшего набора: " + currentValue);
    }

}

class Item {
    private int weight;
    private int value;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public double valuePerUnitOfWeight() {
        return value / (double) weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString(){
        return "{w: " + weight + " ,v: " + value + "}";
    }
}
