package v.o.i.d.vroom;

/*
At a lemonade stand, each lemonade costs $5.

Customers are standing in a queue to buy from you, and order one at a time (in the order specified by bills).

Each customer will only buy one lemonade and pay with either a $5, $10, or $20 bill.  You must provide the correct change to each customer, so that the net transaction is that the customer pays $5.

Note that you don't have any change in hand at first.

Return true if and only if you can provide every customer with correct change.

Example 1
Input: [5,5,5,10,20]
Output: true
Explanation:
From the first 3 customers, we collect three $5 bills in order.
From the fourth customer, we collect a $10 bill and give back a $5.
From the fifth customer, we give a $10 bill and a $5 bill.
Since all customers got correct change, we output true.

Example 2
Input: [10,10]
Output: false
 */

class Solution {

	private int billsOf5;
	private int billsOf10;
	private int billsOf20;

	public static void main(String[] args) {
		new Solution().run();

	}

	public void run() {
		int[] input = new int[] {
				5, 5, 5, 10, 20
		};

		for (int payment : input) {
			acceptMoney(payment);
			if (calculateTheReturningMoney(payment) == false) {
				System.out.println("NOPE");
				return;
			}
		}
		System.out.println("YES");
	}

	private boolean calculateTheReturningMoney(int payment) {
		int returnMoney = payment - 5;

		while (returnMoney > 0) {
			if (returnMoney >= 10 && billsOf10 > 0) {
				billsOf10--;
				returnMoney -= 10;
				continue;
			}
			if (returnMoney >= 5 && billsOf5 > 0) {
				billsOf5--;
				returnMoney -= 5;
				continue;
			}
			// error return false
			return false;
		}
		return true;
	}

	private void acceptMoney(int payment) {
		switch (payment) {
			case 5:
				billsOf5++; break;
			case 10:
				billsOf10++; break;
			case 20:
				billsOf20++; break;
		}
	}
}
