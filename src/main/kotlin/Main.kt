fun main() {
    fun calculateCommission(
        cardType: String,
        previousTransfersSum: Int = 0,
        transferAmount: Int
    ) {
        val maxTransferAmountPerOperation = when (cardType) {  // Максимальный перевод за операцию
            "VK Pay" -> 15_000
            else -> 150_000
        }
        val maxTransferAmountPerMonth = when (cardType) {    //Максимальный первод в месяц
            "VK Pay" -> 40_000
            else -> 600_000
        }

        if (transferAmount > maxTransferAmountPerOperation) {  // Проверяем на максимальный перевод
            println("Превышена максимальная сумма перевода в рамках одной операции")
            return
        }


        val totalTransfersSum = previousTransfersSum + transferAmount  // Проверяем на переводы в месяц
        if (totalTransfersSum > maxTransferAmountPerMonth) {
            println("Превышен лимит суммы переводов за месяц")
            return
        }

        val commission = when (cardType) {
            "VK Pay" -> {
                println("По карте VK Pay перевод без комиссии")
            }

            "Maestro", "MasterCard" -> {
                if (previousTransfersSum <= 75_000) {
                    println("Перевод без коммиссии")
                } else {
                    val commission = ((transferAmount * 0.006) + 20).toInt()
                    println("Коммиссия за перевод по карте составит " + commission + " руб")
                }
            }

            "Visa", "Мир" -> {
                val commission = (transferAmount * 0.0075).toInt()
                if (commission > 35) commission else 35
                println("Коммиссия за перевод по карте составит " + commission + " руб")
            }

            else -> return

        }

    }
    calculateCommission("Мир", 60000, 140000)
}