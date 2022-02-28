package ru.gb.repository

import org.hamcrest.core.IsNot.not
import org.junit.Assert.*
import org.junit.Test
import ru.gb.dictionary.Utils.convertMeaningsToString
import ru.gb.dictionary.Utils.mapSearchResultToResult
import ru.gb.model.data.DataModel
import ru.gb.model.data.Meanings
import ru.gb.model.data.Translation
import ru.gb.model.dto.MeaningsDto
import ru.gb.model.dto.SearchResultDto
import ru.gb.model.dto.TranslationDto

class ParserTest {

    @Test
    fun mapSearchResultToResult_shouldBeEqual(){
        val listExpected : List<DataModel> = listOf(DataModel("", listOf(Meanings(Translation(""),""))))
        val resultList: List<DataModel> = mapSearchResultToResult(listOf(SearchResultDto("", listOf(
            MeaningsDto(TranslationDto(""), "")))))

        assertEquals(listExpected, resultList)

    }

    @Test
    fun mapSearchResultToResult_shouldNotBeEqual(){
        val listExpected : List<SearchResultDto> = listOf(SearchResultDto("", listOf(
            MeaningsDto(TranslationDto(""), ""))))
        val resultList: List<DataModel> = mapSearchResultToResult(listOf(SearchResultDto("", listOf(
            MeaningsDto(TranslationDto(""), "")))))

        assertThat(listExpected, not(resultList))

    }

    @Test
    fun mapSearchResultToResult_shouldNotBeEqualListEmpty(){
        val listExpected : List<DataModel> = listOf(DataModel())
        val resultList: List<DataModel> = mapSearchResultToResult(listOf(SearchResultDto("", listOf(
            MeaningsDto(TranslationDto(""), "")))))

        assertThat(listExpected, not(resultList))
    }

    @Test
    fun mapSearchResultToResult_shouldNotBeEmpty(){
        val resultList: List<DataModel> = mapSearchResultToResult(listOf(SearchResultDto("", listOf(
            MeaningsDto(TranslationDto(""), "")))))

        assertFalse(resultList.isEmpty())
    }


    @Test
    fun convertMeaningsToString_shouldBeEqual(){
        val expectedResult = "test"
        val resultList = convertMeaningsToString(listOf(Meanings(Translation("test"),"")))
        assertEquals(expectedResult, resultList)
    }

    @Test
    fun convertMeaningsToString_shouldNotBeNull(){
        val resultList = convertMeaningsToString(listOf(Meanings(Translation("test"),"")))
        assertNotNull(resultList)
    }

    @Test
    fun convertMeaningsToString_shouldNotBeNullVariants(){
        val resultList = convertMeaningsToString(listOf(Meanings(Translation(""),"")))
        assertNotNull(resultList)
    }



}