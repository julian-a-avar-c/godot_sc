package me.julian_a_avar_c
package godot_sc

export org.scalacheck.Arbitrary
export org.scalacheck.Cogen
export org.scalacheck.Gen

trait TestSuite extends munit.DisciplineSuite, Expectations
