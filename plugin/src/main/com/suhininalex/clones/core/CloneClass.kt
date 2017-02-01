package com.suhininalex.clones.core

import com.suhininalex.suffixtree.Edge
import com.suhininalex.suffixtree.Node
import stream
import java.util.*
import java.util.stream.Stream

class CloneClass(val treeNode: Node) {

    val clones: Sequence<Clone>
        get() =
            if (length==0) {
                sequenceOf()
            } else {
                treeNode.edges.asSequence().flatMap { it.getTerminalsWithOffset() }.map { (edge, offset) ->
                    val lastElementIndex = edge.end - offset - edge.length
                    val firstElementIndex = lastElementIndex - treeNode.lengthToRoot() + 1
                    return@map Clone(this, edge.getFromSequence(firstElementIndex), edge.getFromSequence(lastElementIndex))
                }
            }


    val size by lazy {
        clones.count()
    }

    val length = treeNode.lengthToRoot()

    val isEmpty = clones.isEmpty()

    companion object {
        val lengthComparator = Comparator { first: CloneClass, second: CloneClass -> first.length - second.length }
    }

    private fun Edge.getTerminalsWithOffset() =
        Pair(this, 0).leafTraverse ({it.first.terminal==null}) {
            val offset = it.first.length + it.second
            it.first.terminal.edges.asSequence().map { Pair(it, offset) }
        }

    private fun Edge.getFromSequence(pos: Int) = sequence[pos] as Token
}