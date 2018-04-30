package com.vc.tutorial.algo.btree

import scala.collection.mutable

/**
  * https://www.youtube.com/watch?v=xLQKdq0Ffjg
  */
object IterativePostOrderTraversal {

  def main(args: Array[String]): Unit = {
    val btree = new BST()
    btree.addNode(10)
    btree.addNode(-5)
    btree.addNode(-10)
    btree.addNode(0)
    btree.addNode(5)
    btree.addNode(30)
    btree.addNode(36)
    btree.postOrder(btree.root)
    btree.postOrderRec(btree.root)
  }

  case class Node(var l: Option[Node], var r: Option[Node], data: Int) {
    override def toString: String = data.toString
  }

  class BST {
    var root: Option[Node] = None

    def addNode(d: Int): Node = {
      val n = Node(None, None, d)
      if (root.isEmpty) {
        root = Option(n)
      }
      else {
        var current = root
        var parent = current
        while (current.isDefined) {
          if (d > current.get.data) {
            parent = current
            current = current.get.r
          }
          else if (d < current.get.data) {
            parent = current
            current = current.get.l
          }
          else {
            parent = current
            current = None
          }
        }

        if (d > parent.get.data) {
          parent.get.r = Option(n)
        }
        else if (d < parent.get.data) {
          parent.get.l = Option(n)
        }
      }
      n
    }

    /**
      * left right root
      */
    def postOrder(r: Option[Node]): Unit = {
      val s = new mutable.Stack[Node]()
      var current = r
      while (current.isDefined || s.nonEmpty) {
        if (current.isDefined) {
          s.push(current.get)
          current = current.get.l
        }
        else {
          var t = s.head
          if (t.r.isDefined) {
            current = t.r
          }
          else {
            t = s.pop
            print(t.data + " ")
            while (s.nonEmpty && s.head.r.isDefined && s.head.r.get == t) {
              t = s.pop
              print(t.data + " ")
            }
          }
        }
      }
      println()
    }

    def postOrderRec(r: Option[Node]): Unit = {
      if (r.isDefined) {
        postOrderRec(r.get.l)
        postOrderRec(r.get.r)
        print(r.get.data + " ")
      }
    }

  }
}
