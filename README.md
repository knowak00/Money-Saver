# Money-Saver
Money Saver is an app which helps people saving their money.
### How it works?
You can add your expense to database and be aware of how much you have spent.
Furthermore if expense with the title exists, app will just update a sum.
It's protected against data redundancy and is easier to read.Requests are sent by gateway.
![APLIKACJA POST](https://user-images.githubusercontent.com/80634182/122716543-a9ad3800-d26a-11eb-87c6-c4f6ce35b704.jpg)

### Reading from datebase
If you want to know how much money you've already spent, use a GET method. It's even easier than adding because you can do this by Web browser.
Don’t be shocked if you wouldn’t be able to see as much data as you added. Remember, we don't want to redudant data. It's not important how many times you were in "TESCO", because app just says you left there a lot of money.


![APLIKACJA GET](https://user-images.githubusercontent.com/80634182/122717540-052bf580-d26c-11eb-83e1-c3e2c1ed994a.jpg)

### Is it just a simple "LIST-APP"?

No! App looks for big deals for you!
What does it mean?
It copies deals from "Pepper.pl", cuts and sends them on your email address.
You have to use POST request to start but app sends asynchronous message so don’t worry because you don’t have to do anything else.

![emailProject](https://user-images.githubusercontent.com/80634182/122718477-48d32f00-d26d-11eb-8c7f-202a48927edb.jpg)
