package dev.oblac.voidkt.lod

data class WalletX(val cash : Int)

data class CustomerX(val name : String, val wallet : WalletX)

data class PaperboyX(val name: String, val wallet: WalletX)

// Describes relationship between customer and paperboy and the operation of paying for papers
// There is no information about how this would be used later, hence the name
data class PayingContext(var customer: CustomerX, var paperboy: PaperboyX)

// verbs

fun takeMoneyFromWallet(wallet: WalletX, dueAmount: Int): WalletX {
	if (dueAmount > wallet.cash) {
		throw Exception("Not enough money")
	}
	return WalletX(wallet.cash - dueAmount)
}
fun addMoneyToWallet(wallet: WalletX, amount: Int): WalletX {
	return WalletX(wallet.cash + amount)
}

fun collectMoney(customer: CustomerX, paperboy: PaperboyX, dueAmount: Int): PayingContext {
	val updatedCustomer = takeMoneyFromWallet(customer.wallet, dueAmount)       // this is STILL not OK, as we don't care here HOW money is stored
		.let { customer.copy(wallet = it) }
	val updatedPaperboy = addMoneyToWallet(paperboy.wallet, dueAmount)
		.let { paperboy.copy(wallet = it) }

	return PayingContext(updatedCustomer, updatedPaperboy)
}

//-------
// ISCAN
//-------

/*

* 🟦 WalletX, CustomerX, PaperboyX, PayingContext
* 🟨 takeMoneyFromWallet
arg: WalletX, Int
out: WalletX
inv:
	- wallet (R/C)
use:
	- cash
* 🟨 addMoneyToWallet
arg: WalletX, Int
out: WalletX
inv:
	- wallet (R/C)
use:
	- cash
* 🟨 collectMoney
arg: Customer, Paperboy, Int
out: PayingContext
inv:
	- takeMoneyFromWallet (I)
	- addMoneyToWallet (I)
	- customer (R/C)
	- paperboy (R/C)
	- PayingContext(C)
use:
	- wallet
*/



// usage

fun main() {
	val payingContext = PayingContext(
		customer = CustomerX("Caki", WalletX(100)),
		paperboy = PaperboyX("Pico", WalletX(0))
	)

	val after = with(payingContext) {
		collectMoney(customer, paperboy, 10)
	}

	println(after.customer)
	println(after.paperboy)

}