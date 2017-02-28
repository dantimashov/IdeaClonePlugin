package com.suhininalex.clones.core.postprocessing

import com.suhininalex.clones.core.*
import com.suhininalex.clones.core.structures.Clone
import com.suhininalex.clones.core.structures.CloneClass
import com.suhininalex.clones.core.structures.Token
import com.suhininalex.clones.core.utils.length
import com.suhininalex.clones.core.utils.textLength
import com.suhininalex.clones.core.utils.tokenSequence
import com.suhininalex.clones.core.utils.uniteRanges
import com.suhininalex.clones.ide.method

fun List<CloneClass>.filterSelfCoveredClasses(): List<CloneClass> =
        filter {
            with(it.getScore()) {
                selfCoverage <= 0.7 || selfCoverage <= 0.85 && sameMethodCount <= 0.7
            }
        }

data class CloneScore(val selfCoverage: Double, val sameMethodCount: Double, val length: Int)

fun CloneClass.getScore(): CloneScore =
        CloneScore(scoreSelfCoverage()/100.0, scoreSameMethod()/100.0, clones.first().textLength)

private fun CloneClass.scoreSelfCoverage(): Int =
        clones.first().scoreSelfCoverage()

private fun Clone.scoreSelfCoverage(): Int {
    val sequence = tokenSequence().toList()
    val indexMap = sequence.mapIndexed { i, psiElement ->  psiElement to i}.toMap()
    val length = com.suhininalex.clones.core.utils.suffixTree(sequence.map(::Token).toList())
            .getAllCloneClasses(10).toList()
            .filterSubClassClones()
            .flatMap { it.clones.toList() }
            .map{ IntRange(indexMap[it.firstPsi]!!, indexMap[it.lastPsi]!!) }
            .uniteRanges()
            .sumBy { it.length }
    val bigLength = sequence.size
    return length*100/bigLength
}

private fun CloneClass.scoreSameMethod(): Int {
    assert(size > 1)
    val mostPopularMethodNumber = clones.map{ it.firstPsi.method }.groupBy { it }.map { it.value.size }.max()
    return (mostPopularMethodNumber!!-1)*100/(size-1)
}