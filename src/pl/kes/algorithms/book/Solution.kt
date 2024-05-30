





class Solution {

    private val map: MutableMap<Int, List<Int>> = HashMap()

    fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
        val queue = ArrayDeque<TreeNode>()
        queue.add(root!!)
        while (!queue.isEmpty()) {
            val node = queue.removeFirst()
            val v = node.`val`
            if (node.left != null) {
                addToMap(node.left!!.`val`, v)
                queue.add(node.left!!)
            }
            if (node.right != null) {
                addToMap(node.right!!.`val`, v)
                queue.add(node.right!!)
            }
        }
        var result = mutableListOf<Int>()
        if (map.size == 1) {
            return if (k == 0) {
      //          result.add()
                result
            } else {
                result
            }
        }
        val q = ArrayDeque<Int>()
        val visited = HashSet<Int>()
        q.add(target!!.`val`)
        visited.add(target.`val`)
        var s = k
        while (!q.isEmpty()) {
            val size = q.size
            s--
            if (s == 0) {
                for (i in 1..size) {
                    result.add(q.removeFirst())
                }
                return result
            } else {
                for (i in 1..size) {
                    val v = q.removeFirst()
                    println("$v has " + map[v])
                    val adj = map[v]
                    adj!!.forEach { w ->
                        if (!visited.contains(w)) {
                            visited.add(w)
                            q.add(w)
                        }
                    }
                }
            }
        }
        return result
    }

    private fun addToMap(v: Int, w: Int): Unit {
        if (map[v] == null) {
            map[v] = ArrayList()
        }
        if (map[w] == null) {
            map[w] = ArrayList()
        }
        map[v] = map[v]!! + w
        map[w] = map[w]!! + v
    }
}

class TreeNode(var `val`: Int = 0) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}