# -2
# Асинхронна обробка масивів чисел

## Опис проєкту
Цей проєкт реалізує асинхронну обробку великих масивів випадкових чисел за допомогою багатопотоковості в Java. Програма генерує масив випадкових чисел у заданому діапазоні, розбиває його на частини і обробляє кожну частину в окремому потоці, що дозволяє підвищити ефективність обробки даних. Кожен елемент масиву множиться на множник, який задається користувачем.

## Основні функції
- **Генерація масивів чисел**: Масив заповнюється випадковими цілими числами, які генеруються у діапазоні, заданому користувачем.
- **Поділ на частини та паралельна обробка**: Масив розділяється на частини певного розміру. Кожна частина обробляється окремим потоком, що підвищує швидкість виконання.
- **Множення елементів на заданий множник**: Користувач вводить множник, на який буде помножено кожен елемент масиву.
- **Використання `Future` для контролю завдань**: `Future` дозволяє відстежувати стан кожного завдання (обробка частини масиву), збирати результати та контролювати завершення чи скасування задачі.
- **Статус виконання задач**: Для кожної обробленої частини масиву перевіряється, чи завершено завдання (`isDone()`) або чи було воно скасовано (`isCancelled()`).
- **Фінальний звіт**: Після завершення всіх потоків програма виводить кінцевий оброблений масив і загальний час виконання.

## Технології
Проєкт побудований з використанням таких технологій та інструментів:
- **Java**: Основна мова програмування для реалізації асинхронності та багатопотокової обробки.
- **Багатопотоковість**: `ExecutorService`, `Callable` та `Future` для керування асинхронними потоками.
- **Колекція `CopyOnWriteArrayList`**: Використовується для безпечного збору результатів із різних потоків.
- **Випадкові числа**: Генерація випадкових чисел для заповнення масиву та перевірки роботи алгоритму на реальних даних.

## Вимоги
- **JDK 8** або вище.
- Інструмент для роботи з Java, наприклад, Visual Studio Code або IntelliJ IDEA.


