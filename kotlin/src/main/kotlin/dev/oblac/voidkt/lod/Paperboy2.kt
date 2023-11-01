package dev.oblac.voidkt.lod

data class Wallet3(private var cash : Int) {
	fun withdraw(amount : Int) : Int {
		if (amount > cash) {
			throw IllegalArgumentException("Insufficient funds")
		}
		cash -= amount
		return amount
	}
}

data class Customer2(val name : String, private val wallet : Wallet3) {
	fun pay(amount : Int): Int {
		return wallet.withdraw(amount)
	}
}

class Paperboy2 {
	var collectedAmount: Int = 0
	fun collectMoney(customer : Customer2, dueAmount: Int) {
		collectedAmount += customer.pay(dueAmount)
	}
}

//-------
// ISCAN
//-------

/*
WALLET
------
* 🟥 cash
* 🟧 withdraw
arg:
    - Int
out:
    - Int
inv:
    - [this]
use:
    - [cash]

CUSTOMER2
---------
* 🟦 wallet
* 🟧 pay
arg:
    - Int
out:
    - Int
inv:
	- wallet (I) : ✅

PAPERBOY
--------
* 🟥 collectedAmount
* 🟧 collectMoney
arg:
    - [this]
    - Customer
    - Int
out:
    - [collectedAmount]
inv:
	- customer (I) ✅
	- [this] (R/W)
use:
	- [collectedAmount]
*/

// But: why Paperboy _operates_ the Customer !?!?!?

// usage

fun main() {
	val customer = Customer2("John", Wallet3(100))
	val paperboy = Paperboy2()
	paperboy.collectMoney(customer, 10)
	println(customer)
	println(paperboy.collectedAmount)
}