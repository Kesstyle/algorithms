package pl.kes.algorithms

class Test {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr: Array<String?> = arrayOfNulls(10)
            var j = 0
            for (i in 0..9 step 4) {
                arr[j++] = "t$i"
            }
            arr.forEach { e -> println(e) }
        }
    }

}