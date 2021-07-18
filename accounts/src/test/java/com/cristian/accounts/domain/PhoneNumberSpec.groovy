package com.cristian.accounts.domain

import spock.lang.Specification

class PhoneNumberSpec extends Specification {

    void "Create Phone Number, fails when empty"() {
        given:
        def inputNumber = ''
        when:
        PhoneNumber.of(inputNumber)
        then:
        thrown IllegalArgumentException
    }

}
