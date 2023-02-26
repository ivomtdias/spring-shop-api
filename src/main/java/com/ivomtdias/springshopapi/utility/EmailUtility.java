package com.ivomtdias.springshopapi.utility;

import org.springframework.stereotype.Component;

@Component
public class EmailUtility {

    public String newOrderSubject() {
        return "SHOP_API | New Order Confirmation";
    }

    public String newOrderEmail(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                	<title>Email</title>
                	<meta charset="UTF-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body>
                	<h1>Thank you for your order</h1>
                	<p>Hey,</p>
                	<p>We will inform you when your order will be shipped</p>
                	<p>Thanks!</p>
                </body>
                </html>            
                """;
    }

    public String orderShippedSubject() {
        return "SHOP_API | Order Shipped";
    }

    public String orderShippedEmail(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                	<title>Email</title>
                	<meta charset="UTF-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body>
                	<h1>Your order is on the way!</h1>
                	<p>Hey,</p>
                	<p>We will inform you when your order arrives</p>
                	<p>Thanks!</p>
                </body>
                </html>            
                """;
    }

    public String orderCompletedSubject() {
        return "SHOP_API | Order Shipped";
    }

    public String orderCompletedEmail(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                	<title>Email</title>
                	<meta charset="UTF-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body>
                	<h1>Your order as arrived!</h1>
                	<p>Hey,</p>
                	<p>Your order as arrived to the address you provided</p>
                	<p>Thanks!</p>
                </body>
                </html>            
                """;
    }

    public String orderCanceledSubject() {
        return "SHOP_API | Order Canceled";
    }

    public String orderCanceledEmail(){
        return """
                <!DOCTYPE html>
                <html>
                <head>
                	<title>Email</title>
                	<meta charset="UTF-8">
                	<meta name="viewport" content="width=device-width, initial-scale=1.0">
                </head>
                <body>
                	<h1>Your order as been canceled! :(</h1>
                	<p>Hey,</p>
                	<p>unfortunately your order as been canceled</p>
                	<p>Thanks!</p>
                </body>
                </html>            
                """;
    }

}
