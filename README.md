# ExpensesManagement


## Description
This is a personal expenses management application that allows you to track how much money have you spent.
All you need to do is run the application and write in command line some special commands. 
You can enter costs by specifying a date, amount of money, a variety of currencies and the purpose of payment.
Expenses for various dates could be added in any order. 

## Commands
Application supports followong commands:
* **add yyyy-MM-dd 10 USD Apple** - adds expense entry to the list of user expenses. Command accepts following parameters:

*yyyy-MM-dd*  — is the date when expense occurred

*10* — is an amount of money spent

*USD* — the currency in which expense occurred. See the list of possible currencies below.

*Apple* — is the name of product purchased

* **list** — shows the list of all expenses sorted by date
 * **clear yyyy-MM-dd** — removes all expenses for specified date,
where:

*yyyy-MM-dd* — is the date for which all expenses should be removed

* **total PLN** — this command calculates the total amount of money spent and present it to you in specified currency, 
where:

*PLN* — is the currency in which total amount of expenses should be presented.

## Possible currencies

AUD, BGN, BRL, CAD, CHF, CNY, CZK, DKK, GBP, HKD, HRK, HUF, IDR, ILS, INR, JPY, KRW, MXN, MYR, NOK, NZD, PHP, LN, RON, RUB, EUR, SEK, SGD, THB, TRY, USD, ZAR.


