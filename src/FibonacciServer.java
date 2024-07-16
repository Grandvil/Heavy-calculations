import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FibonacciServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("Сервер запущен на порту 8080.");

        try (Socket clientSocket = serverSocket.accept();
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Клиент подключен.");

                // Получение числа от клиента
                int n = Integer.parseInt(line);
                System.out.println("Получено число: " + n);

                // Вычисление N-го члена Фибоначчи
                int fibonacci = calculateFibonacci(n);

                // Отправка ответа клиенту
                out.println(fibonacci);
                System.out.println("Отправлено число: " + fibonacci);

                System.out.println("Соединение закрыто.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка сервера: " + e.getMessage());
        }
    }

    // Рекурсивный метод для вычисления N-го члена Фибоначчи
    private static int calculateFibonacci(int n) {
        if (n <= 1) {
            return n;
        } else {
            return calculateFibonacci(n - 1) + calculateFibonacci(n - 2);
        }
    }
}