package com.dicoding.picodiploma.byecorona.utils

import com.dicoding.picodiploma.byecorona.data.model.Action
import com.dicoding.picodiploma.byecorona.data.model.Violation

object DataDummy {

    fun generateDummyViolations(): List<Violation> {
        val violations = ArrayList<Violation>()

        violations.add(
            Violation(
                1,
                "Berkerumun",
                "SOCIAL_DISTANCING_VIOLATION",
                "CCTV Istora Stadium - GBK",
                "",
                false,
                "2021-06-04 12.30",
                null
            )
        )

//        violations.add(
//            Violation(
//                2,
//                "Berkerumun",
//                "SOCIAL_DISTANCING_VIOLATION",
//                "CCTV Istora Stadium - GBK",
//                "",
//                true,
//                "2021-06-04 15.30",
//                Action(
//                    1,
//                    "Peringatan dengan Megaphone",
//                    "2021-06-04 15.35",
//                    "Kerumunan bubar, tidak dibutuhkan aksi lebih lanjut"
//                )
//            )
//        )

        return violations
    }
}