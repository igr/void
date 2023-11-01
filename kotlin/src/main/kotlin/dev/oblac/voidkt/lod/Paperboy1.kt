package dev.oblac.voidkt.lod

// trying to fix the issue with the delegate
data class Customer1(val name : String, private val wallet : Wallet) {
	var cash : Int
		get() = wallet.cash
		set(value) {wallet.cash = value}
}

class Paperboy1 {
	private var collectedAmount: Int = 0
	fun collectMoney(customer : Customer1, dueAmount: Int) {
		if (customer.cash < dueAmount) {
			throw IllegalStateException("Customer has insufficient funds")
		}
		customer.cash -= dueAmount
		collectedAmount += dueAmount
	}
}

//-------
// ISCAN
//-------

/*
CUSTOMER1
---------
* 🟦 wallet
* 🟥 cash

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
	- customer (R/W) : NOK - access the state
	- [this] (R/W)
use:
	- cash
	- [collectedAmount]

*/

// Paperboy operates on Cache property; explicitly
