import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FibonacciClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);

        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
             Scanner scanner = new Scanner(System.in)) {

            // Получение числа от пользователя
            System.out.print("Введите число: ");
            int n = Integer.parseInt(scanner.nextLine());

            // Отправка числа серверу
            out.println(n);
            System.out.println("Отправлено число: " + n);

            // Получение ответа от сервера
            int fibonacci = Integer.parseInt(in.readLine());
            System.out.println("Получено число: " + fibonacci);

        } catch (IOException e) {
            System.err.println("Ошибка клиента: " + e.getMessage());
        }
    }
}