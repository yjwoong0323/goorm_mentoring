package me.james.scope

fun main() {
  val person = Person("이재웅", 27)

// 1 - java
//  if (person != null && person.isAdult) {
//    view.showPerson(person)
//  } else {
//    view.showError()
//  }

// 2
//  person?.takeIf { it.isAdult }
//    ?.let { view::showPerson }
//    ?: view.showError
}

//class Person(
//  val name: String,
//  val age: Int
//) {
//  val isAdult: Boolean = age >= 20
//}