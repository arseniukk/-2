import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArrayProcessor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення множника від користувача
        System.out.print("Введiть множник: ");
        int multiplier = scanner.nextInt();
        
        int arraySize = new Random().nextInt(21) + 40; // Розмір масиву від 40 до 60
        int partitionSize = 10; // Розмір кожної частини
        
        List<Integer> array = generateRandomArray(arraySize, -100, 100); // Генеруємо масив
        System.out.println("Згенерований масив: " + array); // Додано для перевірки
        
        CopyOnWriteArrayList<Integer> resultArray = new CopyOnWriteArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(4); // Пул потоків
        
        long startTime = System.currentTimeMillis();

        // Розділення масиву на частини та обробка в потоках
        List<Future<List<Integer>>> futures = new ArrayList<>();
        for (int i = 0; i < arraySize; i += partitionSize) {
            List<Integer> subArray = array.subList(i, Math.min(i + partitionSize, arraySize));
            Future<List<Integer>> future = executorService.submit(new MultiplierTask(subArray, multiplier));
            futures.add(future);
        }

        // Очікування завершення потоків і збір результатів
        for (Future<List<Integer>> future : futures) {
            try {
                List<Integer> result = future.get(); // Блокує виконання до завершення потоку
                resultArray.addAll(result);
                System.out.println("Оброблено частину: " + result);

                // Перевірка статусу виконання завдання
                if (future.isDone()) {
                    System.out.println("Завдання завершено.");
                }
                if (future.isCancelled()) {
                    System.out.println("Завдання було скасовано.");
                }
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Помилка обробки частини масиву: " + e.getMessage());
            }
        }

        // Вивід результату
        System.out.println("Результат: " + resultArray);
        
        long endTime = System.currentTimeMillis();
        System.out.println("Час виконання програми: " + (endTime - startTime) + " мс");
        
        executorService.shutdown();
        scanner.close();
    }

    // Метод для генерації масиву випадкових чисел
    private static List<Integer> generateRandomArray(int size, int min, int max) {
        List<Integer> array = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            array.add(random.nextInt((max - min) + 1) + min);
        }
        return array;
    }

    // Callable для множення чисел у частині масиву
    static class MultiplierTask implements Callable<List<Integer>> {
        private final List<Integer> array;
        private final int multiplier;

        public MultiplierTask(List<Integer> array, int multiplier) {
            this.array = array;
            this.multiplier = multiplier;
        }

        @Override
        public List<Integer> call() {
            List<Integer> result = new ArrayList<>();
            for (Integer number : array) {
                result.add(number * multiplier);
            }
            return result;
        }
    }
}
