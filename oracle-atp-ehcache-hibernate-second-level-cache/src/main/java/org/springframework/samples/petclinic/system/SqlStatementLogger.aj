package org.springframework.samples.petclinic.system;

public aspect SqlStatementLogger {
    final int MIN_BALANCE = 10;

    pointcut callWithDraw(int amount, Account acc) : call(boolean Account.withdraw(int)) && args(amount) && target(acc);

    before(int amount, Account acc) : callWithDraw(amount, acc) {
    }

    boolean around(int amount, Account acc) :
      callWithDraw(amount, acc) {
        if (acc.balance < amount) {
            return false;
        }
        return proceed(amount, acc);
    }

    after(int amount, Account balance) : callWithDraw(amount, balance) {
    }
}
