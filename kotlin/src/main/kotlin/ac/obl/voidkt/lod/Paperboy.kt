package ac.obl.voidkt.lod

data class Wallet(var cash : Int)

data class Customer(val name : String, val wallet : Wallet)

class Paperboy {
	private var collectedAmount: Int = 0
	fun collectMoney(customer : Customer, dueAmount: Int) {
		if (customer.wallet.cash < dueAmount) {
			throw IllegalStateException("Customer has insufficient funds")
		}
		customer.wallet.cash -= dueAmount
		collectedAmount += dueAmount
	}
}

// A paperboy should not be taking cash out of a customer's wallet!


/*
ISCAN

PAPERBOY
--------
* 🟥 collectedAmount
* 🟧 collectMoney
arg:
    - [collectedAmount]
    - Customer
    - Int
out:
    - [collectedAmount]
inv:
	- customer (R) : This is OK.
	- wallet (R/W) : NOK - LoD broke here, why this method needs to know about the wallet?
	- [this] (R/W)
use:
	- wallet
	- cash
	- [collectedAmount]
*/