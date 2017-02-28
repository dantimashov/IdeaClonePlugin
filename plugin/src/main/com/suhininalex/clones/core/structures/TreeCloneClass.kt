package com.suhininalex.clones.core.structures

import com.suhininalex.clones.core.structures.Token
import com.suhininalex.clones.core.utils.isEmpty
import com.suhininalex.clones.core.utils.leafTraverse
import com.suhininalex.clones.core.utils.length
import com.suhininalex.clones.core.utils.lengthToRoot
import com.suhininalex.suffixtree.Edge
import com.suhininalex.suffixtree.Node

class TreeCloneClass(val treeNode: Node): CloneClass {

    override val clones: Sequence<TreeClone>
        get() =
            if (length==0) {
                emptySequence()
            } else {
                treeNode.edges.asSequence().flatMap { it.getTerminalsWithOffset() }.map { (edge, offset) ->
                    val lastElementIndex = edge.end - offset - edge.length
                    val firstElementIndex = lastElementIndex - treeNode.lengthToRoot() + 1
                    return@map TreeClone(edge.getFromSequence(firstElementIndex), edge.getFromSequence(lastElementIndex))
                }
            }


    override val size by lazy {
        clones.count()
    }

    val length = treeNode.lengthToRoot()

    val isEmpty = clones.isEmpty()

    private fun Edge.getTerminalsWithOffset(): Sequence<Pair<Edge, Int>> =
        Pair(this, 0).leafTraverse ({it.first.terminal==null}) {
            val offset = it.first.length + it.second
            it.first.terminal.edges.asSequence().map { Pair(it, offset) }
        }

    private fun Edge.getFromSequence(pos: Int) = sequence[pos] as Token
}