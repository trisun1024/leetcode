import java.util.*;

class InvalidTransactions {
    static class Transaction {
        String name, city;
        int amount, min;

        public Transaction(String[] transaction) {
            name = transaction[0];
            min = Integer.parseInt(transaction[1]);
            amount = Integer.parseInt(transaction[2]);
            city = transaction[3];
        }
    }

    public List<String> invalidTransactions(String[] transactions) {

        List<String> res = new ArrayList<>();
        List<Transaction> trs = new ArrayList<>();

        for (int i = 0; i < transactions.length; ++i)
            trs.add(new Transaction(transactions[i].split(",")));

        for (int i = 0; i < trs.size(); ++i) {
            if (trs.get(i).amount > 1000)
                res.add(transactions[i]);
            else
                for (int j = 0; j < trs.size(); ++j) {
                    if (i != j && trs.get(i).name.equals(trs.get(j).name)
                            && Math.abs(trs.get(i).min - trs.get(j).min) <= 60
                            && !trs.get(i).city.equals(trs.get(j).city)) {
                        res.add(transactions[i]);
                        break;
                    }
                }
        }
        return res;
    }
}