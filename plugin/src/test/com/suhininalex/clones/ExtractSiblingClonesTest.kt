package com.suhininalex.clones

import com.suhininalex.clones.core.*
import com.suhininalex.clones.core.clonefilter.filterClones
import com.suhininalex.clones.core.structures.CloneClass
import com.suhininalex.clones.core.utils.printText
import com.suhininalex.clones.core.utils.stringId
import com.suhininalex.clones.ide.childrenMethods
import com.suhininalex.clones.ide.findAllClones
import com.suhininalex.clones.ide.method

class ExtractSiblingClonesTest : FolderProjectTest("testdata/siblingClones/") {

    val clones
        get() = cloneManager.getAllCloneClasses().filterClones().toList()

    fun testNotAloneDuplicate() {
        val problems = clones.splitSiblingClones().filterSameCloneRangeClasses().filter { ! checkCountInvariant(it) }
        problems.forEach {
            println("========================")
            println("Problem class:")
            it.printInfo()
        }
        assertTrue(problems.isEmpty())
    }

    fun testSameTokenLengthSequence(){
        val problems = clones
//            .filter {
//                it.splitToSiblingClones().any { ! checkTokenLengthInvariant(it) }
//            }
        baseDirectoryPsi.childrenMethods.forEach {
            println("METHOD: ${it.stringId}")
            cloneManager.findAllClones(it).forEach {
                println("========================")
                it.clones.forEach {
                    it.printText()
                    println("----------------------")
                }
            }
        }
//        problems.splitSiblingClones().filterSameCloneRangeClasses().forEach {
//                println("========================")
//                it.clones.forEach {
//                    it.printText()
//                    println("----------------------")
//                }
//
////                it.clones.map{it.normalizePsiHierarchy()}.forEach {
////                    println(it.tokenSequence().map{it.node.elementType}.toList())
////                }
//
//        }
        assertTrue(problems.isEmpty())
    }
}

fun CloneClass.printInfo(){
    clones.forEach {
        println(it.tokenSequence().toList())
        it.printText()
        println("------------------------")
    }
}