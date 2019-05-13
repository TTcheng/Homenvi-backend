package me.wcc.base.infra.utils

import spock.lang.Specification

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset

/**
 *
 * @author chuncheng.wang@hand-china.com 2019-01-29 16:29:28
 */
class DateTimeUtilsTest extends Specification {
    def "test secondsBetween"() {
        given:
        LocalDateTime now = LocalDateTime.now()
        LocalDateTime _5secAgo = now.minusSeconds(5)
        when:
        def seconds = DateTimeUtils.secondsBetween(_5secAgo, now)
        then:
        println(ZoneOffset.systemDefault().getId())
        5 == seconds
    }

    def "test getNanos"() {
        given:
        Date now = new Date()
        when:
        def time = DateTimeUtils.getNanoTime(now)
        then:
        println(time)
        println(now.toTimestamp())
        println(now.getTime())
        noExceptionThrown()
    }

    def "test getNanos1"() {
        given:
        def now = Instant.now()
        when:
        def time = DateTimeUtils.getNanoTime(now)
        then:
        println(now.toEpochMilli())
        println(time)
        println(now.getNano())
        noExceptionThrown()
    }

    def "test getNanos2"() {
        given:
        def now = LocalDateTime.now()
        when:
        def time = DateTimeUtils.getNanoTime(now)
        then:
        println(time)
        println(now.getNano())
        noExceptionThrown()
    }
}
