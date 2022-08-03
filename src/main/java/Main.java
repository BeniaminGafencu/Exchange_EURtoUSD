import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Main {
    public static int digitCounter(float num) {

        int count = 0;

        while (num > 1) {
            num /= 10;
            ++count;
        }
        return count;
    }
    public static void main(String args[]) throws Exception {
        //declare values
        float InitialValueInEUR;

        //reading EUR value
        System.out.println("Insert the value you want to convert: ");
        Scanner console = new Scanner(System.in);
        InitialValueInEUR=console.nextFloat();

        //convert to USD
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://currency-converter5.p.rapidapi.com/currency/convert?format=json&from=EUR&to=USD&amount=" + InitialValueInEUR)).header("X-RapidAPI-Key", "284a44bf7bmsh63709e9a0152f2bp11eb6ejsn034d0d1e5c53").header("X-RapidAPI-Host", "currency-converter5.p.rapidapi.com").method("GET", HttpRequest.BodyPublishers.noBody()).build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println((String)response.body());

        int index= response.body().indexOf("rate_for_amount");
        System.out.println("The result of the conversion is "+response.body().substring(index+18, index+23+digitCounter(InitialValueInEUR))+" USD");

    }
}
