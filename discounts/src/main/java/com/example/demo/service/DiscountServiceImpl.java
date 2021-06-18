package com.example.demo.service;

import com.example.demo.model.Discount;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private static int indexTitle;
    private static int indexPrice;
    private static int indexDescription;
    @Override
    public String getDiscounts() {
        List<Discount> discounts = new ArrayList<>();
        String connection = getConnection();
        do {
            String title = getTitle(connection);
            String price = getPrice(connection);
            String description = getDescription(connection);
            if(title==null || price==null || description==null)
                continue;
            Discount discount = new Discount();
            discount.setDescription(description);
            discount.setPrice(price);
            discount.setTitle(title);
            discounts.add(discount);
        } while (indexTitle<150000);
        return convertToMessage(discounts);
    }
    private String convertToMessage(List<Discount> discounts){
        StringBuilder stringBuilder= new StringBuilder();
        int i=1;
        for(Discount e: discounts){
            stringBuilder.append(i).append(" ");
            stringBuilder.append(e.getTitle());
            stringBuilder.append("\n");
            stringBuilder.append(e.getPrice());
            stringBuilder.append("\n");
            stringBuilder.append(e.getDescription());
            stringBuilder.append("\n");
            stringBuilder.append("\n");
            i++;
        }
        return stringBuilder.toString();
    }
    private String getTitle(String content) {
        int index = content.indexOf(" data-handler=\"track\" data-track='{\"action\":\"goto_thread image\",\"beacon\":true}'", indexTitle+3);
        indexTitle = content.indexOf("alt=\"", index);
        int endIndex = content.indexOf("\"", indexTitle + 6);
        if(endIndex<indexTitle+5)
            return null;
        String title = content.substring(indexTitle + 5, endIndex);
        return title;
    }

    private String getPrice(String content) {
        int index = content.indexOf(" data-handler=\"track\" data-track='{\"action\":\"goto_thread image\",\"beacon\":true}'", indexPrice+3);
         indexPrice = content.indexOf("<span class=\"thread-price text--b cept-tp size--all-l size--fromW3-xl\">", index);
        int startLength = "<span class=\"thread-price text--b cept-tp size--all-l size--fromW3-xl\">".length();
        int endIndex = content.indexOf("</span>", indexPrice + 1 + startLength);
        if(endIndex<indexPrice + startLength)
            return null;
        String price = content.substring(indexPrice + startLength, endIndex);
        return price;
    }

    private String getDescription(String content) {
        int index = content.indexOf(" data-handler=\"track\" data-track='{\"action\":\"goto_thread image\",\"beacon\":true}'", indexDescription+3);
         indexDescription = content.indexOf("data-handler=\"lightbox-xhr emoticon-preview\" data-lightbox-xhr='{\"name\":\"threads\"}'", index);
        int startLength = "data-handler=\"lightbox-xhr emoticon-preview\" data-lightbox-xhr='{\"name\":\"threads\"}'".length() + 1;
        int endIndex = content.indexOf("<", indexDescription + startLength + 1);
        if(endIndex-5<(indexDescription + startLength + 6))
            return null;
        String description = content.substring(indexDescription + startLength + 6, endIndex - 5);
        description=description.replaceAll("\t","");
        return description;
    }

    private String getConnection() {
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse response;
        try {
            response = client.send(
                    HttpRequest.newBuilder().uri(URI.create("https://www.pepper.pl/grupa/artykuly-spozywcze"))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString());
            String content = (String) response.body();
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
