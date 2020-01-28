package fr.isen.peltier.androidtoolbox

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class GetAgeTest {

    fun setup(): FormActivity {
        val sut = FormActivity()
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        sut.currentDate = formatter.parse("28/01/2020")
        return sut
    }

    @Test
    fun simpleYear_test() {
        val sut = FormActivity()
        val age = sut.getAge(2000, 1, 1)
        assertEquals(20, age)
    }

    @Test
    fun simpleMonth_test() {
        val sut = setup()
        val age = sut.getAge(2000, 1, 29)
        assertEquals(19, age)
    }
}