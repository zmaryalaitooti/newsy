package com.ahmadmaaz1.newsy

import com.ahmadmaaz1.newsy.presentatoin.component.getTimeAgo
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
class TestDate: StringSpec({

    val now = Instant.parse("2026-04-23T10:00:00Z")

    "should return seconds ago" {
        getTimeAgo("2026-04-23T09:59:30Z") shouldBe "30s ago"
    }

    "should return minutes ago" {
        getTimeAgo("2026-04-23T0:50:00Z") shouldBe "10m ago"
    }
})