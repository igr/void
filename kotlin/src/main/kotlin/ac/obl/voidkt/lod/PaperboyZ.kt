package ac.obl.voidkt.lod

// verbs

fun charge(customer: CustomerX, amount: Int): CustomerX {
	return takeMoneyFromWallet(customer.wallet, amount)
		.let { customer.copy(wallet = it) }
}

fun receiveMoney(paperboy: PaperboyX, amount: Int): PaperboyX {
	return addMoneyToWallet(paperboy.wallet, amount)
		.let { paperboy.copy(wallet = it) }
}

fun collectMoney2(customer: CustomerX, paperboy: PaperboyX, dueAmount: Int): PayingContext {
	return PayingContext(
		customer = charge(customer, dueAmount),
		paperboy = receiveMoney(paperboy, dueAmount)
	)
}

//-------
// ISCAN
//-------

/*

* 🟨 charge
arg: Customer, Int
out: Customer
inv:
	- takeMoneyFromWallet (I)
	- customer (R/C)
* 🟨 receiveMoney
arg: Paperboy, Int
out: Paperboy
inv:
	- addMoneyToWallet (I)
	- paperboy (R/C)
* 🟨 collectMoney
arg: Customer, Paperboy, Int
out: PayingContext
inv:
	- charge (I)
	- receiveMoney (I)
	- PayingContext(C)
*/


// usage

fun main() {
	val payingContext = PayingContext(
		customer = CustomerX("Caki", WalletX(100)),
		paperboy = PaperboyX("Pico", WalletX(0))
	)

	val after = with(payingContext) {
		collectMoney2(customer, paperboy, 10)
	}

	println(after.customer)
	println(after.paperboy)

}