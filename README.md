# Payment Processing System

Java payment system for Cash, Check, and Credit Card payments.


## Classes

| Class | Description |
|-------|-------------|
| CustomerPayment | Abstract class with customer data |
| Cash | Cash payment with discount |
| Check | Check with balance validation |
| CreditCard | Credit card with fee & expiry |
| Payable | Authorization interface |
| Driver | Main class |

## Payment Rules

| Payment Type | calculatePayment() | isAuthorized() |
|--------------|-------------------|----------------|
| Cash | Amount - discount% | Always true |
| Check | Amount | Balance check |
| Credit Card | Amount + fee | Date validation |



