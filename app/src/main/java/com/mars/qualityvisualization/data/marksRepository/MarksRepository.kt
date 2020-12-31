package com.mars.qualityvisualization.data.marksRepository

import com.mars.qualityvisualization.data.marksRepository.models.ExpertMark
import com.mars.qualityvisualization.data.marksRepository.models.ExpertMarksGroup

class MarksRepository {

    fun getMarksGroups(): List<ExpertMarksGroup>  = data

    private val data = listOf(
        ExpertMarksGroup(
            "Експерти з галузі",
            createMarksMapForList(
                listOf(
                    ExpertMark(10, 8),
                    ExpertMark(9, 5),
                    ExpertMark(9, 10),
                    ExpertMark(6, 6),
                    ExpertMark(7, 5),
                    ExpertMark(9, 9),
                    ExpertMark(10, 9),
                    ExpertMark(6, 6),
                    ExpertMark(9, 8),
                    ExpertMark(6, 7),
                )
            ),
            7
        ),

        ExpertMarksGroup(
            "Експерти з usability",
            createMarksMapForList(
                listOf(
                    ExpertMark(9, 5),
                    ExpertMark(8, 9),
                    ExpertMark(7, 6),
                    ExpertMark(5, 5),
                    ExpertMark(5, 5),
                    ExpertMark(7, 9),
                    ExpertMark(9, 7),
                    ExpertMark(8, 5),
                    ExpertMark(7, 6),
                    ExpertMark(5, 8),
                )
            ),
            8
        ),
        ExpertMarksGroup(
            "Експерти з програмування",
            createMarksMapForList(
                listOf(
                    ExpertMark(10, 9),
                    ExpertMark(8, 6),
                    ExpertMark(9, 9),
                    ExpertMark(8, 10),
                    ExpertMark(8, 10),
                    ExpertMark(8, 7),
                    ExpertMark(10, 6),
                    ExpertMark(7, 10),
                    ExpertMark(6, 9),
                    ExpertMark(9, 6),
                )
            ),
            9
        ),
        ExpertMarksGroup(
            "Оцінки користувачів",
            createMarksMapForList(
                listOf(
                    ExpertMark(8, 7),
                    ExpertMark(8, 5),
                    ExpertMark(6, 6),
                    ExpertMark(8, 7),
                    ExpertMark(6, 4),
                    ExpertMark(8, 10),
                    ExpertMark(7, 10),
                    ExpertMark(6, 5),
                    ExpertMark(8, 6),
                    ExpertMark(4, 10),
                )
            ),
            5
        )
    )

    private fun createMarksMapForList(data: List<ExpertMark>): Map<String, ExpertMark> {
        return mapOf(
            "Точність управління а обчислень" to data[0],
            "Ступінь стандартності інтерфейсів" to data[1],
            "Функціональна повнота" to data[2],
            "Стійкість до помилок" to data[3],
            "Можливість розширення" to data[4],
            "Зручність роботи" to data[5],
            "Простота роботи" to data[6],
            "Відповідальність чинним стандартам" to data[7],
            "Переносність між програмним (апаратним) забезпеченням" to data[8],
            "Зручність навчання" to data[9],
        )
    }
}